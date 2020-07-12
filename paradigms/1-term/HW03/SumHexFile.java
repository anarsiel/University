import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class SumHexFile {   
    public static void main(String args[]) {
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            // Scanner scanner = new Scanner(new File("input.txt"), "utf8");
            // PrintStream out = new PrintStream(new FileOutputStream("output.txt"));

            int sum = 0;
            while (scanner.hasNext()) {
                String s = scanner.next();
                s = s.toLowerCase();
                for (int i = 0; i < s.length(); i++) {
                    if (!(s.charAt(i) >= 'a' && s.charAt(i) <= 'f' && i >= 2 || s.charAt(i) == 'x' && i == 1 || s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                        System.out.println("Error: input is incorrect");
                        scanner.close();
                        return;
                    }
                }
                
                sum += (s.length() >= 2 && s.startsWith("0x") ? Integer.parseUnsignedInt(s.substring(2), 16) : Integer.parseInt(s, 10));
            }

            try {
                PrintStream out = new PrintStream(new FileOutputStream(args[1]));
                out.println(sum);
                out.close();
                scanner.close();
            } catch (FileNotFoundException e) {
                // System.out.println("Error: file not found.");
                scanner.close();
                return;
                // System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found.");
            return;
            // System.exit(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: input/output file is not defined");
            return;
        }

    }
}