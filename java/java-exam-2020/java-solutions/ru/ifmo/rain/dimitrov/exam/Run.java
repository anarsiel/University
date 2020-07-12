package ru.ifmo.rain.dimitrov.exam;

import java.util.LinkedList;
import java.util.Scanner;

// :NOTE: - Он должен был называться Main
public class Run {
    public static void main(final String[] args) {
        // :NOTE: * Использование конкретных типов коллекций объявлениях
        final LinkedList<RedirectInfo> linkedList;

        if (args.length != 1) {
            System.err.println("1 argument needed: filename");
            return;
        }

        // :NOTE: - Захаркоженый путь
        final String filename = "ru/ifmo/rain/dimitrov/exam/" + args[0];
        try {
            linkedList = TableParser.getRedirectInfo(filename);
            final Proxy proxy = new Proxy(linkedList);
            proxy.start();

            System.out.println("Text something to stop server");
            final Scanner in = new Scanner(System.in);
            final String line = in.nextLine();
            proxy.close();
        } catch (final InternalException e) {
            System.err.println(e.getMessage());
        }
    }
}
