import java.io.InputStream;
import java.text.ParseException;

class Parser {
    private LexicalAnalyzer lex;
    private int counter;
    private String curValue;
    private static final String SPLITER = ", ";

    private void tokenChecker(Token expectedToken) throws ParseException {
        if (lex.getCurrentToken() != expectedToken) {
            throw new ParseException(
                    "Token " + expectedToken.getValue() + " expected. Got: " + lex.getCurrentToken() + ".",
                    lex.getCurrentPos()
            );
        }
    }

    private void unexpectedTokenError() throws ParseException {
        throw new ParseException(
                "Unexpected token " + lex.getCurrentToken().getValue() + ".",
                lex.getCurrentPos());
    }

    private Node Signature() throws ParseException {
        if (lex.getCurrentToken() == Token.FOR) {

            lex.nextToken(); // (
            tokenChecker(Token.LEFT_PAREN);

            curValue = lex.nextToken(); // A
            Node a = A(); // )

            curValue = lex.nextToken(); // B
            Node b = B(); // )

            lex.nextToken();
            tokenChecker(Token.SEMICOLON);

            curValue = lex.nextToken(); // C
            Node c = C(); // )

            lex.nextToken();
            tokenChecker(Token.RIGHT_PAREN);

            lex.nextToken();
            tokenChecker(Token.END);

            return new Node("S" + SPLITER + Integer.toString(++counter),
                    new Node("for" + ", " + Integer.toString(++counter)),
                    new Node("(" + ", " + Integer.toString(++counter)),
                    a,
                    new Node(";" + ", " + Integer.toString(++counter)),
                    b,
                    new Node(";" + ", " + Integer.toString(++counter)),
                    c,
                    new Node(")" + ", " + Integer.toString(++counter)));
        } else {
            unexpectedTokenError();
            return null;
        }
    }

    private Node A() throws ParseException {
        switch (lex.getCurrentToken()) {
            case TYPE: {
                tokenChecker(Token.TYPE);
                String type = curValue + SPLITER + "type" + SPLITER;

                curValue = lex.nextToken();
                tokenChecker(Token.NAME);
                String name = curValue + SPLITER + "name" + SPLITER;

                lex.nextToken();
                tokenChecker(Token.EQUALLY);

                curValue = lex.nextToken();
                tokenChecker(Token.VALUE);
                String value = curValue + SPLITER + "value" + SPLITER;

                lex.nextToken();
                try {
                    tokenChecker(Token.COMMA);
                } catch (ParseException e) {
                    tokenChecker(Token.SEMICOLON);
                    return new Node("A" + SPLITER + ++counter,
                            new Node(type + ++counter),
                            new Node(name + ++counter),
                            new Node("equally" + SPLITER + ++counter),
                            new Node( value + ++counter));
                }

                curValue = lex.nextToken();
                tokenChecker(Token.NAME);
                String name2 = curValue + SPLITER + "name" + SPLITER;

                lex.nextToken();
                tokenChecker(Token.EQUALLY);

                curValue = lex.nextToken();
                tokenChecker(Token.VALUE);
                String value2 = curValue + SPLITER + "value" + SPLITER;

                curValue = lex.nextToken();
                tokenChecker(Token.SEMICOLON);

                return new Node("A" + SPLITER + ++counter,
                        new Node(type + ++counter),
                        new Node(name + ++counter),
                        new Node("equally" + SPLITER + ++counter),
                        new Node( value + ++counter),
                        new Node(name2 + ++counter),
                        new Node(value2 + ++counter)
                );

            }
            default: {
                unexpectedTokenError();
                return null;
            }
        }
    }

    private Node B() throws ParseException {
        switch (lex.getCurrentToken()) {
            case NAME: {
                tokenChecker(Token.NAME);
                String name = curValue + SPLITER + "name" + SPLITER;

                curValue = lex.nextToken();
                tokenChecker(Token.COMPARE);
                String cmp = curValue + SPLITER + "cmp" + SPLITER;


                curValue = lex.nextToken();
                tokenChecker(Token.VALUE);
                String value = curValue + SPLITER + "value" + SPLITER;

                return new Node("B" + ", " + Integer.toString(++counter),
                        new Node(name + Integer.toString(++counter)),
                        new Node(cmp + Integer.toString(++counter)),
                        new Node(value + Integer.toString(++counter)));
            }
            default: {
                unexpectedTokenError();
                return null;
            }
        }
    }

    private Node C() throws ParseException {
        switch (lex.getCurrentToken()) {
            case NAME: {
                tokenChecker(Token.NAME);
                String name = curValue + SPLITER + "name" + SPLITER;

                curValue = lex.nextToken();
                Node cPrime = Cprime();

                return new Node("C" + SPLITER + Integer.toString(++counter),
                        new Node( name + Integer.toString(++counter)),
                        cPrime);
            }
            case OPER: {
                tokenChecker(Token.OPER);
                Node cPrime = Cprime();

                curValue = lex.nextToken();
                tokenChecker(Token.NAME);
                String name = curValue + SPLITER + "name" + SPLITER;

                return new Node("C" + SPLITER + ++counter,
                        cPrime,
                        new Node( name + ++counter));
            }
            default: {
                unexpectedTokenError();
                return null;
            }
        }
    }

    private Node Cprime() throws ParseException {
        if (lex.getCurrentToken() == Token.OPER) {
            tokenChecker(Token.OPER);
            String oper = curValue + SPLITER + "oper" + SPLITER;

            return new Node("Cprime" + ", " + Integer.toString(++counter),
                    new Node(oper + Integer.toString(++counter)));
        }
        unexpectedTokenError();
        return null;
    }

    Node parse(InputStream is) throws ParseException {
        lex = new LexicalAnalyzer(is);
        return Signature();
    }
}