package trees.hard;

import trees.basic.Node;

public class TreesHardMain {

	public static void main(String[] args) {
		Node root = Node.createTree();

		TreesHardLevel treeOperations = new TreesHardLevel();
		treeOperations.treeToDLL(root);
		treeOperations.printKDistanceNodes(root, root, 3);
		System.out.println(" - nodes at distance 3 from root");
		int turns = treeOperations.numberOfTurns(root, 8, 14);
		System.out.println("Number of turns to reach from 8 to 14 is " + turns);
	}
}
