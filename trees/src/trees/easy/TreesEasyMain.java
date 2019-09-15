package trees.easy;

import trees.basic.Node;
import trees.basic.TreeTraversals;

public class TreesEasyMain {

	public static void main(String[] args) {
		Node root = Node.createTree();
		int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };

		TreesEasyLevel treeOperations = new TreesEasyLevel();
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
		boolean hasPathSum = treeOperations.hasPathSum(root, 15);
		if (hasPathSum) {
			System.out.println("tree has path sum");
		} else {
			System.out.println("tree does not has path sum");
		}
		boolean isCousins = treeOperations.isCousins(root, 11, 15);
		if (isCousins) {
			System.out.println("Is Cousins");
		} else {
			System.out.println("Not Cousins");
		}
		int maxNodeLevel = treeOperations.maxNodeLevel(root);
		System.out.println("Max node level: " + maxNodeLevel);
		treeOperations.printNonSiblingNodes(root);
		System.out.println(" - Non sibling nodes");
		int largestSequence = treeOperations.largestConsecutiveSequence(root);
		System.out.println(largestSequence + " - largest consequence");
		TreeTraversals treeTraversals = new TreeTraversals();
		treeOperations.removeHalfNodes(root);
		treeTraversals.inOrderTraversal(root);
		System.out.println("- Inorder traversal of removed half nodes tree");
		treeOperations.reverseAlternateLevelsOfTree(root);
		treeTraversals.inOrderTraversal(root);
		System.out.println(" - inOrder traversal of reversed alternate levels");
		treeOperations.mirrorTree(root);
		System.out.println("- Inorder traversal of mirror tree");
		int diameter = treeOperations.diameter(root);
		System.out.println(diameter + " - diameter of the tree");
		Node createdBSTRoot = treeOperations.arrayToBST(array, 0, 14);
		treeTraversals.preOrderTraversal(createdBSTRoot);
		System.out.println(" - preOrderTraversal of created BST");
	}
}
