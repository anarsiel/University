import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;

public class Main {
    public static void main(String[] args) throws Exception {
        HelloLexer lexer = new HelloLexer(CharStreams.fromString(
                "pow :: Integer -> Integer -> Integer\n" +
                        "pow a, (n | n == 0) = 1\n" +
                        "pow a, (n | n % 2 == 1) = pow(a, n - 1) * a\n" +
                        "pow a, n = pow(pow(a, n / 2), 2) "
        ));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        com.ifmo.evgfed.HelloParser parser = new com.ifmo.evgfed.HelloParser(tokens);
        System.out.println(parser.code().val);
    }
}