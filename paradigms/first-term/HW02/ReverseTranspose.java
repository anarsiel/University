import java.util.Scanner;
import java.util.ArrayList;

public class ReverseTranspose {
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

			for (int i = 0; i < splittedLine.length; i++) {
				if (splittedLine[i].equals("")) continue;

				array[i] = Integer.parseInt(splittedLine[i]);
			}
			inputData.add(array);
		}

		int n = inputData.size();
		int m = 0;

		for (int i = 0; i < n; i++) {
			m = Math.max(m, inputData.get(i).length);
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i < inputData.get(j).length) {
					System.out.print(inputData.get(j)[i] + " ");
				}
			}
			System.out.println("");
 		}
		scanner.close();
	}
}