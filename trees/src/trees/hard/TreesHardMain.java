package trees.hard;

import trees.basic.Node;

public class TreesHardMain {

	public static void main(String[] args) {
		Node root = Node.createTree();

		TreesHardLevel treeOperations = new TreesHardLevel();
		treeOperations.treeToDLL(root);
	}
}
