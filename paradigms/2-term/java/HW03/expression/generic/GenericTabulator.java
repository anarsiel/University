package expression.generic;


import expression.TripleExpression;
import expression.exceptions.ParsingException;
import expression.exceptions.UnknownModeException;
import expression.operations.*;
import expression.parser.ExpressionParser;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private static final Map<String, Operation> operations = Map.of(
            "i", new IntegerOperation(false),
            "d", new DoubleOperation(),
            "u", new IntegerOperation(true),
            "bi", new BigIntegerOperation(),
            "b", new ByteOperation(),
            "f", new FloatOperation()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException, UnknownModeException {
        Operation<?> operation = operations.get(mode);
        if (operation == null) {
            throw new UnknownModeException(mode);
        }
        return fillTable(operation, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] fillTable(Operation<T> op, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParsingException {
        Object[][][] mas = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        ExpressionParser<T> parser = new ExpressionParser<>(op);
        TripleExpression<T> expr = parser.parse(expression);
        for (int i = 0; i <= x2 - x1; ++i) {
            for (int j = 0; j <= y2 - y1; ++j) {
                for (int k = 0; k <= z2 - z1; ++k) {
                    try {
                        mas[i][j][k] = expr.evaluate(valueOf(x1 + i, op), valueOf(y1 + j, op), valueOf(z1 + k, op));
                    } catch (Exception e) {
//                        mas[i][j][k] = null;
                    }
                }
            }
        }
        return mas;
    }

    private <T> T valueOf(int value, Operation<T> op) {
        return op.parseValue(Integer.toString(value));
    }
}
