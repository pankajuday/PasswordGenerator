package com.pankajuday.cli.commands;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import com.pankajuday.service.PasswordGenerator;
import com.pankajuday.validators.PatternValidator;

@Command(
        name = "pw",
        description = "Generate strong passwords that do not contain configured known words.",
        mixinStandardHelpOptions = true
)
public class PasswordCommand implements Runnable {

    @Option(
            names = {"-l", "--length"},
            description = "Characters per password. Minimum: 12. Default: ${DEFAULT-VALUE}.",
            defaultValue = "20"
    )
    private int length;

    @Option(
            names = {"-n", "--numbers"},
            negatable = true,
            description = "Include digits. Use --no-numbers to exclude them."
    )
    private Boolean numbers;

    @Option(
            names = {"-s", "--symbols"},
            negatable = true,
            description = "Include symbols. Use --no-symbols to exclude them."
    )
    private Boolean symbols;

    @Option(names = "--no-uppercase", description = "Exclude uppercase letters.")
    private boolean noUppercase;

    @Option(names = "--no-lowercase", description = "Exclude lowercase letters.")
    private boolean noLowercase;

    @Option(names = {"-a", "--exclude-ambiguous"}, description = "Exclude visually ambiguous characters such as O, 0, l, and 1.")
    private boolean excludeAmbiguous;

    @Option(names = {"-c", "--count"}, defaultValue = "1", description = "Number of passwords to generate. Default: ${DEFAULT-VALUE}.")
    private int count;

    @Override
    public void run() {
        boolean includeNumbers = numbers == null || numbers;
        boolean includeSymbols = symbols == null || symbols;
        PatternValidator.validatePasswordOptions(length, count, !noUppercase, !noLowercase, includeNumbers, includeSymbols);
        PasswordGenerator generator = new PasswordGenerator();
        for (int index = 0; index < count; index++) {
            System.out.println(generator.generate(length, !noUppercase, !noLowercase, includeNumbers, includeSymbols, excludeAmbiguous));
        }
    }
}
