package design;

import java.util.Stack;

public class CustomStack {

	public static void main(String[] args) {
		CustomStack customStack = new CustomStack(3); // Stack is Empty []

		customStack.push(1); // stack becomes [1]
		customStack.push(2); // stack becomes [1, 2]
		System.out.println(customStack.pop()); // return 2 --> Return top of the stack 2, stack becomes [1]
		customStack.push(2); // stack becomes [1, 2]
		customStack.push(3); // stack becomes [1, 2, 3]
		customStack.push(4); // stack still [1, 2, 3], Don't add another elements as size is 4
		customStack.increment(5, 100); // stack becomes [101, 102, 103]
		customStack.increment(2, 100); // stack becomes [201, 202, 103]
		System.out.println(customStack.pop()); // return 103 --> Return top of the stack 103, stack becomes [201, 202]
		System.out.println(customStack.pop()); // return 202 --> Return top of the stack 102, stack becomes [201]
		System.out.println(customStack.pop()); // return 201 --> Return top of the stack 101, stack becomes []
		System.out.println(customStack.pop());
	}

	int[] increment;
	Stack<Integer> stack;

	public CustomStack(int maxSize) {
		stack = new Stack<>();
		increment = new int[maxSize];
	}

	public void push(int x) {
		if (stack.size() < increment.length) {
			stack.push(x);
		}
	}

	public int pop() {
		if (stack.isEmpty()) {
			return -1;
		}
		int index = stack.size() - 1;
		int result = stack.pop() + increment[index];
		if (index > 0) {
			increment[index - 1] += increment[index];
		}
		increment[index] = 0;
		return result;
	}

	public void increment(int k, int val) {
		int min = Math.min(stack.size(), k) - 1;
		if (min >= 0) {
			increment[min] += val;
		}
	}
}
