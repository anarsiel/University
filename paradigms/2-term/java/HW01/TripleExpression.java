package expression;

import expression.exceptions.*;

public strictfp interface TripleExpression {
	int evaluate(int x, int y, int z) throws AllExceptions;
}