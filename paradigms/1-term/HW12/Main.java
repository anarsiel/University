import expression.exceptions.*;
import expression.parser.*;

import java.util.*;

public class Main{
    public static void main(String[] args) throws WrongOperandException, WrongScopesException, WrongSymbolException, OverflowException, DivisionByZeroException {
        ExpressionParser ans = new ExpressionParser();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int x = sc.nextInt();
//        int y = sc.nextInt();
//        int z = sc.nextInt();
        int y = 0;
        int z = 0;
        System.out.println(ans.parse(str).evaluate(x, y, z));
    }
}

// -(-(-          -5 + 16   *x*y) + 1 * z) -(((-11)))

//  1000000*x*x*x*x*x/(x-1)