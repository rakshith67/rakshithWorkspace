package linkedlists;

public class Main {

	public static void main(String[] args) {

		NodeLL node1 = new NodeLL(1);
		NodeLL node2 = new NodeLL(2);		node1.setNext(node2);

		LinkedListMedium medium = new LinkedListMedium();
		LinkedListEasy easy = new LinkedListEasy();
		medium.reverseKGroup(node1, 2);
		node1 = easy.addOne(node1);
		print(node1);
		int[] array = medium.nextLargerNodes(node1);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		easy.detectLoop(node1);
		easy.removeDuplicates(node1);
		node1 = easy.reverseList(node1);
		print(node1);
	}

	private static void print(NodeLL node1) {
		while (node1 != null) {
			System.out.print(node1.getValue() + " ");
			node1 = node1.getNext();
		}
	}
}
