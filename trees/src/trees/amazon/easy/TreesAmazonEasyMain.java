package trees.amazon.easy;

import trees.Node;
import trees.TreeTraversals;

public class TreesAmazonEasyMain {

	public static void main(String[] args) {
		Node root = Node.createTree();

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
