SCRIPT_PATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
ROOT_DIR=$(dirname "${SCRIPT_PATH}")

find "$ROOT_DIR"/java-solutions/ru/ifmo/rain/dimitrov/bank/* -name "*.java" > sources.txt

javac -d "$ROOT_DIR"/out -cp "$ROOT_DIR"/java-solutions:"$ROOT_DIR"/lib/* @sources.txt

rm sources.txt