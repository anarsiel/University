SCRIPT_PATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
ROOT_DIR=$(dirname "${SCRIPT_PATH}")

java -jar "$ROOT_DIR"/lib/junit-platform-console-standalone-1.6.2.jar -cp "$ROOT_DIR/out" --scan-classpath

echo $?
exit $?