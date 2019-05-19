package md2html;

import java.io.*;

public class MarkdownSource implements AutoCloseable {
    private BufferedReader fin;
    private BufferedWriter fout;
    private StringBuilder buffer;

    void addToOutputBuffer(String s) {
        buffer.append(s);
    }

    String readParagraph() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        String cur;

        while ((cur = fin.readLine()) != null && (cur.equals(""))) {}

        if (cur != null)
            stringBuilder.append(cur).append('\n');

        while ((cur = fin.readLine()) != null && (!cur.equals(""))) {
            stringBuilder.append(cur).append('\n');
        }

        if (stringBuilder.length() > 0)
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    void writeBufferToFile() throws IOException {
        fout.write(buffer.toString());
        buffer = new StringBuilder();
    }

    void closeInputOutput() throws IOException {
        fin.close();
        fout.close();
    }

    MarkdownSource(final String inputFileName, String outputFileName) throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream(inputFileName), "UTF-8");
        fin = new BufferedReader(reader);

        Writer writer = new OutputStreamWriter(new FileOutputStream(outputFileName), "UTF-8");
        fout = new BufferedWriter(writer);

        buffer = new StringBuilder();
//        try (Reader reader = new InputStreamReader(new FileInputStream(inputFileName), "UTF-8")) {
//            fin = new BufferedReader(reader);
//
//            try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputFileName), "UTF-8")) {
//                fout = new BufferedWriter(writer);
//            } catch (IOException e) {
//                fin.close();
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        buffer = new StringBuilder();
    }

    @Override
    public void close() throws Exception {
        fin.close();
        fout.close();
    }
}
