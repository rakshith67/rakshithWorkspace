package trees;

public class Main {

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
		TreeTraversals treeTraversals = new TreeTraversals();
		treeTraversals.preOrderTraversal(root);
		System.out.println();
		treeTraversals.inOrderTraversal(root);
		System.out.println();
		treeTraversals.postOrderTraversal(root);
		System.out.println();
		treeTraversals.lineOrderTraversal(root);
	}
}
