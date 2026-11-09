package com.pankajuday.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.pankajuday.cli.commands.PgCommand;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

class CommandConfigurationTest {
    @Test
    void registersPasswordAndSecretSubcommands() {
        CommandLine commandLine = new CommandLine(new PgCommand());

        assertEquals("pg", commandLine.getCommandName());
        assertTrue(commandLine.getSubcommands().containsKey("pw"));
        assertTrue(commandLine.getSubcommands().containsKey("sec"));
    }
}
