package trees.medium;

public class TreesMediumMain2 {

	public static void main(String[] args) {
		int[] preOrder = new int[] { 8, 7, 2, 1, 3, 5, 4, 6, 12, 10, 9, 11, 14, 13, 15 };
		TreesMediumLevel2 treeOperations = new TreesMediumLevel2();

		treeOperations.preOrderToPostOrder(preOrder, 15);
	}
}
