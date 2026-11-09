#!/usr/bin/env bash
set -euo pipefail

script_directory="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
project_root="$(cd "$script_directory/.." && pwd)"
target_directory="$project_root/target"
output_directory="$project_root/dist/linux"
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

printf '%s\n' "$output_directory/pg/bin/pg"
