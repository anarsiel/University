import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.ArrayList;

public class WordStatInput {

    public static void main(String args[]) {
        try {
            // Scanner scanner = new Scanner(new File(args[0]), "UTF-8");
            // PrintStream out = new PrintStream(new FileOutputStream(args[1]));
            Scanner scanner = new Scanner(new File("input.txt"), "utf8");
            PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
            
            LinkedHashMap <String, Integer> hm = new LinkedHashMap<String, Integer>();
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
            for (String key : wordsInCorrectOrder) {
                out.println(key + " " + hm.get(key));
            }

            // ArrayList<String> keyList = new ArrayList<String>(hm.keySet());
            // for (String i : keyList) {
            //     out.println(i + " " + hm.get(i));
            // }
            
        } catch (FileNotFoundException e) {
           // System.out.println("Error: file not found.");
           // System.exit(0);
        }

    }
}