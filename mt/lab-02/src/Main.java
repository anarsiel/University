import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        String text = "for (int x = 0; x < 10; x++)";
        ByteArrayInputStream bais = new ByteArrayInputStream(text.getBytes());
        BufferedInputStream bis = new BufferedInputStream(bais);

        Parser parser = new Parser();
        Node node = new Node("Empty");

        try {
            node = parser.parse(bis);
        } catch (ParseException e) {
            System.out.println(e.getMessage() + " Offset: " + e.getErrorOffset());
        }

        Drawer tree = new Drawer(node);
        tree.draw();
    }
}