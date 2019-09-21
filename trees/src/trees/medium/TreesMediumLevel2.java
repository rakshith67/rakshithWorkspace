package trees.medium;

public class TreesMediumLevel2 {

	/**
	 * prints postOrder traversal of tree from preOrder traversal.
	 * 
	 * GFG link: https://practice.geeksforgeeks.org/problems/preorder-to-postorder/0
	 * 
	 * @param preOrder - preOrder of the tree
	 */
	public void preOrderToPostOrder(int[] preOrder, int size) {
		int[] currentIndex = new int[1];
		int minimum = Integer.MIN_VALUE;
		int maximum = Integer.MAX_VALUE;
		printPreOrderToPostOrder(preOrder, size, minimum, maximum, currentIndex);
		System.out.println(" - postOrder from preOrder");
	}

	private void printPreOrderToPostOrder(int[] preOrder, int size, int minimum, int maximum, int[] currentIndex) {
		if (currentIndex[0] == size) {
			return;
		}
		if (preOrder[currentIndex[0]] < minimum || preOrder[currentIndex[0]] > maximum) {
			return;
		}
		int value = preOrder[currentIndex[0]];
		currentIndex[0]++;
		printPreOrderToPostOrder(preOrder, size, minimum, value, currentIndex);
		printPreOrderToPostOrder(preOrder, size, value, maximum, currentIndex);
		System.out.print(value + " ");
	}

}
