$ErrorActionPreference = 'Stop'

$projectRoot = Split-Path -Parent $PSScriptRoot
$targetDirectory = Join-Path $projectRoot 'target'
$outputDirectory = Join-Path $projectRoot 'dist/win'
$jarName = 'PasswordGenerator-1.0.0.jar'

& mvn.cmd -q -DskipTests package
if ($LASTEXITCODE -ne 0) {
    exit $LASTEXITCODE
}

New-Item -ItemType Directory -Path $outputDirectory -Force | Out-Null

& jpackage.exe `
    --type app-image `
    --name pg `
    --input $targetDirectory `
    --main-jar $jarName `
    --main-class com.pankajuday.Main `
    --dest $outputDirectory `
    --win-console

if ($LASTEXITCODE -ne 0) {
    exit $LASTEXITCODE
}

Write-Output (Join-Path $outputDirectory 'pg\pg.exe')
