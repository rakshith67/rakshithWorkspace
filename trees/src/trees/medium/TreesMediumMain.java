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
	}
}
