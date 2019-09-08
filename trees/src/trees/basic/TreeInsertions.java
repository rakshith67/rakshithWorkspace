package trees.basic;

import java.util.Scanner;

public class TreeInsertions {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();

		while (t-- > 0) {
			int n = sc.nextInt();
			if (n == 0) {
				System.out.println(0);
				continue;
			}
			Node root = null;
			for (int i = 0; i < n; i++) {
				int a = sc.nextInt();
				int a1 = sc.nextInt();
				char lr = sc.next().charAt(0);
				if (i == 0 && lr == 'L') {
					root = new Node(a);
					root.setLeft(new Node(a1));
				} else if (i == 0) {
					root = new Node(a);
					root.setRight(new Node(a1));
				} else {
					insert(root, a, a1, lr);
				}
			}
		}
	}

	public static void insert(Node root, int a, int a1, char lr) {
		if (root == null) {
			return;
		}
		if (root.getValue() == a && lr == 'L') {
			root.setLeft(new Node(a1));
			return;
		} else if (root.getValue() == a) {
			root.setRight(new Node(a1));
			return;
		}
		insert(root.getLeft(), a, a1, lr);
		insert(root.getRight(), a, a1, lr);
	}
}
