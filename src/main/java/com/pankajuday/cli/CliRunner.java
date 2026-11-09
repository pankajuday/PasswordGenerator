package com.pankajuday.cli;
import com.pankajuday.cli.commands.PgCommand;
import picocli.CommandLine;

public final class CliRunner {
    private CliRunner() {
    }

    public static int runner(String[] args) {
        CommandLine commandLine = new CommandLine(new PgCommand());
        commandLine.setCaseInsensitiveEnumValuesAllowed(true);
        commandLine.setParameterExceptionHandler((exception, arguments) -> {
            CommandLine failingCommand = exception.getCommandLine();
            failingCommand.getErr().println("Error: " + exception.getMessage());
            failingCommand.usage(failingCommand.getErr());
            return failingCommand.getCommandSpec().exitCodeOnInvalidInput();
        });
        commandLine.setExecutionExceptionHandler((exception, failingCommand, parseResult) -> {
            failingCommand.getErr().println("Error: " + exception.getMessage());
            return failingCommand.getCommandSpec().exitCodeOnExecutionException();
        });
        return commandLine.execute(args);
    }
}
