# pg

`pg` is a terminal generator for strong passwords and cryptographic secrets.
It uses Java's `SecureRandom`, not a predictable pseudo-random generator.

Build and run:

```bash
mvn package
java -jar target/PasswordGenerator-1.0.0.jar --help
```

Generate a password:

```bash
java -jar target/PasswordGenerator-1.0.0.jar pw
java -jar target/PasswordGenerator-1.0.0.jar pw --length 28 --exclude-ambiguous
java -jar target/PasswordGenerator-1.0.0.jar pw --no-symbols --count 3
```

`pw` defaults to 20 characters and includes uppercase letters, lowercase letters, digits, and symbols. Every selected character group is represented in each password. Candidates containing entries from `src/main/resources/forbidden_words.txt` are rejected; configured substitutions in `substitutions.properties` are attempted before generating another candidate.

Generate a secret:

```bash
java -jar target/PasswordGenerator-1.0.0.jar sec
java -jar target/PasswordGenerator-1.0.0.jar sec --bytes 64 --format hex
```

`sec` defaults to 32 random bytes (256 bits), encoded as unpadded URL-safe Base64. This is suitable for a JWT HMAC signing secret when the algorithm and key size meet the requirements of the JWT library you use. Treat generated output as sensitive: do not commit it, add it to shell history, or share it in logs.

## Windows portable app

With JDK 21 on `PATH`, run the following in PowerShell:

```powershell
.\scripts\package-windows.ps1
```

JPackage creates a portable application image at `dist\pg`. WiX is not required. Run the console launcher directly:

```powershell
.\dist\pg\pg.exe pw
.\dist\pg\pg.exe sec --format hex
```

Keep the complete `dist\pg` directory when copying the application to another Windows computer, because it contains the launcher, application JAR, and bundled Java runtime.

## Linux portable app

With JDK 21 on `PATH`, run:

```bash
bash scripts/package-linux.sh
./dist/linux/pg/bin/pg pw
```

Keep the complete `dist/linux/pg` directory when distributing the application.

## macOS portable app

With JDK 21 on `PATH`, run:

```bash
bash scripts/package-macos.sh
./dist/macos/pg.app/Contents/MacOS/pg pw
```

Keep the complete `dist/macos/pg.app` application bundle when distributing the application. The bundle is unsigned; signing and notarization are required before distributing outside your own Mac environment.
