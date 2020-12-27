package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationIterator {

	public static void main(String[] args) {
		CombinationIterator iterator = new CombinationIterator("bvjbvebv", 5);
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	private List<String> list;
	int current;

	public CombinationIterator(String characters, int combinationLength) {
		list = new ArrayList<>();
		char[] array = characters.toCharArray();
		Arrays.sort(array);
		fillList(list, new StringBuilder(), array, 0, combinationLength);
		current = 0;
	}

	public String next() {
		if (hasNext()) {
			return list.get(current++);
		}
		return null;
	}

	public boolean hasNext() {
		return current != list.size();
	}

	private void fillList(List<String> list, StringBuilder builder, char[] string, int index, int combinationLength) {
		if (builder.length() == combinationLength) {
			list.add(builder.toString());
			return;
		}
		for (int i = index; i < string.length; i++) {
			builder.append(string[i]);
			fillList(list, builder, string, i + 1, combinationLength);
			builder.deleteCharAt(builder.length() - 1);
		}
	}

}
