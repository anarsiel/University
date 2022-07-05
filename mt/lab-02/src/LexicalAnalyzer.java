import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;


class LexicalAnalyzer {
    private static final String[] TYPE_KEY_WORDS = {"int", "float"};
    private static final String[] COMPARE_KEY_WORDS = {"<", ">", "<=", ">="};
    private static final String[] OPER_KEY_WORDS = {"++", "--"};
    private static final String FOR_KEY_WORD = "for";

    private final InputStream is;
    private int currentChar;
    private int currentPos;
    private Token currentToken;

    LexicalAnalyzer(InputStream is) throws ParseException {
        this.is = is;
        this.currentPos = -1;
        nextChar();
        nextToken();
    }

    private boolean isBlank(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t';
    }

    private void nextChar() throws ParseException {
        currentPos++;

        try {
            currentChar = is.read();
        } catch (IOException e) {
            throw new ParseException(e.getMessage(), currentPos);
        }
    }

    String nextToken() throws ParseException {
        while (isBlank(currentChar)) {
            nextChar();
        }

        if (Character.isAlphabetic(currentChar)
                || Character.isDigit(currentChar)
                || currentChar == '_'
                || currentChar == '+'
                || currentChar == '-'
                || currentChar == '<'
                || currentChar == '>'
        ) {
            StringBuilder value = new StringBuilder();

            while (Character.isDigit(currentChar)
                    || Character.isAlphabetic(currentChar)
                    || currentChar == '_'
                    || currentChar == '<'
                    || currentChar == '>'
                    || currentChar == '.'
                    || currentChar == '='
            ) {
                value.append((char) Character.toLowerCase(currentChar));
                nextChar();
            }

            if (value.toString().isEmpty()) {
                while (currentChar == '-'
                        || currentChar == '+'
                ) {
                    value.append((char) Character.toLowerCase(currentChar));
                    nextChar();
                }
            }

            currentToken = Token.NAME;

            try {
                double d = Double.parseDouble(value.toString());
                currentToken = Token.VALUE;
            } catch(NumberFormatException ignored) {
            }

            if (value.toString().equals(FOR_KEY_WORD)) {
                currentToken = Token.FOR;
            }

            for (String typeName : TYPE_KEY_WORDS) {
                if (value.toString().equals(typeName)) {
                    currentToken = Token.TYPE;
                    break;
                }
            }

            for (String typeName : COMPARE_KEY_WORDS) {
                if (value.toString().equals(typeName)) {
                    currentToken = Token.COMPARE;
                    break;
                }
            }

            for (String typeName : OPER_KEY_WORDS) {
                if (value.toString().equals(typeName)) {
                    currentToken = Token.OPER;
                    break;
                }
            }
            return value.toString();
        } else {
            switch (currentChar) {
                case '=': {
                    nextChar();
                    currentToken = Token.EQUALLY;
                    break;
                }
                case '(': {
                    nextChar();
                    currentToken = Token.LEFT_PAREN;
                    break;
                }
                case ')': {
                    nextChar();
                    currentToken = Token.RIGHT_PAREN;
                    break;
                }
                case ';': {
                    nextChar();
                    currentToken = Token.SEMICOLON;
                    break;
                }
                case ',': {
                    nextChar();
                    currentToken = Token.COMMA;
                    break;
                }
                case -1: {
                    currentToken = Token.END;
                    break;
                }
                default: {
                    throw new ParseException(
                            "Unexpected character " + (char) currentChar + " at position: " + currentPos,
                            currentPos
                    );
                }
            }
            if (currentChar != -1) {
                return Character.toString(currentChar);
            } else {
                return "";
            }
        }
    }

    int getCurrentChar() {
        return currentChar;
    }

    Token getCurrentToken() {
        return currentToken;
    }

    int getCurrentPos() {
        return currentPos;
    }
}