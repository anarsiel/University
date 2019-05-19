package md2html;

import java.io.IOException;

public class Md2Html {

    public static void main(String[] args) throws IOException {
//        try(Md2HtmlParser mkdownparser = new Md2HtmlParser("input.txt", "output.txt")) {
        try(Md2HtmlParser mkdownparser = new Md2HtmlParser(args[0], args[1])) {
            mkdownparser.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}