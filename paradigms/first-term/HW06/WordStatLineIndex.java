import java.io.*;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.ArrayList;

public class WordStatLineIndex {

    public static void main(String args[]) {
        try {
            Scanner scanner = new Scanner(args[0], args[1]);
//            Scanner scanner = new Scanner("input.txt", "output.txt");

            LinkedHashMap<String, Integer> hm = new LinkedHashMap<String, Integer>();
            ArrayList<String> wordsInCorrectOrder = new ArrayList<String>();
            LinkedHashMap<String, ArrayList<String>> wordIndexes = new LinkedHashMap<String, ArrayList<String>>();

            int cntWords = 0;
            int cntLines = 0;

            while (scanner.hasNext()) {
                String string = scanner.nextLine().toLowerCase();
                cntLines++;
                cntWords = 0;
                String[] parsedString = string.split("[^\\p{L}\\p{Pd}']");

                for (int i = 0; i < parsedString.length; i++) {
                    if (!parsedString[i].equals("")) {
                        cntWords++;
                        if (!hm.containsKey(parsedString[i])){
                            wordsInCorrectOrder.add(parsedString[i]);

                            wordIndexes.put(parsedString[i], new ArrayList<String>());
                        }
                        hm.put(parsedString[i], (hm.containsKey(parsedString[i]) ? hm.get(parsedString[i]) + 1 : 1));
                        wordIndexes.get(parsedString[i]).add(cntLines + ":" + cntWords);
                    }
                }
            }

            Collections.sort(wordsInCorrectOrder);
            for (String key : wordsInCorrectOrder) {
                scanner.print(key + " " + hm.get(key) + " ");

                int i = 0;
                for (String kek: wordIndexes.get(key)) {
                    i++;
                    scanner.print(kek);
                    if (i != wordIndexes.get(key).size())
                        scanner.print(" ");
                }
                scanner.println("");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File Not Found");
        } catch (IOException e) {
            System.out.println("Error: IOException");
        }  catch (IndexOutOfBoundsException e) {
            System.out.println("Error: No files in  input");
        }
    }
}

