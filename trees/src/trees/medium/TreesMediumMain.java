package trees.medium;

import linkedlists.NodeLL;
import trees.basic.Node;
import trees.basic.TreeTraversals;

public class TreesMediumMain {

	public static void main(String[] args) {
		Node root = Node.createTree();
		NodeLL head = NodeLL.createLL();

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
	}
}
