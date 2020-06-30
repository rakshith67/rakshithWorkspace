package stacks.operations;

import java.util.ArrayDeque;
import java.util.Deque;

public class StacksOps {

	/**
	 * evaluates the postFix expression.
	 * 
	 * GFG link:
	 * https://practice.geeksforgeeks.org/problems/evaluation-of-postfix-expression/0
	 * 
	 * @param postfixExpression - postFix expression
	 */
	public int evaluatePostfixExpression(String postfixExpression) {
		if (postfixExpression.isEmpty()) {
			return -1;
		}
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < postfixExpression.length(); i++) {
			char character = postfixExpression.charAt(i);
			if (isOperator(character)) {
				int second = deque.pop();
				int first = deque.pop();
				int value = evaluate(character, first, second);
				deque.push(value);
			} else {
				deque.push(character - '0');
			}
		}
		return deque.pop();
	}

	/**
	 * evaluates the prefix expression.
	 * 
	 * @param prefixExpression - prefix expression
	 */
	public int evaluatePrefixExpression(String prefixExpression) {
		if (prefixExpression.isEmpty()) {
			return -1;
		}
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = prefixExpression.length() - 1; i >= 0; i--) {
			char character = prefixExpression.charAt(i);
			if (isOperator(character)) {
				int secondValue = deque.pop();
				int firstValue = deque.pop();
				int value = evaluate(character, secondValue, firstValue);
				deque.push(value);
			} else {
				deque.push(character - '0');
			}
		}
		return deque.pop();
	}

	private boolean isOperator(char character) {
		if (character == '+' || character == '-' || character == '*' || character == '/') {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private int evaluate(char character, int first, int second) {
		switch (character) {
		case '/':
			return first / second;
		case '+':
			return first + second;
		case '-':
			return first - second;
		case '*':
			return first * second;
		default:
			return 0;
		}
	}

	/**
	 * validates the string and returns the max valid size.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/valid-substring/0
	 * 
	 * @param expression - string to be checked
	 */
	public int validSubString(String expression) {
		if (expression.isEmpty()) {
			return 0;
		}
		Deque<Integer> deque = new ArrayDeque<>();
		int maximum = 0;
		for (int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);
			if (character == '(') {
				deque.push(i);
			} else {
				deque.pop();
				if (!deque.isEmpty()) {
					maximum = Math.max(maximum, i - deque.getLast());
				} else {
					deque.push(i);
				}
			}
		}
		return maximum;
	}

	/**
	 * prints the functional bracket number of the string.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/print-bracket-number/0
	 * 
	 * @param expression - string to be checked
	 */
	public void printBracketNumber(String expression) {
		if (expression.isEmpty()) {
			return;
		}
		Deque<Integer> deque = new ArrayDeque<>();
		StringBuilder stringBuilder = new StringBuilder();
		int count = 0;
		for (int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);
			if (character == '(') {
				count++;
				deque.push(count);
				stringBuilder.append(count + " ");
			} else if (character == ')') {
				int currentCount = deque.pop();
				stringBuilder.append(currentCount + " ");
			}
		}
		System.out.println("Bracket Numbers: " + stringBuilder.toString());
	}

	/**
	 * decodes the given string and prints it.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/decode-the-string/0
	 * 
	 * @param expression - string to be decoded
	 */
	public void decodeString(String expression) {
		if (expression.isEmpty()) {
			return;
		}
		Deque<Character> deque = new ArrayDeque<>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);
			if (character == ']') {
				while (deque.getFirst() != '[') {
					builder.insert(0, String.valueOf(deque.getFirst()));
					deque.pop();
				}
				deque.pop();
				int value = deque.pop() - '0';
				String val = builder.toString();
				for (int j = 1; j < value; j++) {
					builder.append(val);
				}
			} else {
				deque.push(character);
			}
		}
		System.out.println("Decoded string: " + builder.toString());
	}

	/**
	 * counts number of reversals required to get a balanced string.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/count-the-reversals/0
	 * 
	 * @param expression - string to be counted
	 */
	public int countReversals(String expression) {
		throw new UnsupportedOperationException();
	}

	/**
	 * sorts the given stack.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/sort-a-stack/1
	 * 
	 * @param stack - stack
	 */
	public void sortStack(Deque<Integer> stack) {
		if (!stack.isEmpty()) {
			int temp = stack.pop();
			sortStack(stack);
			sortedInsert(stack, temp);
		}
		while (!stack.isEmpty()) {
			System.out.print(stack.getLast() + " ");
			stack.pop();
		}
	}

	private void sortedInsert(Deque<Integer> stack, int element) {
		if (stack.isEmpty() || element > stack.getLast()) {
			stack.push(element);
			return;
		}
		int temp = stack.pop();
		sortedInsert(stack, temp);
		stack.push(temp);
	}

	/**
	 * parenthesis checker whether balanced or not
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/parenthesis-checker/0
	 * 
	 * @param expression - expression
	 */
	public void parenthisChecker(String expression) {
		boolean balanced = true;
		Deque<Character> deque = new ArrayDeque<>();
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '{' || expression.charAt(i) == '[' || expression.charAt(i) == '(') {
				deque.push(expression.charAt(i));
			} else if (expression.charAt(i) == '}') {
				if (deque.isEmpty()) {
					balanced = false;
					break;
				} else {
					char character = deque.pop();
					if (character != '{') {
						balanced = false;
						break;
					}
				}
			} else if (expression.charAt(i) == ')') {
				if (deque.isEmpty()) {
					balanced = false;
					break;
				} else {
					char character = deque.pop();
					if (character != '(') {
						balanced = false;
						break;
					}
				}
			} else {
				if (deque.isEmpty()) {
					balanced = false;
					break;
				} else {
					char character = deque.pop();
					if (character != '[') {
						balanced = false;
						break;
					}
				}
			}
		}
		if (!deque.isEmpty()) {
			balanced = false;
		}
		if (!balanced) {
			System.out.println("not balanced");
		} else {
			System.out.println("balanced");
		}
	}

	/**
	 * finds the next larger element in the array.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/next-larger-element/0
	 * 
	 * @param expression - expression
	 */
	public void nextLargerElement(int[] array, int size) {
		Deque<Integer> deque = new ArrayDeque<>();
		if (size == 0) {
			return;
		}
		deque.push(array[0]);
		for (int i = 1; i < size; i++) {
			if (deque.isEmpty()) {
				deque.push(array[i]);
			}
			while (!deque.isEmpty() && deque.getLast() < array[i]) {
				System.out.print(array[i] + " ");
				deque.removeLast();
			}
			deque.push(array[i]);
		}
		while (!deque.isEmpty()) {
			System.out.print("-1 ");
		}
	}
}
