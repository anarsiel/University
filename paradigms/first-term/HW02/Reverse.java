import java.util.Scanner;
import java.util.ArrayList;

public class Reverse {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		ArrayList<int[]> inputData = new ArrayList<int[]>();

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] splittedLine = line.split(" ");

			int[] array = new int[0];
			if (line.equals("")) {
				inputData.add(array);
				continue;
			};

			array = new int[splittedLine.length];

			for (int i = splittedLine.length - 1; i > -1; i--) {
				if (splittedLine[i].equals("")) continue;

				array[splittedLine.length - i - 1] = Integer.parseInt(splittedLine[i]);
			}
			inputData.add(array);
		}

		for (int i = inputData.size() - 1; i > -1; i--) {
			for (int j : inputData.get(i)) {
				System.out.print(j + " ");
			}
			System.out.println("");
		}
	}
}