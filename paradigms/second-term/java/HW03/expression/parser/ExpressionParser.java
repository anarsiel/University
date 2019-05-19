package expression.parser;

import expression.TripleExpression;
import expression.exceptions.*;
import expression.operations.Operation;

public class ExpressionParser<T> implements Parser {

    private final Operation<T> operation;

    public ExpressionParser(Operation<T> op) {
        operation = op;
    }

    public TripleExpression<T> parse(String expression) throws ParsingException {
        s = expression;
        i = 0;
        depth = 0;
        cur = Token.END;
        TripleExpression<T> a = add();
        return a;
    }

    private TripleExpression<T> add() throws ParsingException {
        TripleExpression<T> a = mul();
        while (true) {
            switch (cur) {
                case ADD:
                    a = new CheckedAdd<T>(a, mul(), operation);
                    break;
                case SUBTRACT:
                    a = new CheckedSubtract<T>(a, mul(), operation);
                    break;
                default:
                    return a;
            }
        }
    }

    private TripleExpression<T> mul() throws ParsingException {
        TripleExpression<T> a = unar();
        while (true) {
            switch (cur) {
                case MUL:
                    a = new CheckedMultiply<T>(a, unar(), operation);
                    break;
                case DIVIDE:
                    a = new CheckedDivide<T>(a, unar(), operation);
                    break;
                case MOD:
                    a = new CheckedMod<>(a, unar(), operation);
                    break;
                default:
                    return a;
            }
        }
    }


    private TripleExpression<T> unar() throws ParsingException {
        next();
        TripleExpression<T> a;
        switch (cur) {
            case SQR:
                a = new CheckedSquare<>(unar(), operation);
                break;
            case ABS:
                a = new CheckedAbs<>(unar(), operation);
                break;
            case OPEN:
                a = add();
                if (cur != Token.CLOSE) {
                    throw new MissingClosingBracketException(s.substring(Math.max(i - 10, 0), Math.min(i + 10, s.length())), i, i - Math.max(i - 10, 0));
                }
                next();
                break;
            case CONST:
                a = new Const<T>(val);
                next();
                break;
            case VARIABLE:
                a = new Variable<T>(name);
                next();
                break;
            case NEG:
                a = new CheckedNegate<T>(unar(), operation);
                break;
            default:
                throw new ParsingException("Incorrect expression");
        }
        isNumber();
        return a;
    }

    private enum Token {
        ADD,
        DIVIDE,
        CONST,
        MUL,
        SUBTRACT,
        NEG,
        OPEN,
        CLOSE,
        VARIABLE,
        SQR,
        ABS,
        MOD,
        END;
    }

    Token cur = Token.END;
    int i = 0;
    String s;
    String name;
    T val;
    int depth;

    private void skipSpace() {
        while (s.length() > i && Character.isWhitespace(s.charAt(i))) {
            i++;
        }
    }

    private void next() throws ParsingException {
        skipSpace();
        if (s.length() <= i) {
            cur = Token.END;
            return;
        }

        switch (s.charAt(i)) {
            case '+':
                cur = Token.ADD;
                break;
            case '*':
                cur = Token.MUL;
                break;
            case '/':
                cur = Token.DIVIDE;
                break;
            case '-':
                if (i + 1 == s.length()) {
                    throw new MissingOperandException(s.substring(Math.max(i - 10, 0), Math.min(i + 10, s.length())), i, i - Math.max(i - 10, 0));
                }
                if (cur == Token.VARIABLE || cur == Token.CONST || cur == Token.CLOSE) {
                    cur = Token.SUBTRACT;
                } else {
                    if (Character.isDigit(s.charAt(i + 1))) {
                        i++;
                        String number = parseNumber();
                        try {
                            val = operation.parseValue("-" + number);
                        } catch (NumberFormatException e) {
                            throw new IncorrectConstException(i);
                        }
                        i--;
                        cur = Token.CONST;
                    } else {
                        cur = Token.NEG;
                    }
                }
                break;
            case '(':
                cur = Token.OPEN;
                depth++;
                break;
            case ')':
                cur = Token.CLOSE;
                if (depth <= 0) {
                    throw new ExtraClosingBracketException(s.substring(Math.max(i - 10, 0), Math.min(i + 10, s.length())), i, i - Math.max(i - 10, 0));
                }
                depth--;
                break;
            case 'a':
                checkOperation("abs");
                cur = Token.ABS;
                break;
            case 's':
                checkOperation("square");
                cur = Token.SQR;
                break;
            case 'm':
                checkOperation("mod");
                cur = Token.MOD;
                break;
            default: {
                if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                    getNumber();
                    cur = Token.CONST;
                } else {
                    getName();
                    cur = Token.VARIABLE;
                }
            }
        }
        i++;
    }

    void checkOperation(String operation) throws UnknownOperationExceptions {
        int begin = i;
        if (i + operation.length() >= s.length()) {
            throw new UnknownOperationExceptions(s.substring(Math.max(i - 10, 0), Math.min(i + 10, s.length())), i, begin - Math.max(i - 10, 0), 0);
        }
        for (int j = 0; j < operation.length(); ++j) {
            if (s.charAt(i + j) != operation.charAt(j)) {
                throw new UnknownOperationExceptions(s.substring(Math.max(i - 10, 0), Math.min(i + 10, s.length())), i, begin - Math.max(i - 10, 0), j);
            }
        }
        i += operation.length() - 1;
        if (!Character.isWhitespace(s.charAt(i + 1)) && s.charAt(i + 1) != '-' && s.charAt(i + 1) != '(') {
            throw new UnknownOperationExceptions(s.substring(Math.max(i - 10, 0), Math.min(i + 10, s.length())), begin, begin - Math.max(i - 10, 0), operation.length());
        }
    }

    void isNumber() throws MissingOperationException {
        if (cur == Token.VARIABLE || cur == Token.CONST || cur == Token.OPEN) {
            throw new MissingOperationException(s.substring(Math.max(i - 10, 0), Math.min(i + 10, s.length())), i, i - Math.max(i - 10, 0));
        }
    }

    String parseNumber() {
        StringBuilder parsedNumber = new StringBuilder();
        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == 'e' || s.charAt(i) == '.')) {
            parsedNumber.append(s.charAt(i));
            i++;
        }
        return parsedNumber.toString();
    }


    void getNumber() throws IncorrectConstException {
        String number = parseNumber();
        try {
            val = operation.parseValue(number);
        } catch (NumberFormatException e) {
            throw new IncorrectConstException(i);
        }
        i--;
    }

    void getName() throws UnknownSymbolException {
        name = String.valueOf(s.charAt(i));
        if (!(name.contains("x") || name.contains("y") || name.contains("z"))) {
            throw new UnknownSymbolException(s.substring(Math.max(i - 10, 0), Math.min(i + 10, s.length())), i, i - Math.max(i - 10, 0));
        }
    }
}