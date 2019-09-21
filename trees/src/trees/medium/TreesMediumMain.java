package trees.medium;

import linkedlists.NodeLL;
import trees.basic.Node;
import trees.basic.NodeRight;
import trees.basic.TreeTraversals;

public class TreesMediumMain {

	public static void main(String[] args) {
		Node root = Node.createTree();
		Node rootBST = Node.createBST();
		NodeLL head = NodeLL.createLL();
		NodeRight rootRight = NodeRight.createTree();
		int[] inOrder = new int[] { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };
		int[] postOrder = new int[] { 8, 9, 4, 10, 11, 5, 2, 12, 13, 6, 14, 15, 7, 3, 1 };
		int[] preOrder = new int[] { 1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 15, 7, 14, 15 };
		char[] preLN = new char[] { 'N', 'N', 'N', 'L', 'L', 'N', 'L', 'L', 'N', 'N', 'L', 'L', 'N', 'L', 'L', };

		TreesMediumLevel treeOperations = new TreesMediumLevel();
		TreeTraversals treeTraversals = new TreeTraversals();

		Node newRoot = treeOperations.cloneTree(root);
		treeTraversals.inOrderTraversal(newRoot);
		System.out.println(" - Inorder traversal of cloned tree");
		boolean isFoldable = treeOperations.foldableTree(root);
		if (isFoldable) {
			System.out.println("Is foldable tree");
		} else {
			System.out.println("Not foldable tree");
		}
		int count = treeOperations.countNumberOfNodesInRange(root, 3, 13);
		System.out.println("Number of nodes in range 3 to 13 is " + count);
		treeOperations.leavesToDLL(root);
		treeOperations.treeToCDLL(root);
		treeOperations.linkedListToTree(head);
		int countSum = treeOperations.countSubtreesWithSumX(root, 26);
		System.out.println("Number of sub trees with count 26 is " + countSum);
		treeOperations.printExtremeNodesAlternate(root);
		System.out.println(" - Extreme nodes alternate order");
		int pairs = treeOperations.countPairsViolatingBST(rootBST);
		System.out.println("Number of pairs violating BST " + pairs);
		treeOperations.mergeTwoBSTs(rootBST, rootBST);
		System.out.println(" - Inorder traversal of merged BST's");
		treeOperations.printCornerNodes(root);
		System.out.println(" - Corner Nodes of the tree");
		treeOperations.connectNodesWithRight(rootRight);
		int minDiff = treeOperations.minimumDifference(rootBST, 21);
		System.out.println("Minmum difference in the BST is " + minDiff);
		boolean isSubTree = treeOperations.isSubTree(root, root);
		if (isSubTree) {
			System.out.println("Is sub tree");
		} else {
			System.out.println("Not sub tree");
		}
		Node rootInPost = treeOperations.buildTree(inOrder, postOrder, 15);
		treeTraversals.inOrderTraversal(rootInPost);
		System.out.println(" - Inorder traversal of created tree from inOrder and postOrder");
		String expression = treeOperations.serialize(root);
		Node deserializedRoot = treeOperations.deserialize(expression);
		treeTraversals.inOrderTraversal(deserializedRoot);
		System.out.println(" - Inorder traversal of deserialized tree");
		Node rootPreOrder = treeOperations.constructTree(15, preOrder, preLN);
		treeTraversals.inOrderTraversal(rootPreOrder);
		System.out.println(" - Inorder traversal of constructed tree from preOrder and preLN");
	}
}
