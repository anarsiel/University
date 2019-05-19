import java.math.BigInteger;

public class SumBigInteger {
	public static void main (String[] args) {
		BigInteger sum = BigInteger.valueOf(0);

		for (int i = 0; i < args.length; i++) {
			String currentString = args[i];
			StringBuilder currentNumber = new StringBuilder();

			for (int j = 0; j < currentString.length(); j++) {
				char currentChar = currentString.charAt(j); 

				if (Character.isDigit(currentChar) || currentChar == '-') {
					currentNumber.append(currentChar);
				} else {
					if (currentNumber.length() > 0) {
						sum = sum.add(new BigInteger(currentNumber.toString()));
						currentNumber = new StringBuilder();
					}
				}
			}

			if (currentNumber.length() > 0) {
				sum = sum.add(new BigInteger(currentNumber.toString()));
				currentNumber = new StringBuilder();
			}
		}

		System.out.println(sum);
	}
}