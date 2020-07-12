SCRIPT_PATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
ROOT_DIR=$(dirname "${SCRIPT_PATH}")

java  -cp "$ROOT_DIR"/out ru.ifmo.rain.dimitrov.bank.server.Server