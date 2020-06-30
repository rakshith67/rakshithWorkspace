package trees.medium;

import trees.basic.Node;
import trees.basic.NodeString;
import trees.basic.TreeTraversals;

public class TreesMediumMain2 {

	public static void main(String[] args) {
		Node root = Node.createTree();
		NodeString rootString = NodeString.createTree();
		int[] preOrderBST = new int[] { 8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15 };
		int[] preOrder = new int[] { 1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 15, 7, 14, 15 };
		int[] preOrderMirror = new int[] { 1, 3, 7, 15, 14, 6, 13, 12, 2, 5, 11, 10, 4, 9, 8 };
		int[] parentArray = new int[] { 1, 13, 31, 7, 17, 33, 27, 1, 5, 15, 19, 3, 33, 17, 19, 21, 23, 25, 31, 11, 29,
				13, 27, 7, 25, -1, 23, 15, 3, 11, 21, 5, 9, 9 };
		int[] levelOrder = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		int[] levelOrderBST = new int[] { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15 };
		int[] inOrder = new int[] { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };
		TreesMediumLevel2 treeOperations = new TreesMediumLevel2();
		TreeTraversals treeTraversals = new TreeTraversals();

		treeOperations.preOrderToPostOrder(preOrderBST, 15);
		
		int depth = treeOperations.findDistance(root, 11, 15);
		System.out.println("Distance between 11 and 15 is " + depth);
		
		int maxDiffNodeAncestor = treeOperations.maxDiffNodeAndAncestor(root);
		System.out.println("Max difference between node and ancestor is " + maxDiffNodeAncestor);
		
		Node rootParentArray = treeOperations.createTreeFromParentArray(parentArray, 34);
		treeTraversals.inOrderTraversal(rootParentArray);
		System.out.println(" - Inorder traversal of created tree from parent array.");
		
		boolean isPreOrderBST = treeOperations.isPreOrderOfBST(preOrderBST, 15);
		if (isPreOrderBST) {
			System.out.println("Is PreOrder of BST");
		} else {
			System.out.println("Not PreOrder of BST");
		}
		
		treeOperations.kLeafNodes(root, 2);
		System.out.println(" - Nodes having 2 leaves");
		
		int maxSum = treeOperations.maximumPathSumOfTree(root);
		System.out.println("Maximum path sum of tree is " + maxSum);
		
		int count = treeOperations.nodesKDistanceFromLeaf(root, 1);
		System.out.println("Number of nodes at 1 distance from leaves is " + count);
		
		boolean isBST = treeOperations.isBinarySearchTree(root);
		if (isBST) {
			System.out.println("is BST");
		} else {
			System.out.println("Not BST");
		}
		
		int largestBSTSize = treeOperations.sizeOfLargestBSTinTree(root);
		System.out.println("Size of largest BST in tree is " + largestBSTSize);
		
		int countSumK = treeOperations.countNodesSumAnyPathK(root, 13);
		System.out.println("count for sum any node with 13 is " + countSumK);
		
		boolean isDupSubTree = treeOperations.isDupSubTree(rootString);
		if (isDupSubTree) {
			System.out.println("is duplicate subtree");
		} else {
			System.out.println("Not duplicate subtree");
		}
		
		treeOperations.numberOfRootLeafPaths(root);
		
		int mirror = treeOperations.mirrorOfKey(root, 13);
		System.out.println("Mirror of 13 is " + mirror);
		
		treeOperations.inOrderSuccessor(root);
		
		Node rootInLevelOrder = treeOperations.buildTreeInOrderLevelOrder(inOrder, levelOrder);
		treeTraversals.preOrderTraversal(rootInLevelOrder);
		System.out.println(" - preOrder of tree from levelOrder and inOrder");
		
		Node rootLevelOrder = treeOperations.buildBSTLevelOrder(levelOrderBST);
		treeTraversals.inOrderTraversal(rootLevelOrder);
		System.out.println(" - inOrder of BST from levelOrder");
		
		Node rootPreOrder = treeOperations.buildTreePreOrderAndMirror(preOrder, preOrderMirror);
		treeTraversals.inOrderTraversal(rootPreOrder);
		System.out.println(" - inOrder of tree from preOrder and mirror tree preOrder");
		
		int maxSumNonAdjacent = treeOperations.getMaxSumNonAdjacent(root);
		System.out.println("Max sum of non adjacent nodes: " + maxSumNonAdjacent);
	}
}
