package com.pankajuday.cli.commands;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "ps",
        mixinStandardHelpOptions = true
)
public class PasswordCommand implements Runnable {

    @Option(
            names = {"-l", "--length"},
            description = "Password length",
            defaultValue = "16"
    )
    private int length;

    @Option(
            names = {"-n", "--numbers"},
            description = "Include numbers"
    )
    private boolean numbers;

    @Option(
            names = {"-s", "--symbols"},
            description = "Include symbols"
    )
    private boolean symbols;

    @Override
    public void run() {
        
    }
}