package expression.parser;

import expression.*;
import expression.exceptions.*;

public class ExpressionParser implements Parser {
	enum Type {LOW, HIGH, ADD, SUBTRACT, MULTIPLY, DIVIDE, CONST, VARIABLE, OPEN_BRACKET, CLOSE_BRACKET, START, END}

	private String str;
	private Type currentType;
	private int currentIndex;
	private int currentConst;
	private int currentBalance;
	private String currentVariable;

	public TripleExpression parse(String s) throws AllExceptions{
		str = s;
		currentIndex = 0;
		currentBalance = 0;
		currentType = Type.START;
		return parseAddSubtract();
	}

	private TripleExpression parseAddSubtract() throws AllExceptions{
		TripleExpression currentResult = parseMultiplyDivide();
		while (true) {
			switch (currentType) {
				case ADD:
					currentResult = new CheckedAdd(currentResult, parseMultiplyDivide());
					break;
				case SUBTRACT:
					currentResult = new CheckedSubtract(currentResult, parseMultiplyDivide());
					break;
				default:
					return currentResult;
			}
		}
	}

	private TripleExpression parseMultiplyDivide() throws AllExceptions{
		TripleExpression currentResult = parseUnaryOperations();
		while (true) {
			switch (currentType) {
				case MULTIPLY:
					currentResult = new CheckedMultiply(currentResult, parseUnaryOperations());
					break;
				case DIVIDE:
					currentResult = new CheckedDivide(currentResult, parseUnaryOperations());
					break;
				default:
					return currentResult;
			}
		}
	}

	private TripleExpression parseUnaryOperations() throws AllExceptions{
		TripleExpression currentResult = null;
		next();
		switch (currentType) {
			case SUBTRACT:
				currentResult = new CheckedNegate(parseUnaryOperations());
				break;
			case CONST:
				currentResult = new Const(currentConst);
				next();
				break;
			case VARIABLE:
				currentResult = new Variable(currentVariable);
				next();
				break;
			case HIGH:
				currentResult = new CheckedHigh(parseUnaryOperations());
				break;
			case LOW:
				currentResult = new CheckedLow(parseUnaryOperations());
				break;
			case OPEN_BRACKET:
				currentResult = parseAddSubtract();
				next();
				break;
		}
		return currentResult;
	}

	private void nextNotWhiteSpace() {
		while (currentIndex < str.length() && Character.isWhitespace(str.charAt(currentIndex))) {
			currentIndex++;
		}
	}

	private boolean cantBeInNameOfFunction(int index) {
		if (str.charAt(index) >= 'a' && str.charAt(index) <= 'z') {
			return true;
		}
		if (str.charAt(index) >= '0' && str.charAt(index) <= '9') {
			return true;
		}
		return false;
	}

	private void checkOnException(String name, int index) throws AllExceptions{
		if (name.equals("low") || name.equals("high")) {
			if (currentType == Type.CLOSE_BRACKET || currentType == Type.VARIABLE || currentType == Type.CONST) {
				throw new MissingOperatorException(currentIndex);
			}
			if (currentIndex + name.length() < str.length() && cantBeInNameOfFunction(currentIndex + name.length())) {
				throw new UsingWrongOperatorException(currentIndex);
			}
			currentIndex += name.length() - 1;
			return;
		}
		if (currentType != Type.CONST && currentType != Type.VARIABLE && currentType != Type.CLOSE_BRACKET) {
			throw new IncorrectUsingOfOperatorException(currentIndex, name);
		}
	}

	private int checkOnNumber() throws AllExceptions{
		int i = currentIndex;
		if (str.charAt(i) == '-') {
			if (currentType == Type.CONST || currentType == Type.VARIABLE || currentType == Type.CLOSE_BRACKET) {
					return 0;
				} 
			i++;
			nextNotWhiteSpace();
		}
		int cnt = 0;
		while (i < str.length() && Character.isDigit(str.charAt(i))) {
			i++;
			cnt++;
		}
		if (cnt == 0) {
			return 0;
		}
		try {
			currentConst = Integer.parseInt(str.substring(currentIndex, i));
		} catch (NumberFormatException e) {
			throw new OverflowException();
		}
		currentType = Type.CONST;
		return (i - currentIndex);
	}

	private void next() throws AllExceptions{
		nextNotWhiteSpace();
		if (currentIndex >= str.length()) {
			if (currentType != Type.CONST && currentType != Type.VARIABLE && currentType != Type.CLOSE_BRACKET) {
				throw new WrongEndOfExpressionException();
			}
			currentType = Type.END;
			return;
		}
		int lengthOfPossibleNumber = checkOnNumber();
		if (lengthOfPossibleNumber > 0) {																
			currentIndex += lengthOfPossibleNumber;
			return;
		}
		switch (str.charAt(currentIndex)) {
			case '+':
				if (currentType != Type.CONST && currentType != Type.VARIABLE && currentType != Type.CLOSE_BRACKET) {
					throw new IncorrectUsingOfOperatorException(currentIndex, "add");
				}
				currentType = Type.ADD;
				break;
			case '-':
				if (currentType == Type.CONST || currentType == Type.VARIABLE || currentType == Type.CLOSE_BRACKET) {
					currentType = Type.SUBTRACT;
				} else {
					currentType = Type.SUBTRACT;
				}
				break;
			case '*':
				if (currentType != Type.CONST && currentType != Type.VARIABLE && currentType != Type.CLOSE_BRACKET) {
					throw new IncorrectUsingOfOperatorException(currentIndex, "multiply");
				}
				currentType = Type.MULTIPLY;
				break;
			case '/':
				if (currentType != Type.CONST && currentType != Type.VARIABLE && currentType != Type.CLOSE_BRACKET) {
					throw new IncorrectUsingOfOperatorException(currentIndex, "divide");
				}
				currentType = Type.DIVIDE;
				break;
			case '(':
				if (currentType == Type.CLOSE_BRACKET || currentType == Type.CONST || currentType == Type.VARIABLE) {
					throw new MissingOperatorException(currentIndex);
				}
				currentType = Type.OPEN_BRACKET;
				currentBalance++;
				if (currentBalance > str.length() - currentIndex - 1) {
					throw new InvalidBracketsException(currentIndex, 0);
				}
				break;
			case ')':
				if (currentType == Type.OPEN_BRACKET) {
					throw new EmptyBracketsException(currentIndex);
				}

				if (currentType != Type.CONST && currentType != Type.VARIABLE && currentType != Type.CLOSE_BRACKET) {
					throw new MissingOperandException(currentIndex);
				}
				currentType = Type.CLOSE_BRACKET;
				currentBalance--;
				if (currentBalance < 0) {
					throw new InvalidBracketsException(currentIndex, 1);
				}
				break;
			default:
				if (str.charAt(currentIndex) >= 'x' && str.charAt(currentIndex) <= 'z') {
					if (currentType == Type.CLOSE_BRACKET || currentType == Type.VARIABLE || currentType == Type.CONST) {
						throw new MissingOperatorException(currentIndex);
					}
					currentVariable = str.substring(currentIndex, currentIndex + 1);
					currentType = Type.VARIABLE;
				} else if (currentIndex + 2 < str.length() && str.substring(currentIndex, currentIndex + 3).equals("low")) {
					checkOnException("low", currentIndex);
					currentType = Type.LOW;
				} else if (currentIndex + 3 < str.length() && str.substring(currentIndex, currentIndex + 4).equals("high")) {
					checkOnException("high", currentIndex);
					currentType = Type.HIGH;
				} else {
					throw new UsingWrongOperatorException(currentIndex);
				}
				break;
		}
		currentIndex++;
	}


}