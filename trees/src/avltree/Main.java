package avltree;

public class Main {

	public static void main(String[] args) {

		AVLTree avlTree = new AVLTree();
		AVLNode root = new AVLNode(30, 1);
		avlTree.insert(root, 20);
		avlTree.insert(root, 40);
		avlTree.insert(root, 10);
		avlTree.insert(root, 5);
		avlTree.insert(root, 3);
		avlTree.insert(root, 4);
		avlTree.insert(root, 50);
		avlTree.insert(root, 60);
		avlTree.insert(root, 70);
		avlTree.insert(root, 65);
		System.out.println("Height of the entire tree after rotations " + root.getHeight());
	}
}
