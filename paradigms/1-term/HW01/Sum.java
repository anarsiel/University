public class Sum {
	public static void main (String[] args) {
		int sum = 0;

		for (int i = 0; i < args.length; i++) {
			String currentString = args[i];
			String currentNumber = "";

			for (int j = 0; j < currentString.length(); j++) {
				char currentChar = currentString.charAt(j); 

				if (Character.isDigit(currentChar) || currentChar == '-') {
					currentNumber += currentChar;
				} else {
					if (currentNumber.length() > 0) {
						sum += Integer.parseInt(currentNumber);
						currentNumber = "";
					}
				}
			}

			if (currentNumber.length() > 0) {
				sum += Integer.parseInt(currentNumber);
				currentNumber = "";
			}
		}

		System.out.println(sum);
	}
}