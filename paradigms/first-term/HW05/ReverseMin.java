import java.io.IOException;
import java.util.ArrayList;

public class ReverseMin {
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

    private static final int mxInt = 2147483647;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner();
        ArrayList<String[]> inputData = new ArrayList<String[]>();

        while (scanner.hasNext()) {
            inputData.add(split(scanner.nextLine(), ' '));
        }

        ArrayList<ArrayList<Integer>> minLineColumn;

        int n = inputData.size();
        int m = 0;
        for (int i = 0; i < n; i++) {
            m = Math.max(m, inputData.get(i).length);
        }

        int[] minLineArr = new int[n]; // min in line[i]
        int[] minColumnArr = new int[m]; // min in column[j]

        for (int i = 0; i < n; i++) {
            minLineArr[i] = mxInt;
        }

        for (int j = 0; j < m; j++) {
            minColumnArr[j] = mxInt;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j < inputData.get(i).length && !inputData.get(i)[j].equals(""))
                    minLineArr[i] = Math.min(minLineArr[i], Integer.parseInt(inputData.get(i)[j]));

                minColumnArr[j] = Math.min(minColumnArr[j],
                        (j < inputData.get(i).length && !inputData.get(i)[j].equals("") ? Integer.parseInt(inputData.get(i)[j]) : minColumnArr[j]));
                if (j >= inputData.get(i).length) break;
            }
        }

        for (int i = 0; i < inputData.size(); i++) {
            for (int j = 0; j < inputData.get(i).length; j++) {
                if (!inputData.get(i)[j].equals(""))
                    System.out.print(Math.min(minColumnArr[j], minLineArr[i]) + " ");
            }
            System.out.println();
        }
    }
}
