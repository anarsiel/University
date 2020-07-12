//
//  Made by Anarsiel
//

import java.io.*;
import java.nio.charset.Charset;
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

        int nextByte = (int)buffer[currentIndexToReadFromBuffer];

        if ((nextByte >> 5) == -2) {
            byte[] byteArr = new byte[2];

            for (int i = 0; i < 2; i++) {
                byteArr[i] = buffer[currentIndexToReadFromBuffer++];
                if (currentIndexToReadFromBuffer == endOfBuffer) {
                    endOfBuffer = fillBuffer();
                    currentIndexToReadFromBuffer = 0;
                }
            }
            String answ = new String(byteArr, "UTF-8");
            return answ.charAt(0);
        } else if ((nextByte >> 4) == -2) {
            byte[] byteArr = new byte[3];

            for (int i = 0; i < 3; i++) {
                byteArr[i] = buffer[currentIndexToReadFromBuffer++];
                if (currentIndexToReadFromBuffer == endOfBuffer) {
                    endOfBuffer = fillBuffer();
                    currentIndexToReadFromBuffer = 0;
                }
            }

            String answ = new String(byteArr, "UTF-8");
            return answ.charAt(0);
        } else {
            char c = (char) buffer[currentIndexToReadFromBuffer++];
            if (currentIndexToReadFromBuffer == endOfBuffer) {
                endOfBuffer = fillBuffer();
                currentIndexToReadFromBuffer = 0;
            }
            return c;
        }
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

    void print(String s) throws IOException {
        outputStream.write(s.getBytes(Charset.forName("UTF-8")));
    } // prints String

    void print(int a) throws IOException {
        outputStream.write(Integer.toString(a).getBytes(Charset.forName("UTF-8")));
    } // prints Int

    void println(String s) throws IOException {
        outputStream.write(s.getBytes(Charset.forName("UTF-8")));

        String newLineCharacter = "\n";
        outputStream.write(newLineCharacter.getBytes(Charset.forName("UTF-8")));
    } // prints String and begins new line

    void println(int a) throws IOException {
        outputStream.write(Integer.toString(a).getBytes(Charset.forName("UTF-8")));

        String newLineCharacter = "\n";
        outputStream.write(newLineCharacter.getBytes(Charset.forName("UTF-8")));
    } // prints Int and begins new line
}
