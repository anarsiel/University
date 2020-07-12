import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SumFile {

    public static void main(String args[]) {
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            PrintStream out = new PrintStream(new FileOutputStream(args[1]));
            // Scanner scanner = new Scanner(new File("test1.in"), "utf8");
            // PrintStream out = new PrintStream(new FileOutputStream("test1.out"));

            int sum = 0;
            while (scanner.hasNext()) {
                try {
                    sum += scanner.nextInt();
                } catch (InputMismatchException e) {
                   // System.out.println("Error: input is incorrect");
                    String s = scanner.next();
                }
            }
            
            out.println(sum);
            out.close();
            scanner.close();
        } catch (FileNotFoundException e) {
           // System.out.println("Error: file not found.");
           // System.exit(0);
        } catch (IndexOutOfBoundsException e) {
            // System.out.println("Error: input/output file is not defined");
        }

    }
}