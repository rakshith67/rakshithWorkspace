package avltree;

public class AVLTree {

	public AVLNode insert(AVLNode root, int key) {
		if (root == null) {
			return (new AVLNode(key));
		}
		if (key < root.getValue()) {
			root.setLeft(insert(root.getLeft(), key));
		} else if (key > root.getValue()) {
			root.setRight(insert(root.getRight(), key));
		} else {
			return root;
		}

		root.setHeight(1 + Math.max(height(root.getLeft()), height(root.getRight())));
		int balance = getBalance(root);

		if (balance > 1 && key < root.getLeft().getValue()) {
			System.out.println("LL Rotation for " + root.getValue() + " after inserting " + key);
			return rightRotate(root);
		}
		if (balance < -1 && key > root.getRight().getValue()) {
			System.out.println("RR Rotation for " + root.getValue() + " after inserting " + key);
			return leftRotate(root);
		}
		if (balance > 1 && key > root.getLeft().getValue()) {
			System.out.println("LR Rotation for " + root.getValue() + " after inserting " + key);
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}
		if (balance < -1 && key < root.getRight().getValue()) {
			System.out.println("RL Rotation for " + root.getValue() + " after inserting " + key);
			root.setRight(rightRotate(root.getRight()));
			return leftRotate(root);
		}
		return root;
	}

	public AVLNode deleteNode(AVLNode root, int key) {
		if (root == null) {
			return root;
		}
		if (key < root.getValue()) {
			root.setLeft(deleteNode(root.getLeft(), key));
		} else if (key > root.getValue()) {
			root.setRight(deleteNode(root.getRight(), key));
		} else {

			// node with only one child or no child
			if ((root.getLeft() == null) || (root.getRight() == null)) {
				AVLNode temp = null;
				if (temp == root.getLeft()) {
					temp = root.getRight();
				} else {
					temp = root.getLeft();
				}
				// No child case
				if (temp == null) {
					temp = root;
					root = null;
				} else // One child case
					root = temp; // Copy the contents of
									// the non-empty child
			} else {

				// node with two children: Get the inorder
				// successor (smallest in the right subtree)
				AVLNode temp = minValueNode(root.getRight());

				// Copy the inorder successor's data to this node
				root.setValue(temp.getValue());

				// Delete the inorder successor
				root.setRight(deleteNode(root.getRight(), temp.getValue()));
			}
		}

		// If the tree had only one node then return
		if (root == null)
			return root;

		// STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
		root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);

		// STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
		// this node became unbalanced)
		int balance = getBalance(root);

		// If this node becomes unbalanced, then there are 4 cases
		// Left Left Case
		if (balance > 1 && getBalance(root.getLeft()) >= 0)
			return rightRotate(root);

		// Left Right Case
		if (balance > 1 && getBalance(root.getLeft()) < 0) {
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}

		// Right Right Case
		if (balance < -1 && getBalance(root.getRight()) <= 0)
			return leftRotate(root);

		// Right Left Case
		if (balance < -1 && getBalance(root.getRight()) > 0) {
			root.setRight(rightRotate(root.getRight()));
			return leftRotate(root);
		}

		return root;
	}

	private AVLNode minValueNode(AVLNode node) {
		AVLNode current = node;

		/* loop down to find the leftmost leaf */
		while (current.getLeft() != null) {
			current = current.getLeft();
		}
		return current;
	}

	private AVLNode rightRotate(AVLNode root) {
		AVLNode newRoot = root.getLeft();
		root.setLeft(newRoot.getRight());
		newRoot.setRight(root);
		newRoot.setHeight(1 + Math.max(height(newRoot.getLeft()), height(newRoot.getRight())));
		root.setHeight(1 + Math.max(height(root.getLeft()), height(root.getRight())));
		return newRoot;
	}

	private AVLNode leftRotate(AVLNode root) {
		AVLNode newRoot = root.getRight();
		root.setRight(newRoot.getLeft());
		newRoot.setLeft(root);
		newRoot.setHeight(1 + Math.max(height(newRoot.getLeft()), height(newRoot.getRight())));
		root.setHeight(1 + Math.max(height(root.getLeft()), height(root.getRight())));
		return newRoot;
	}

	private int height(AVLNode avlNode) {
		if (avlNode == null) {
			return 0;
		}
		return avlNode.getHeight();
	}

	private int getBalance(AVLNode avlNode) {
		if (avlNode == null) {
			return 0;
		}
		return height(avlNode.getLeft()) - height(avlNode.getRight());
	}
}
