package com.pankajuday.cli.commands;
import picocli.CommandLine;

@CommandLine.Command(
        name = "pg",
        description = "Password generator",
        mixinStandardHelpOptions = true,
        version = "pg 1.0.0",
        customSynopsis = {"pg [COMMAND] [OPTIONS]"},
        subcommands = {
                PasswordCommand.class,
        }
)

public class Command {

}
