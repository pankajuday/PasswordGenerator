package com.pankajuday;
import com.pankajuday.cli.CliRunner;

public class Main {

    public static void main(String[] args) {
        int exitCode = CliRunner.runner("--help");
        System.exit(exitCode);
    }
}