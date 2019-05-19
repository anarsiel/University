package expression.parser;

import expression.exceptions.WrongOperandException;
import expression.exceptions.WrongScopesException;
import expression.exceptions.WrongSymbolException;
import expression.TripleExpression;

public interface Parser {
    TripleExpression parse(String expression) throws WrongScopesException, WrongSymbolException, WrongOperandException;
}