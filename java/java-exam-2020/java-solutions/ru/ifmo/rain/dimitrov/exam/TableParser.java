package ru.ifmo.rain.dimitrov.exam;

import java.io.*;
import java.util.LinkedList;

public class TableParser {

    static LinkedList<RedirectInfo> getRedirectInfo(final String filename) throws InternalException {
        final BufferedReader reader;

        final LinkedList<RedirectInfo> linkedList = new LinkedList<>();
        try {
            // :NOTE: - Текстовый ввод-вывод без указания кодировки
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                final String[] splited = line.split("\\s+");

                if (splited.length != 3) {
                    System.err.println("Wrong line format: " + line);
                    line = reader.readLine();
                    continue;
                }

                try {
                    // :NOTE: - NumberFormatException
                    linkedList.add(new RedirectInfo(
                            Integer.parseInt(splited[0]), splited[1], Integer.parseInt(splited[2]))
                    );
                } catch (final Exception e) {
                    System.err.println("Ports must be int");
                }

                line = reader.readLine();
            }
            // :NOTE: - Утечка ресурсов
            reader.close();
        } catch (final IOException e) {
            // :NOTE: - Игнорирование исключений
            throw new InternalException("Wrong filename: " + filename, e);
        }

        return linkedList;
    }


}
