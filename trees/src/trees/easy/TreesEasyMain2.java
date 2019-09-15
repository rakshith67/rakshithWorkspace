package trees.easy;

import trees.basic.Node;
import trees.basic.TreeTraversals;

public class TreesEasyMain2 {

	public static void main(String[] args) {
		Node root = Node.createTree();
		int[] inOrder = new int[] { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };
		int[] preOrder = new int[] { 1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 15, 7, 14, 15 };

		TreesEasyLevel2 treeOperations = new TreesEasyLevel2();
		treeOperations.printNodesAtKLevel(root, 3);
		System.out.println("- Nodes at 3rd level from root");
		int oddEvenDifferenece = treeOperations.diffOddEvenLevelSum(root);
		System.out.println(oddEvenDifferenece + " : Difference in odd and even levels");
		boolean isIdentical = treeOperations.isIdenticalTrees(root, root);
		if (isIdentical) {
			System.out.println("Identical Trees");
		} else {
			System.out.println("Not Identical Trees");
		}
		boolean isSymmetric = treeOperations.isSymmetricTree(root);
		if (isSymmetric) {
			System.out.println("Symmetric Tree");
		} else {
			System.out.println("Not Symmetric Tree");
		}
		boolean areMirrorTrees = treeOperations.areMirrorTrees(root, root);
		if (areMirrorTrees) {
			System.out.println("Mirror Tree");
		} else {
			System.out.println("Not mirror Tree");
		}
		boolean leavesSameLevel = treeOperations.leavesAtSameLevel(root);
		if (leavesSameLevel) {
			System.out.println("Leaves at same level.");
		} else {
			System.out.println("Leaves not at same level.");
		}
		treeOperations.kthLargestElement(root, 3);
		System.out.println("- kth largest element");
		int maxPathSum = treeOperations.maximumPathSum(root);
		System.out.println(maxPathSum + "- max path sum");
		boolean isIsomorphic = treeOperations.isIsomorphicTrees(root, root);
		if (isIsomorphic) {
			System.out.println("Isomorphic trees");
		} else {
			System.out.println("Not Isomorphic trees");
		}
		treeOperations.printAncestors(root, 15);
		System.out.println("Ancestors of 15");
		Node leastCommonAncestor = treeOperations.leastCommonAncestor(root, 6, 15);
		System.out.println("LCA of 6 and 15 is " + leastCommonAncestor.getValue());
		boolean isSumTree = treeOperations.isSumTree(root);
		if (isSumTree) {
			System.out.println("sum tree");
		} else {
			System.out.println("Not sum tree");
		}
		Node rootBST = treeOperations.binaryTreeToBST(root);
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.inOrderTraversal(rootBST);
		System.out.println(" - BST inorder traversal");
		treeOperations.toSumTree(root);
		System.out.println(" - Sum tree");
		int[] currentIndex = new int[] { 0 };
		Node createdRoot = treeOperations.buildTree(inOrder, preOrder, 0, 14, currentIndex);
		treeTraversals.inOrderTraversal(createdRoot);
		System.out.println(" - inOrder Traversal of create root");
	}
}
