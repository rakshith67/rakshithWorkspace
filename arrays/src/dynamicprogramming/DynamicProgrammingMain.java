package dynamicprogramming;

public class DynamicProgrammingMain {

	public static void main(String[] args) {
		DynamicProgrammingOperations operations = new DynamicProgrammingOperations();
		DynamicProgrammingOperations2 operations2 = new DynamicProgrammingOperations2();
		operations.generateFibonacciBottomUp(12);
		operations.generateFibonacciTopDown(12);
		int[] factors = new int[] { 1, 3, 4 };
		operations.numberFactorTopDown(12, factors);
		operations.numberFactorBottomUp(12, factors);
		String[] strings = new String[] {"11111","100","1101","1101","11000"};
		System.out.println(operations2.findMaxForm(strings, 5, 7));
	}
}
