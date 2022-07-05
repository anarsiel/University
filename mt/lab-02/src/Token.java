public enum Token {
    FOR("for"),
    LEFT_PAREN("left"),
    RIGHT_PAREN("right"),
    SEMICOLON(";"),
    COMMA(";"),
    OPER("oper"),
    VALUE("value"),
    NAME("name"),
    EQUALLY("="),
    COMPARE("cmp"),
    TYPE("type"),
    END("$");

    private final String value;

    Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
