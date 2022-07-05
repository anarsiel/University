import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) throws Exception {
        String fibonacciF = "fibonacci :: Integer -> Integer\n" +
                "fibonacci (x | x < 0) = Error()\n" +
                "fibonacci (x | x <= 1) = 1\n" +
                "fibonacci x = fibonacci(x - 2) + fibonacci(x - 1)\n";

        String factorialF = "myFactorial :: Integer -> Integer\n" +
                "myFactorial (x | x < 0) = Error()\n" +
                "myFactorial (x | x == 0) = 1\n" +
                "myFactorial x = 1! + 2!\n";

        String sum2Even = "sum2Even :: Integer -> Integer -> Integer\n" +
                "sum2Even (a | a % 2 != 1), (b | b % 2 != 1) = Error()\n" +
                "sum2Even a, (b | b! != 1) = Error()\n" +
                "sum2Even a, b = a + b * b - a * a + b\n";

        String mainF = "mainF :: Void\n" +
                "mainF = print(abs(5))\n";

        StringJoiner joiner = new StringJoiner("\n\n\n");
        joiner.add(sum2Even).add(mainF).add(factorialF);
        HelloLexer lexer = new HelloLexer(CharStreams.fromString(joiner.toString()));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokens);

        String fileName = "test.cpp";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(String.valueOf(parser.all_file().val));

        writer.close();
    }
}