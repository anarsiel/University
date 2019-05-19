import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SumHexFile {

    public static void main(String args[]) {
        try {
            // Scanner scanner = new Scanner(new File(args[0]));
            // PrintStream out = new PrintStream(new FileOutputStream(args[1]));
            Scanner scanner = new Scanner(new File("input.txt"), "utf8");
            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));

            int sum = 0;
            while (scanner.hasNext()) {
                try {
                    sum += scanner.nextInt(16);
                } catch (InputMismatchException e) {
                    String s = scanner.next();
                    // System.out.println("Error: input is incorrect");
                    // System.exit(0);
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