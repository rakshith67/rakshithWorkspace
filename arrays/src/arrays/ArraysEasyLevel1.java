package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class ArraysEasyLevel1 {

	/**
	 * Given two sorted arrays A and B, such that the arrays may have some common
	 * elements. Find the sum of the maximum sum path to reach from the beginning of
	 * any array to end of any of the two arrays. We can switch from one array to
	 * another array only at the common elements. Link:
	 * https://practice.geeksforgeeks.org/problems/max-sum-path-in-two-arrays/1
	 * 
	 */
	public int maxPathSum(int ar1[], int ar2[]) {
		int sum1 = 0;
		int sum2 = 0;
		int result = 0;
		int i = 0;
		int j = 0;
		while (i < ar1.length && j < ar2.length) {
			if (ar1[i] < ar2[j]) {
				sum1 += ar1[i++];
			} else if (ar1[i] > ar2[j]) {
				sum2 += ar2[j++];
			} else {
				result += Math.max(sum1, sum2);
				sum1 = 0;
				sum2 = 0;
				while (i < ar1.length && j < ar2.length && ar1[i] == ar2[j]) {
					result = result + ar1[i++];
					j++;
				}
			}
		}
		while (i < ar1.length) {
			sum1 += ar1[i++];
		}
		while (j < ar2.length) {
			sum2 += ar2[j++];
		}
		result += Math.max(sum1, sum2);
		return result;
	}

	/**
	 * Given an array nums, write a function to move all 0's to the end of it while
	 * maintaining the relative order of the non-zero elements. Link:
	 * https://leetcode.com/problems/move-zeroes/
	 * 
	 */
	public void moveZeroes(int[] nums) {
		if (nums.length == 0) {
			return;
		}
		int position = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[position] = nums[i];
				position++;
			}
		}
		while (position < nums.length) {
			nums[position] = 0;
			position++;
		}
	}

	/**
	 * Given an array of length N consisting of only 0s and 1s in random order.
	 * Modify the array to segregate 0s on left side and 1s on the right side of the
	 * array. Link:
	 * https://practice.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1/?company[]=Goldman%20Sachs&problemType=functional&page=1&sortBy=submissions&query=company[]Goldman%20SachsproblemTypefunctionalpage1sortBysubmissions
	 * 
	 */
	void segregate0and1(int[] arr, int n) {
		int low = 0;
		int high = n - 1;
		while (low < high) {
			while (arr[low] == 0 && low < high) {
				low++;
			}
			while (arr[high] == 1 && low < high) {
				high--;
			}
			if (low < high) {
				arr[low] = 0;
				arr[high] = 1;
				low++;
				high--;
			}
		}
	}

	/**
	 * Given an array nums with n objects colored red, white, or blue, sort them
	 * in-place so that objects of the same color are adjacent, with the colors in
	 * the order red, white, and blue. Here, we will use the integers 0, 1, and 2 to
	 * represent the color red, white, and blue respectively.
	 * 
	 * Link: https://leetcode.com/problems/sort-colors/
	 * 
	 */
	public void sortColors_0s1s2s(int[] a) {
		int low = 0;
		int mid = 0;
		int high = a.length - 1;
		while (mid <= high) {
			if (a[mid] == 0) {
				int temp = a[low];
				a[low] = a[mid];
				a[mid] = temp;
				low++;
				mid++;
			} else if (a[mid] == 1) {
				mid++;
			} else {
				int temp = a[high];
				a[high] = a[mid];
				a[mid] = temp;
				high--;
			}
		}
	}

	public int threeSumClosest(ArrayList<Integer> list, int target) {
		int res = 0, diff = Integer.MAX_VALUE;

		Collections.sort(list);
		for (int i = 0; i < list.size() - 2; i++) {
			int j = i + 1, k = list.size() - 1;
			while (j < k) {
				int sum = list.get(i) + list.get(j) + list.get(k);

				if (sum == target)
					return sum;
				else if (sum > target)
					k--;
				else
					j++;

				if (Math.abs(target - sum) < diff) {
					res = sum;
					diff = Math.abs(target - sum);
				}

			}
		}

		return res;
	}

	/**
	 * Amazon Music is working on a new "community radio station" feature which will
	 * allow users to iteratively populate the playlist with their desired songs.
	 * Considering this radio station will also have other scheduled shows to be
	 * aired, and to make sure the community soundtrack will not run over other
	 * scheduled shows, the user-submitted songs will be organized in full-minute
	 * blocks. Users can choose any songs they want to add to the community list,
	 * but only in pairs of songs with durations that add up to a multiple of 60
	 * seconds (e.g. 60, 120, 180). As an attempt to insist on the highest standards
	 * and avoid this additional burden on users, Amazon will let them submit any
	 * number of songs they want, with any duration, and will handle this 60-second
	 * matching internally. Once the user submits their list, given a list of song
	 * durations, calculate the total number of distinct song pairs that can be
	 * chosen by Amazon Music.
	 * 
	 * Link: https://leetcode.com/discuss/interview-question/861432/
	 * 
	 */
	public int numPairsDivisibleBy60(int[] time) {
		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for (int i = 0; i < time.length; i++) {
			int current = time[i] % 60;
			int difference = 60 - current == 60 ? 0 : 60 - current;
			if (map.containsKey(difference)) {
				count += map.get(difference);
			}
			map.put(current, map.getOrDefault(current, 0) + 1);
		}
		return count;
	}

	/**
	 * Given n and m which are the dimensions of a matrix initialized by zeros and
	 * given an array indices where indices[i] = [ri, ci]. For each pair of [ri, ci]
	 * you have to increment all cells in row ri and column ci by 1. Return the
	 * number of cells with odd values in the matrix after applying the increment to
	 * all indices.
	 * 
	 * Link: https://leetcode.com/problems/cells-with-odd-values-in-a-matrix/
	 * 
	 * Hint: Count the rows and columns that appear odd times; Traverse all cells to
	 * get the answer.
	 * 
	 */
	public int oddCells(int n, int m, int[][] indices) {
		boolean[] oddRows = new boolean[n];
		boolean[] oddCols = new boolean[m];
		int cntRow = 0;
		int cntCol = 0;
		for (int[] idx : indices) {
			oddRows[idx[0]] ^= true;
			oddCols[idx[1]] ^= true;
		}
		for (boolean r : oddRows) {
			cntRow += r ? 1 : 0;
		}
		for (boolean c : oddCols) {
			cntCol += c ? 1 : 0;
		}
		// return m * cntRow + n * cntCol - 2 * cntRow * cntCol;
		return (m - cntCol) * cntRow + (n - cntRow) * cntCol;
	}

	/**
	 * Given an array of positive integers arr, calculate the sum of all possible
	 * odd-length subarrays. A subarray is a contiguous subsequence of the array.
	 * Return the sum of all odd-length subarrays of arr.
	 * 
	 * Link: https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
	 * 
	 * Hint: Consider the subarray that contains A[i], we can take 0,1,2..,i
	 * elements on the left, from A[0] to A[i], we have i + 1 choices. we can take
	 * 0,1,2..,n-1-i elements on the right, from A[i] to A[n-1], we have n - i
	 * choices. In total, there are (i + 1) * (n - i) subarrays, that contains A[i].
	 * And there are ((i + 1) * (n - i) + 1) / 2 subarrays with odd length, that
	 * contains A[i]. A[i] will be counted ((i + 1) * (n - i) + 1) / 2 times.
	 */
	public int sumOddLengthSubarrays(int[] arr) {
		int res = 0;
		int n = arr.length;
		for (int i = 0; i < n; ++i) {
			res += ((i + 1) * (n - i) + 1) / 2 * arr[i];
		}
		return res;
	}

	/**
	 * Given an array of integers A sorted in non-decreasing order, return an array
	 * of the squares of each number, also in sorted non-decreasing order.
	 * 
	 * Link: https://leetcode.com/problems/squares-of-a-sorted-array/
	 * 
	 */
	public int[] sortedSquares(int[] A) {
		int low = 0;
		int high = A.length - 1;
		int[] result = new int[high + 1];
		int k = high;
		while (low <= high) {
			if (Math.abs(A[low]) > Math.abs(A[high])) {
				result[k] = A[low] * A[low];
				low++;
			} else {
				result[k] = A[high] * A[high];
				high--;
			}
			k--;
		}
		return result;
	}

	/**
	 * Given an array of numbers arr. A sequence of numbers is called an arithmetic
	 * progression if the difference between any two consecutive elements is the
	 * same. Return true if the array can be rearranged to form an arithmetic
	 * progression, otherwise, return false.
	 * 
	 * Link:
	 * https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
	 * 
	 */
	public boolean canMakeArithmeticProgression(int[] arr) {
		int n = arr.length;
		int a = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		Set<Integer> set = new HashSet<>();

		for (int num : arr) {
			a = Math.min(num, a);
			max = Math.max(num, max);
			set.add(num);
		}

		int d = max - a;
		if (d % (n - 1) != 0) {
			return false;
		}
		d /= n - 1;
		int i = 0;
		while (i < n) {
			if (!set.contains(a)) {
				return false;
			}
			a += d;
			i++;
		}

		return true;
	}

	/**
	 * Students are asked to stand in non-decreasing order of heights for an annual
	 * photo. Return the minimum number of students that must move in order for all
	 * students to be standing in non-decreasing order of height. Notice that when a
	 * group of students is selected they can reorder in any possible way between
	 * themselves and the non selected students remain on their seats.
	 * 
	 * Link: https://leetcode.com/problems/height-checker/
	 *
	 */
	public int heightChecker(int[] heights) {
		int[] heightToFreq = new int[101];

		for (int height : heights) {
			heightToFreq[height]++;
		}

		int result = 0;
		int curHeight = 0;

		for (int i = 0; i < heights.length; i++) {
			while (heightToFreq[curHeight] == 0) {
				curHeight++;
			}

			if (curHeight != heights[i]) {
				result++;
			}
			heightToFreq[curHeight]--;
		}

		return result;
	}

	/**
	 * Given an array A of non-negative integers, half of the integers in A are odd,
	 * and half of the integers are even. Sort the array so that whenever A[i] is
	 * odd, i is odd; and whenever A[i] is even, i is even. You may return any
	 * answer array that satisfies this condition.
	 * 
	 * Link: https://leetcode.com/problems/sort-array-by-parity-ii
	 * 
	 */
	public int[] sortArrayByParityII(int[] A) {
		int i = 0, j = 1, n = A.length;
		while (i < n && j < n) {
			while (i < n && A[i] % 2 == 0) {
				i += 2;
			}
			while (j < n && A[j] % 2 == 1) {
				j += 2;
			}
			if (i < n && j < n) {
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		return A;
	}

	/**
	 * Given an array of integers nums and an integer target, return indices of the
	 * two numbers such that they add up to target. You may assume that each input
	 * would have exactly one solution, and you may not use the same element twice.
	 * You can return the answer in any order.
	 * 
	 * Link: https://leetcode.com/problems/two-sum/
	 */
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target - nums[i])) {
				return new int[] { map.get(target - nums[i]), i };
			}
			map.put(nums[i], i);
		}
		return new int[0];
	}

	/**
	 * Given an integer array, find three numbers whose product is maximum and
	 * output the maximum product.
	 * 
	 * Link: https://leetcode.com/problems/maximum-product-of-three-numbers/
	 * 
	 */
	public int maximumProduct(int[] nums) {
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		int i = 0;
		while (i < nums.length) {
			if (nums[i] > max1) {
				max3 = max2;
				max2 = max1;
				max1 = nums[i];
			} else if (nums[i] > max2) {
				max3 = max2;
				max2 = nums[i];
			} else if (nums[i] > max3) {
				max3 = nums[i];
			}

			if (nums[i] < min1) {
				min2 = min1;
				min1 = nums[i];
			} else if (nums[i] < min2) {
				min2 = nums[i];
			}
			i++;
		}
		return Math.max(max1 * max2 * max3, max1 * min1 * min2);
	}

	/**
	 * Given a m * n matrix of distinct numbers, return all lucky numbers in the
	 * matrix in any order. A lucky number is an element of the matrix such that it
	 * is the minimum element in its row and maximum in its column.
	 * 
	 * Link: https://leetcode.com/problems/lucky-numbers-in-a-matrix/
	 * 
	 */
	public List<Integer> luckyNumbers(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();

		for (int row = 0; row < matrix.length; row++) {
			int minCol = minColInRow(matrix, row);
			int value = matrix[row][minCol];
			if (checkIfMaxInCol(matrix, minCol, value)) {
				result.add(value);
			}
		}

		return result;
	}

	private int minColInRow(int[][] matrix, int row) {
		int minIndex = 0, min = matrix[row][minIndex];
		for (int col = 1; col < matrix[row].length; col++) {
			if (matrix[row][col] < min) {
				min = matrix[row][col];
				minIndex = col;
			}
		}
		return minIndex;
	}

	private boolean checkIfMaxInCol(int[][] matrix, int col, int value) {
		for (int row = 0; row < matrix.length; row++) {
			if (matrix[row][col] > value)
				return false;
		}
		return true;
	}

	/**
	 * Given a non-empty array of digits representing a non-negative integer,
	 * increment one to the integer. The digits are stored such that the most
	 * significant digit is at the head of the list, and each element in the array
	 * contains a single digit. You may assume the integer does not contain any
	 * leading zero, except the number 0 itself.
	 * 
	 * Link: https://leetcode.com/problems/plus-one/
	 * 
	 */
	public int[] plusOne(int[] digits) {
		for (int i = digits.length - 1; i >= 0; i--) {
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			digits[i] = 0;
		}

		int[] newNumber = new int[digits.length + 1];
		newNumber[0] = 1;
		return newNumber;
	}

	/**
	 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y]
	 * represents the coordinate of a point. Check if these points make a straight
	 * line in the XY plane.
	 * 
	 * Link: https://leetcode.com/problems/check-if-it-is-a-straight-line/
	 * 
	 */
	public boolean checkStraightLine(int[][] cordinates) {
		int x1 = cordinates[1][0];
		int y1 = cordinates[1][1];
		int dx = x1 - cordinates[0][0];
		int dy = y1 - cordinates[0][1];
		for (int i = 0; i < cordinates.length; i++) {
			int x = cordinates[i][0];
			int y = cordinates[i][1];
			if (dx * (y - y1) != dy * (x - x1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Given two integer arrays arr1 and arr2, and the integer d, return the
	 * distance value between the two arrays. The distance value is defined as the
	 * number of elements arr1[i] such that there is not any element arr2[j] where
	 * |arr1[i]-arr2[j]| <= d.
	 * 
	 * Link:
	 * https://leetcode.com/problems/find-the-distance-value-between-two-arrays/
	 * 
	 */
	public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
		int res = 0;
		TreeSet<Integer> ts = new TreeSet<>();
		for (int n : arr2)
			ts.add(n);

		for (int n : arr1) {
			Integer higher = ts.ceiling(n);
			Integer lower = ts.floor(n);
			int diff = 0;
			if (higher == null) {
				diff = Math.abs(lower - n);
			} else if (lower == null) {
				diff = Math.abs(higher - n);
			} else {
				diff = Math.min(higher - n, n - lower);
			}
			if (diff > d)
				res++;
		}
		return res;
	}

	/**
	 * A bus has n stops numbered from 0 to n - 1 that form a circle. We know the
	 * distance between all pairs of neighboring stops where distance[i] is the
	 * distance between the stops number i and (i + 1) % n. The bus goes along both
	 * directions i.e. clockwise and counterclockwise. Return the shortest distance
	 * between the given start and destination stops
	 * 
	 * Link: https://leetcode.com/problems/distance-between-bus-stops/
	 */
	public int distanceBetweenBusStops(int[] distance, int start, int destination) {
		if (start > destination) {
			int temp = start;
			start = destination;
			destination = temp;
		}
		int left = 0;
		int right = 0;
		for (int i = start; i < destination; i++) {
			left += distance[i];
		}
		for (int i = 0; i < start; i++) {
			right += distance[i];
		}
		for (int i = distance.length - 1; i >= destination; i--) {
			right += distance[i];
		}
		// System.out.println(left + " " + right);
		return Math.min(left, right);
	}

	/**
	 * Given an array of integers and an integer k, find out whether there are two
	 * distinct indices i and j in the array such that nums[i] = nums[j] and the
	 * absolute difference between i and j is at most k.
	 *
	 * Link: https://leetcode.com/problems/contains-duplicate-ii/
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (i > k) {
				set.remove(nums[i - k - 1]);
			}
			if (!set.add(nums[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * We have n chips, where the position of the ith chip is position[i]. We need
	 * to move all the chips to the same position. In one step, we can change the
	 * position of the ith chip from position[i] to: position[i] + 2 or position[i]
	 * - 2 with cost = 0. position[i] + 1 or position[i] - 1 with cost = 1. Return
	 * the minimum cost needed to move all the chips to the same position.
	 * 
	 * Link:
	 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/
	 * 
	 */
	public int minCostToMoveChips(int[] position) {
		int odd = 0;
		int even = 0;
		int count = 0;
		for (int i = 0; i < position.length; i++) {
			if (position[i] % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}
		if (even > odd) {
			for (int i = 0; i < position.length; i++) {
				if (position[i] % 2 == 1) {
					count++;
				}
			}
		} else {
			for (int i = 0; i < position.length; i++) {
				if (position[i] % 2 == 0) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Given an array of integers nums, you start with an initial positive value
	 * startValue. In each iteration, you calculate the step by step sum of
	 * startValue plus elements in nums (from left to right). Return the minimum
	 * positive value of startValue such that the step by step sum is never less
	 * than 1.
	 * 
	 * Link:
	 * https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum/
	 * 
	 */
	public int minStartValue(int[] nums) {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (sum < min) {
				min = sum;
			}
		}
		if (min < 0) {
			return 1 - min;
		} else {
			return 1;
		}
	}

	/**
	 * Given a rows x cols matrix mat, where mat[i][j] is either 0 or 1, return the
	 * number of special positions in mat. A position (i,j) is called special if
	 * mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and
	 * columns are 0-indexed).
	 * 
	 * Link: https://leetcode.com/problems/special-positions-in-a-binary-matrix/
	 * 
	 */
	public int numSpecial(int[][] mat) {
		int[] row = new int[mat.length];
		int[] column = new int[mat[0].length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 1) {
					row[i]++;
					column[j]++;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				if (mat[i][j] == 1 && row[i] == 1 && column[j] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * You are given an array nums of non-negative integers. nums is considered
	 * special if there exists a number x such that there are exactly x numbers in
	 * nums that are greater than or equal to x. Notice that x does not have to be
	 * an element in nums. Return x if the array is special, otherwise, return -1.
	 * It can be proven that if nums is special, the value for x is unique.
	 * 
	 * Link:
	 * https://leetcode.com/problems/special-array-with-x-elements-greater-than-or-equal-x/
	 * 
	 */
	public int specialArray(int[] nums) {
		Arrays.sort(nums);
		if (nums[0] >= nums.length) {
			return nums.length;
		}
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] >= nums.length - i && nums[i - 1] < nums.length - i) {
				return nums.length - i;
			}
		}
		return -1;
	}

	/**
	 * Link: https://leetcode.com/problems/day-of-the-week/
	 * 
	 */
	public String dayOfTheWeek(int d, int m, int y) {
		String[] days = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		if (m < 3) {
			m += 12;
			y -= 1;
		}
		int c = y / 100;
		y = y % 100;
		int w = (c / 4 - 2 * c + y + y / 4 + 13 * (m + 1) / 5 + d - 1) % 7;
		return days[(w + 7) % 7];
	}

	/**
	 * Given an array of integers arr, replace each element with its rank. The rank
	 * represents how large the element is. The rank has the following rules: Rank
	 * is an integer starting from 1. The larger the element, the larger the rank.
	 * If two elements are equal, their rank must be the same. Rank should be as
	 * small as possible.
	 * 
	 * Link: https://leetcode.com/problems/rank-transform-of-an-array/
	 * 
	 */
	public int[] arrayRankTransform(int[] arr) {
		int[] curr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(curr);
		int count = 1;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < curr.length; i++) {
			if (map.get(curr[i]) == null) {
				map.put(curr[i], count);
				count++;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = map.get(arr[i]);
		}
		return arr;
	}

	/**
	 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] =
	 * [c, d] if and only if either (a==c and b==d), or (a==d and b==c) - that is,
	 * one domino can be rotated to be equal to another domino. Return the number of
	 * pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is
	 * equivalent to dominoes[j].
	 *
	 * Link: https://leetcode.com/problems/number-of-equivalent-domino-pairs/
	 *
	 */
	public int numEquivDominoPairs(int[][] dominoes) {
		Map<Integer, Integer> count = new HashMap<>();
		int res = 0;
		for (int[] d : dominoes) {
			int k = Math.min(d[0], d[1]) * 10 + Math.max(d[0], d[1]);
			count.put(k, count.getOrDefault(k, 0) + 1);
		}
		for (int v : count.values()) {
			res += v * (v - 1) / 2;
		}
		return res;
	}

	/**
	 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all
	 * elements in arr2 are also in arr1. Sort the elements of arr1 such that the
	 * relative ordering of items in arr1 are the same as in arr2. Elements that
	 * don't appear in arr2 should be placed at the end of arr1 in ascending order.
	 * 
	 * Link: https://leetcode.com/problems/relative-sort-array/
	 * 
	 */
	public int[] relativeSortArray(int[] arr1, int[] arr2) {
		int[] count = new int[1001];
		for (int i = 0; i < arr1.length; i++) {
			count[arr1[i]]++;
		}
		int k = 0;
		for (int i = 0; i < arr2.length; i++) {
			while (count[arr2[i]] > 0) {
				arr1[k] = arr2[i];
				k++;
				count[arr2[i]]--;
			}
		}
		for (int i = 0; i < 1001; i++) {
			while (count[i] > 0) {
				arr1[k++] = i;
				count[i]--;
			}
		}
		return arr1;
	}

	/**
	 * Given a non-empty array of non-negative integers nums, the degree of this
	 * array is defined as the maximum frequency of any one of its elements. Your
	 * task is to find the smallest possible length of a (contiguous) subarray of
	 * nums, that has the same degree as nums.
	 * 
	 * Link: https://leetcode.com/problems/degree-of-an-array/
	 * 
	 */
	public int findShortestSubArray(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> first = new HashMap<>();
		int degree = 0;
		int min = 0;
		for (int i = 0; i < nums.length; i++) {
			first.putIfAbsent(nums[i], i);
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
			if (map.get(nums[i]) > degree) {
				degree = map.get(nums[i]);
				min = i - first.get(nums[i]) + 1;
			} else if (map.get(nums[i]) == degree) {
				min = Math.min(i - first.get(nums[i]) + 1, min);
			}
		}
		return min;
	}

	/**
	 * Given a fixed length array arr of integers, duplicate each occurrence of
	 * zero, shifting the remaining elements to the right. Note that elements beyond
	 * the length of the original array are not written. Do the above modifications
	 * to the input array in place, do not return anything from your function.
	 * 
	 * Link: https://leetcode.com/problems/duplicate-zeros/
	 * 
	 */
	public void duplicateZeros(int[] arr) {
		int zeros = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				zeros++;
			}
		}
		int length = arr.length + zeros;
		for (int i = arr.length - 1, j = length - 1; i < j; i--, j--) {
			if (arr[i] != 0) {
				if (j < arr.length) {
					arr[j] = arr[i];
				}
			} else {
				if (j < arr.length) {
					arr[j] = arr[i];
				}
				j--;
				if (j < arr.length) {
					arr[j] = arr[i];
				}
			}
		}
	}

	/**
	 * In MATLAB, there is a very useful function called 'reshape', which can
	 * reshape a matrix into a new one with different size but keep its original
	 * data. You're given a matrix represented by a two-dimensional array, and two
	 * positive integers r and c representing the row number and column number of
	 * the wanted reshaped matrix, respectively. The reshaped matrix need to be
	 * filled with all the elements of the original matrix in the same
	 * row-traversing order as they were. If the 'reshape' operation with given
	 * parameters is possible and legal, output the new reshaped matrix; Otherwise,
	 * output the original matrix.
	 * 
	 * Link: https://leetcode.com/problems/reshape-the-matrix/
	 * 
	 */
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		int[][] matrix = new int[r][c];
		int col = 0;
		int row = 0;
		int mul = nums.length * nums[0].length;
		int newMul = r * c;
		if (newMul != mul) {
			return nums;
		}
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				if (col == c) {
					row++;
					col = 0;
				}
				matrix[row][col] = nums[i][j];
				col++;
			}
		}
		return matrix;
	}

	/**
	 * Let's define a function f(s) over a non-empty string s, which calculates the
	 * frequency of the smallest character in s. For example, if s = "dcce" then
	 * f(s) = 2 because the smallest character is "c" and its frequency is 2. Now,
	 * given string arrays queries and words, return an integer array answer, where
	 * each answer[i] is the number of words such that f(queries[i]) < f(W), where W
	 * is a word in words.
	 * 
	 * Link:
	 * https://leetcode.com/problems/compare-strings-by-frequency-of-the-smallest-character/
	 * 
	 */
	public int[] numSmallerByFrequency(String[] queries, String[] words) {
		int[] querArr = new int[queries.length];
		int[] wordArr = new int[words.length];
		for (int i = 0; i < queries.length; i++) {
			querArr[i] = getFreq(queries[i]);
		}
		for (int i = 0; i < words.length; i++) {
			wordArr[i] = getFreq(words[i]);
		}
		Arrays.sort(wordArr);
		for (int i = 0; i < querArr.length; i++) {
			int count = 0;
			for (int j = wordArr.length - 1; j >= 0; j--) {
				if (wordArr[j] > querArr[i]) {
					count++;
				} else {
					break;
				}
			}
			querArr[i] = count;
		}
		return querArr;
	}

	private int getFreq(String x) {
		int max = 0;
		int curr = 999;
		for (int i = 0; i < x.length(); i++) {
			if (x.charAt(i) < curr) {
				curr = x.charAt(i);
				max = 1;
			} else if (x.charAt(i) == curr) {
				max++;
			}
		}
		return max;
	}

	/**
	 * Alice and Bob have candy bars of different sizes: A[i] is the size of the
	 * i-th bar of candy that Alice has, and B[j] is the size of the j-th bar of
	 * candy that Bob has. Since they are friends, they would like to exchange one
	 * candy bar each so that after the exchange, they both have the same total
	 * amount of candy. (The total amount of candy a person has is the sum of the
	 * sizes of candy bars they have.) Return an integer array ans where ans[0] is
	 * the size of the candy bar that Alice must exchange, and ans[1] is the size of
	 * the candy bar that Bob must exchange. If there are multiple answers, you may
	 * return any one of them. It is guaranteed an answer exists.
	 * 
	 * Link: https://leetcode.com/problems/fair-candy-swap/
	 * 
	 */
	public int[] fairCandySwap(int[] A, int[] B) {
		int sumA = 0;
		int sumB = 0;
		for (int i = 0; i < A.length; i++) {
			sumA += A[i];
		}
		for (int i = 0; i < B.length; i++) {
			sumB += B[i];
		}
		int diff = (sumA - sumB) / 2;
		HashSet<Integer> set = new HashSet<>();
		for (int a : A) {
			set.add(a);
		}
		for (int b : B) {
			if (set.contains(b + diff)) {
				return new int[] { b + diff, b };
			}
		}
		return new int[0];
	}

	/**
	 * Given a non-negative integer numRows, generate the first numRows of Pascal's
	 * triangle.
	 * 
	 * Link: https://leetcode.com/problems/pascals-triangle/
	 * 
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> list = new ArrayList<>();
		if (numRows == 0) {
			return list;
		}
		for (int i = 0; i < numRows; i++) {
			list.add(new ArrayList<>());
		}
		list.get(0).add(1);
		if (numRows == 1) {
			return list;
		}
		list.get(1).add(1);
		list.get(1).add(1);
		if (numRows == 2) {
			return list;
		}
		int i;
		int j;
		for (int k = 2; k < numRows; k++) {
			list.get(k).add(1);
			i = 0;
			j = i + 1;
			while (j < list.get(k - 1).size()) {
				list.get(k).add(list.get(k - 1).get(i) + list.get(k - 1).get(j));
				i++;
				j++;
			}
			list.get(k).add(1);
		}
		return list;
	}

}
