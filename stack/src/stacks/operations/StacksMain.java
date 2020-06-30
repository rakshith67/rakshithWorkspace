package stacks.operations;

import java.util.ArrayDeque;
import java.util.Deque;

public class StacksMain {

	public static void main(String[] args) {
		String postfixExpression = "231*+9-";
		StacksOps stackOperations = new StacksOps();
		System.out.println("postfix value: " + stackOperations.evaluatePostfixExpression(postfixExpression));
		String prefixExpression = "-+8/632";
		System.out.println("prefix value: " + stackOperations.evaluatePrefixExpression(prefixExpression));
		String validString = "()()()()())(((((()))(()(((())())())(()(()(()";
		int validSubstringCount = stackOperations.validSubString(validString);
		System.out.println("Valid substring count: " + validSubstringCount);
		String decodeString = "1[a4[bccd]2[c]]";
		stackOperations.decodeString(decodeString);
		String bracketsExpression = "(a+(b*c))+(d/e)";
		stackOperations.printBracketNumber(bracketsExpression);
		Deque<Integer> stack = createStack();
		stackOperations.sortStack(stack);
	}

	private static Deque<Integer> createStack() {
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		return stack;
	}
}
