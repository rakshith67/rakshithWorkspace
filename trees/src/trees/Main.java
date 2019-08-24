package trees;

public class Main {

	public static void main(String[] args) {
		Node root = new Node(1);
		Node root2 = new Node(2);
		root.setLeft(root2);
		Node root3 = new Node(3);
		root.setRight(root3);
		Node root4 = new Node(4);
		Node root5 = new Node(5);
		root2.setLeft(root4);
		root2.setRight(root5);
		Node root6 = new Node(6);
		Node root7 = new Node(7);
		root3.setLeft(root6);
		root3.setRight(root7);
		root4.setLeft(new Node(8));
		root4.setRight(new Node(9));
		root5.setLeft(new Node(10));
		root5.setRight(new Node(11));
		root6.setLeft(new Node(12));
		root6.setRight(new Node(13));
		root7.setLeft(new Node(14));
		root7.setRight(new Node(15));
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.preOrderTraversal(root);
		System.out.println(" - PreOrderTraversal");
		treeTraversals.inOrderTraversal(root);
		System.out.println(" - InOrderTraversal");
		treeTraversals.postOrderTraversal(root);
		System.out.println(" - PostOrderTraversal");
		treeTraversals.levelOrderTraversal(root);
		System.out.println(" - LevelOrderTraversal");
		treeTraversals.reverseLevelOrderTraversal(root);
		System.out.println(" - ReverseLevelOrderTraversal");
		treeTraversals.levelOrderTraversalLine(root);
		System.out.println(" - LineLevelOrderTraversal");
		TreeIntOperations treeIntOperations = new TreeIntOperations();
		int height = treeIntOperations.height(root);
		System.out.println("height of tree is " + height);
		treeIntOperations.checkIfBalanced(root);
		int size = treeIntOperations.sizeOfTree(root);
		System.out.println("Size of the tree is " + size);
		int numberOfLeaves = treeIntOperations.numberOfLeaves(root);
		System.out.println("number of leaves in the tree is " + numberOfLeaves);
		int numberOfNonLeaves = treeIntOperations.numberOfNonLeaves(root);
		System.out.println("number of non leaves in the tree is " + numberOfNonLeaves);
		int sum = treeIntOperations.sumOfTree(root);
		System.out.println("Sum of the tree is " + sum);
		int sumOfLeaves = treeIntOperations.sumOfLeaves(root);
		System.out.println("Sum of leaves of the tree is " + sumOfLeaves);
		int sumOfRightLeaves = treeIntOperations.sumOfRightLeaves(root, 0);
		System.out.println("Sum of right leaves of the tree is " + sumOfRightLeaves);
		int sumOfLeftLeaves = treeIntOperations.sumOfLeftLeaves(root, 0);
		System.out.println("Sum of left leaves of the tree is " + sumOfLeftLeaves);
		int maximum = treeIntOperations.maxElement(root);
		int minimum = treeIntOperations.minElement(root);
		System.out.println("Max element in the tree is " + maximum);
		System.out.println("Min element in the tree is " + minimum);
		int maximumWidth = treeIntOperations.maxWidth(root);
		System.out.println("Max width in the tree is " + maximumWidth);
		int level = treeIntOperations.findLevelOfKey(root, 15);
		System.out.println("15th node depth in the tree is " + level);
		int minDepth = treeIntOperations.minimumHeight(root);
		System.out.println("minimum depth of the tree is " + minDepth);
	}
}
