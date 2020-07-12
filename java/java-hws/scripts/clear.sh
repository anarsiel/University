SCRIPT_PATH="$( cd "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"
ROOT_DIR=$(dirname "${SCRIPT_PATH}")

rm -r "$ROOT_DIR"/out