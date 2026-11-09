#!/usr/bin/env bash

# NOTE:
# This packaging script has not been tested on macOS in this environment.
# Please run and verify the build on your own Mac before relying on it for use.
# The project must be built locally on your device, and no
# guarantee is provided that it will work correctly on every macOS system.

set -euo pipefail

script_directory="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
project_root="$(cd "$script_directory/.." && pwd)"
target_directory="$project_root/target"
output_directory="$project_root/dist/macos"
jar_name="PasswordGenerator-1.0.0.jar"

command -v jpackage >/dev/null || { echo "jpackage is required. Install JDK 21 or later." >&2; exit 1; }

cd "$project_root"
mvn -q -DskipTests package
mkdir -p "$output_directory"

jpackage \
    --type app-image \
    --name pg \
    --input "$target_directory" \
    --main-jar "$jar_name" \
    --main-class com.pankajuday.Main \
    --dest "$output_directory"

printf '%s\n' "$output_directory/pg.app/Contents/MacOS/pg"
