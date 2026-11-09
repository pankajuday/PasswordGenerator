package com.pankajuday.cli.commands;

import picocli.CommandLine.Command;

@Command(
        name = "pg",
        description = "Generate strong passwords and cryptographic secrets.",
        mixinStandardHelpOptions = true,
        version = "pg 1.0.0",
        customSynopsis = "pg <command> [options]",
        subcommands = {PasswordCommand.class, SecretCommand.class}
)
public final class PgCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Use 'pg --help' to see available commands.");
    }
}
