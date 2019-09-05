package trees.amazon.easy;

import trees.Node;
import trees.TreeTraversals;

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

		TreesAmazonEasyLevel treeOperations = new TreesAmazonEasyLevel();
		boolean ischildSumParentTree = treeOperations.childSumParentTree(root);
		System.out.println("is child sum parent tree: " + ischildSumParentTree);
		int maxSumLevel = treeOperations.maxLevelSum(root);
		System.out.println("Max sum in a level is : " + maxSumLevel);
		boolean fullBinaryTree = treeOperations.fullBinaryTree(root);
		System.out.println("Full Binary Tree: " + fullBinaryTree);
		treeOperations.verticalSum(root);
		System.out.println(" - vertical sum of the tree");
		treeOperations.diagonalSum(root);
		System.out.println(" - diagonal sum of the tree");
		treeOperations.rootToLeafPaths(root);
		System.out.println(" - Root to leaf paths");
		long sum = treeOperations.rootToLeafPathsSum(root);
		System.out.println(sum + " - Root to leaf paths sum");
		int maxNodeLevel = treeOperations.maxNodeLevel(root);
		System.out.println("Max node level: " + maxNodeLevel);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeOperations.removeHalfNodes(root);
		treeTraversals.inOrderTraversal(root);
		System.out.println("- Inorder traversal of removed half nodes tree");
		treeOperations.mirrorTree(root);
		System.out.println("- Inorder traversal of mirror tree");
		int diameter = treeOperations.diameter(root);
		System.out.println(diameter + " - diameter of the tree");
	}
}
