package com.pankajuday.cli;
import com.pankajuday.cli.commands.Command;
import picocli.CommandLine;
public class CliRunner implements Runnable {
    @Override
    public void run() {
        System.out.println("Running command");
    }

    public static int runner(String args) {
        return new CommandLine(new Command()).execute(args);
    }
}
