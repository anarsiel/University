package expression.parser;

import expression.exceptions.WrongOperandException;
import expression.exceptions.WrongScopesException;
import expression.exceptions.WrongSymbolException;
import expression.*;

public class ExpressionParser implements Parser{
    private String expression = "";
    private int currentIndex = 0;

    private enum Token {Variable, Number, Add, Sub, Mul,
                        Div, LeftBracket, RightBracket, TheEnd, Count, UnaryMinus}


    private Token currentToken;
    private String variableName;
    private int value;

    private boolean isOperation() {
        return (currentToken == Token.Add) || (currentToken == Token.Sub)
                || (currentToken == Token.Mul) || (currentToken == Token.Div)
                || (currentToken == Token.Count); // || (currentToken == Token.UnaryMinus);
    }

    private void getNextToken() throws WrongSymbolException, WrongOperandException {
        while (currentIndex < expression.length() && expression.charAt(currentIndex) == ' ') {
            currentIndex++;
        }

        if (currentIndex >= expression.length()) {
            currentToken = Token.TheEnd;
            return;
        }

        char currentChar = expression.charAt(currentIndex);

        switch (currentChar) {
            case '+':
                if (isOperation()) {
                    throw new WrongOperandException();
                }

                currentToken = Token.Add;
                break;

            case '-':
                if (currentToken == Token.Number || currentToken == Token.Variable || currentToken == Token.RightBracket)
                    currentToken = Token.Sub;
                else {
                    currentToken = Token.UnaryMinus;
                    currentIndex++;
                    currentChar = expression.charAt(currentIndex);

                    if ('0' <= currentChar && currentChar <= '9') {
                        int numberEnding = currentIndex;

                        value = 0;

                        int cN = 0;

                        while (numberEnding < expression.length() && '0' <= expression.charAt(numberEnding) && expression.charAt(numberEnding) <= '9') {
                            value *= 10;
                            value += (expression.charAt(numberEnding) - '0');
                            numberEnding++;
                            cN++;
                            if (cN == 1) {
                                value *= -1;
                            }
                        }
                        currentToken = Token.Number;
                        currentIndex = numberEnding - 1;

                        value *= -1;
                    } else {
                        currentIndex--;
                    }
                }
                break;

            case '*':
                if (isOperation()) {
                    throw new WrongOperandException();
                }

                currentToken = Token.Mul;
                break;

            case '/':
                if (isOperation()) {
                    throw new WrongOperandException();
                }

                currentToken = Token.Div;
                break;

            case '(':

                currentToken = Token.LeftBracket;
                break;

            case ')':
                currentToken = Token.RightBracket;
                break;

//            case '&':
//                currentToken = Token.And;
//                break;
//
//            case '|':
//                currentToken = Token.Or;
//                break;

//            case '^':
//                currentToken = Token.Xor;
//                break;
//
//            case '~':
//                currentToken = Token.Not;
//                break;

            default:
                if ('0' <= currentChar && currentChar <= '9') {
                    int numberEnding = currentIndex;

                    value = 0;

                    while (numberEnding < expression.length() && '0' <= expression.charAt(numberEnding) && expression.charAt(numberEnding) <= '9') {
                        value *= 10;
                        value += (expression.charAt(numberEnding) - '0');
                        numberEnding++;
                    }
                    currentToken = Token.Number;
                    currentIndex = numberEnding - 1;
                } else if ('x' <= currentChar && currentChar <= 'z') {
                    variableName = String.valueOf(currentChar);
                    currentToken = Token.Variable;
                } else if (expression.substring(currentIndex, currentIndex + 5).equals("count")) {
                    currentToken = Token.Count;
                    currentIndex += 4;
                } else {
                    throw new WrongSymbolException();
                }
        }
        currentIndex++;
    }

//    private TripleExpression or() {
//        TripleExpression res = xor();
//        do {
//            switch (currentToken) {
//                case Or:
//                    res = new Or(res, xor());
//                    break;
//                default:
//                    return res;
//            }
//        } while (true);

//    }

    private TripleExpression unary() throws WrongSymbolException, WrongOperandException {
        getNextToken();
        TripleExpression result = null;

        switch (currentToken) {
            case Number:
                result = new Const(value);
                getNextToken();
                break;

            case UnaryMinus:
                result = new CheckedNegate(unary());
                break;

            case Count:
                result = new Count(unary());
                break;

            case LeftBracket:
                result = addAndSub();
                getNextToken();
                break;

            case Variable:
                result = new Variable(variableName);
                getNextToken();
                break;

            default:
                result = new Const(0);
                break;
        }
        return result;
    }

    private TripleExpression mulDivMod() throws WrongSymbolException, WrongOperandException {
        TripleExpression res = unary();

        do {
            switch (currentToken) {
                case Mul:
                    res = new CheckedMultiply(res, unary());
                    break;
                case Div:
                    res = new CheckedDivide(res, unary());
                    break;
                default:
                    return res;
            }
        } while (true);
    }

    private TripleExpression addAndSub() throws WrongSymbolException, WrongOperandException {
        TripleExpression res = mulDivMod();

        do {
            switch (currentToken) {
                case Add:
                    res = new CheckedAdd(res, mulDivMod());
                    break;
                case Sub:
                    res = new CheckedSubtract(res, mulDivMod());
                    break;
                default:
                    return res;
            }
        } while (true);
    }

//    private TripleExpression and() {
//        TripleExpression res = addAndSub();
//
//        do {
//            switch (currentToken) {
//                case And:
//                    res = new CheckedAnd(res, addAndSub());
//                    break;
//                default:
//                    return res;
//            }
//        } while (true);
//    }

//    private TripleExpression xor() {
//        TripleExpression res = and();
//
//        do {
//            switch (currentToken) {
//                case Xor:
//                    res = new Xor(res, and());
//                    break;
//                default:
//                    return res;
//            }
//        } while (true);
//    }

    @Override
    public TripleExpression parse(String expression) throws WrongScopesException, WrongSymbolException, WrongOperandException {
        currentIndex = 0;
        this.expression = expression;

        int balance = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                balance++;
            } else if (expression.charAt(i) == ')') {
                balance--;
            }

            if (balance < 0) {
                throw new WrongScopesException();
            }
        }

        if (balance != 0) {
            throw new WrongScopesException();
        }

        return addAndSub();
    }
}