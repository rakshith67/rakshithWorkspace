package trees.amazon.easy;

import trees.Node;

public class Main2 {

	public static void main(String[] args) {
		Node root = new Node(1);
		Node root2 = new Node(2);
		root.setLeft(root2);
		Node root3 = new Node(3);
		root.setRight(root3);
		Node root4 = new Node(4);
		Node root5 = new Node(5);
		root2.setLeft(root4);
		root2.setRight(root5);
		Node root6 = new Node(6);
		Node root7 = new Node(7);
		root3.setLeft(root6);
		root3.setRight(root7);
		root4.setLeft(new Node(8));
		root4.setRight(new Node(9));
		root5.setLeft(new Node(10));
		root5.setRight(new Node(11));
		root6.setLeft(new Node(12));
		root6.setRight(new Node(13));
		root7.setLeft(new Node(14));
		root7.setRight(new Node(15));

		TreeAmazonEasyLevel2 treeOperations = new TreeAmazonEasyLevel2();
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
		treeOperations.toSumTree(root);
		System.out.println(" - Sum tree");
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
	}
}
