package arrays;

import java.util.List;

public class ArraysMediumMain {

	public static void main(String[] args) {
		BacktrackingLevel arraysMedium = new BacktrackingLevel();
		int[] nums = new int[] { 1, 2, 3 };
		int[] numsDup = new int[] { 1, 1, 3 };
		List<List<Integer>> result = arraysMedium.subsets(nums);
		for (List<Integer> list : result) {
			for (Integer num : list) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
		result = arraysMedium.permute(nums);
		for (List<Integer> list : result) {
			for (Integer num : list) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
		result = arraysMedium.permuteUnique(numsDup);
		for (List<Integer> list : result) {
			for (Integer num : list) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

}
