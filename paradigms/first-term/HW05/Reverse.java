import java.io.*;
import java.util.ArrayList;

public class Reverse {
    public static String[] split(String s, char c) {
        int n = s.length();
        ArrayList<String> out = new ArrayList<String>();
        int curStringLen = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != c) {
                curStringLen++;
            } else {
                out.add(s.substring(i - curStringLen, i));
                curStringLen = 0;
            }
        }
        out.add(s.substring(n - curStringLen, n));
        String[] outt = new String[out.size()];

        for (int i = 0; i < out.size(); i++)
            outt[i] = out.get(i);
        return outt;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner("input.txt");
        ArrayList<String[]> inputData = new ArrayList<String[]>();

        while (scanner.hasNext()) {
            inputData.add(split(scanner.nextLine(), ' '));
        }

        for (int i = inputData.size() - 1; i > -1; i--) {
            for (int j = inputData.get(i).length - 1; j > -1; j--) {
                System.out.print(inputData.get(i)[j] + " ");
            }
            System.out.println();
        }
    }
}