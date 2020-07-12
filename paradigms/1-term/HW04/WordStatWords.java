import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.ArrayList;

public class WordStatWords {

    public static void main(String args[]) {
        try {
            Scanner scanner = new Scanner(new File(args[0]), "UTF-8");
            // Scanner scanner = new Scanner(new File("input.txt"), "utf8");
            // PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            
            LinkedHashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
            ArrayList<String> wordsInCorrectOrder = new ArrayList<String>();
            
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine().toLowerCase();
                String[] parsedString = string.split("[^\\p{L}\\p{Pd}']");

                for (int i = 0; i < parsedString.length; i++) {
                    if (!parsedString[i].equals("")) {
                        if (!hm.containsKey(parsedString[i])){
                            wordsInCorrectOrder.add(parsedString[i]);
                        }
                        hm.put(parsedString[i], (hm.containsKey(parsedString[i]) ? hm.get(parsedString[i]) + 1 : 1));
                    }
                }
            }

            try {
                // PrintStream out = new PrintStream(new FileOutputStream(args[1], "UTF-8"));
                PrintStream out = new PrintStream(args[1], "UTF-8");
                Collections.sort(wordsInCorrectOrder);
                for (String key : wordsInCorrectOrder) {
                    out.println(key + " " + hm.get(key));
                }
                out.close();
            } catch (IOException e) {
                System.out.println("You lost: output file in UTF-8 encoding not found");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You lost: output file not found");
            }
            scanner.close();
            
        } catch (IOException e) {
           System.out.println("You lost: input file in UTF-8 encoding not found");
        } catch (IndexOutOfBoundsException e) {
           System.out.println("You lost: input file encoding not found");
        }

    }
}

