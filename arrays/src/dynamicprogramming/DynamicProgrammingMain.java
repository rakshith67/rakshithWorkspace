package dynamicprogramming;

public class DynamicProgrammingMain {

	public static void main(String[] args) {
		DynamicProgrammingOperations operations = new DynamicProgrammingOperations();
		operations.generateFibonacciBottomUp(12);
		operations.generateFibonacciTopDown(12);
		int[] factors = new int[] { 1, 3, 4 };
		operations.numberFactorTopDown(12, factors);
		operations.numberFactorBottomUp(12, factors);
	}
}
