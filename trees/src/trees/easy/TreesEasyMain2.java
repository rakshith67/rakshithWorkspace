package trees.easy;

import trees.basic.Node;
import trees.basic.TreeTraversals;

public class TreesEasyMain2 {

	public static void main(String[] args) {
		Node root = Node.createTree();
		int[] inOrder = new int[] { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };
		int[] preOrder = new int[] { 1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 15, 7, 14, 15 };
		String ternaryExpression = "1?2?3:4:5";
		String treeString = "(0(51(64()())(46()(97()())))(76(17()())(36()())))";

		TreesEasyLevel2 treeOperations = new TreesEasyLevel2();
		TreeTraversals treeTraversals = new TreeTraversals();

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
		treeTraversals.inOrderTraversal(rootBST);
		System.out.println(" - BST inorder traversal");

		treeOperations.toSumTree(root);
		System.out.println(" - Sum tree");

		int[] currentIndex = new int[] { 0 };
		Node createdRoot = treeOperations.buildTree(inOrder, preOrder, 0, 14, currentIndex);
		treeTraversals.inOrderTraversal(createdRoot);
		System.out.println(" - inOrder Traversal of create root");
		
		treeOperations.printPostOrder(inOrder, preOrder, 15);
		System.out.println(" - PostOrder traversal of created tree.");

		boolean isPerfect = treeOperations.isPerfectTree(root);
		if (isPerfect) {
			System.out.println("Perfect tree.");
		} else {
			System.out.println("Not perfect tree.");
		}

		treeOperations.printNodesAtOddLevel(root);
		System.out.println(" - Nodes at odd levels.");

		int count = treeOperations.getCountForBudget(root, 13);
		System.out.println("count for 13 budget is " + count);

		treeOperations.printSumOfLeafNodesAtMinLevel(root);

		treeOperations.printPredecessorAndSuccessor(root, 12);

		boolean ismaxHeap = treeOperations.isMaxHeap(root);
		if (ismaxHeap) {
			System.out.println("Max heap tree.");
		} else {
			System.out.println("Not Max heap tree.");
		}

		int shortestDistance = treeOperations.shortestDistance(7, 14);
		System.out.println("Shortest distance between 7 and 14 is " + shortestDistance);

		int kthLevelSum = treeOperations.kthLevelSum(treeString, 2);
		System.out.println("Kth level sum of the tree is " + kthLevelSum);

		Node deletedTreeRoot = treeOperations.deleteNodesGreaterThanKey(root, 9);
		treeTraversals.inOrderTraversal(deletedTreeRoot);
		System.out.println(" - deleted tree with nodes greater than 9");

		Node ternaryRoot = treeOperations.buildTreeFromTernaryExp(ternaryExpression, 0);
		treeTraversals.inOrderTraversal(ternaryRoot);
		System.out.println(" - inOrder traversal of created ternary tree.");
	}
}
