//
//  Made by Anarsiel
//

import java.io.*;
import java.util.ArrayList;

public class Scanner {
    /*
     * Fields
     */
    private InputStream inputStream;
    private OutputStream outputStream;
    private byte[] buffer = new byte[256];
    private int currentIndexToReadFromBuffer = 256;
    private int endOfBuffer = 256;

    /*
     * Constructors
     */
    public Scanner(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    Scanner() {
        this.inputStream = new DataInputStream(System.in);
    }

    Scanner(String IFile) throws FileNotFoundException {
        this.inputStream = new FileInputStream(IFile);
    }

    public Scanner(String IFile, String OFile) throws FileNotFoundException {
        this.inputStream = new FileInputStream(IFile);
        this.outputStream = new FileOutputStream(OFile);
    }


    /*
     * Methods
     */
    private int fillBuffer() throws IOException {
        int kek = inputStream.read(buffer);
        while (kek == 0) {
            kek = inputStream.read(buffer);
        }
        return kek;
    } // full-fills Buffer

    boolean hasNext() throws IOException {
        return currentIndexToReadFromBuffer < endOfBuffer || inputStream.available() > 0;
    } // returns True if there is something left in Input

    int nextInt() throws IOException {
        int answer = 0;
        boolean integerIsBelowZero = false;

        while (hasNext()) {
            char c = nextChar();
            if (!(c >= '0' && c <= '9' || c == '-')) {
                break;
            }

            if (c != '-') {
                int number = (c - '0');
                answer *= 10;
                answer += number;
            } else {
                integerIsBelowZero = true;
            }
        }
        return (integerIsBelowZero ? -answer : answer);
    } // returns next Integer from Input

    char nextChar() throws IOException {
        if (currentIndexToReadFromBuffer == endOfBuffer) {
            endOfBuffer = fillBuffer();
            currentIndexToReadFromBuffer = 0;
        }
        return (char) buffer[currentIndexToReadFromBuffer++];
    } // returns next char from Input

    String nextLine() throws IOException {
        ArrayList<Character> line = new ArrayList<Character>();
        char c;

        while (hasNext()) {
            c = nextChar();
            if (c == '\n') {
                break;
            }
            line.add(c);
        }
        char[] charLine = new char[line.size()];

        for (int i = 0; i < line.size(); i++) {
            charLine[i] = line.get(i);
        }
        String s = new String(charLine);
        return s;
    } // returns next Line from Input
}
