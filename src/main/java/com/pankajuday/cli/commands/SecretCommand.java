package com.pankajuday.cli.commands;

import com.pankajuday.service.SecretGenerator;
import com.pankajuday.validators.PatternValidator;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "sec", description = "Generate cryptographic secrets for JWT signing keys, API keys, and similar uses.", mixinStandardHelpOptions = true)
public final class SecretCommand implements Runnable {
    @Option(names = {"-b", "--bytes"}, defaultValue = "32", description = "Random bytes per secret. Minimum: 16. Default: ${DEFAULT-VALUE}.")
    private int bytes;

    @Option(names = {"-f", "--format"}, defaultValue = "base64url", description = "Output format: ${COMPLETION-CANDIDATES}. Default: ${DEFAULT-VALUE}.")
    private SecretGenerator.Format format;

    @Option(names = {"-c", "--count"}, defaultValue = "1", description = "Number of secrets to generate. Default: ${DEFAULT-VALUE}.")
    private int count;

    @Override
    public void run() {
        PatternValidator.validateSecretOptions(bytes, count);
        SecretGenerator generator = new SecretGenerator();
        for (int index = 0; index < count; index++) {
            System.out.println(generator.generate(bytes, format));
        }
    }
}
