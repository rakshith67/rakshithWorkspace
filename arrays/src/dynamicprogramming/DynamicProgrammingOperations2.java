package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgrammingOperations2 {

	/**
	 * Samu is in super market and in a mood to do a lot of shopping. She needs to
	 * buy shirts, pants and shoes for herself and her family. There are N different
	 * shops. Each shop contains all these three items but at different prices. Now
	 * Samu has a strategy that she won't buy the same item from the current shop if
	 * she had already bought that item from the shop adjacent to the current shop.
	 * Now Samu is confused, because although she want to follow her strategy
	 * strictly but at the same time she want to minimize the total money she spends
	 * on shopping. Being a good programmer, she asks for your help. You are
	 * provided description about all N shops i.e costs of all three items in each
	 * shop. You need to help Samu find minimum money that she needs to spend such
	 * that she buys exactly one item from every shop. Link:
	 * https://www.hackerearth.com/problem/algorithm/samu-and-shopping-165-5691d19b/discussion/java-simple-concise--2fb5b73d/ @
	 *
	 */
	public void minAmountShopping(int[][] array) {
		System.out.println("Min amount " + minAmount(array, array.length - 1));
	}

	private int minAmount(int[][] array, int row) {
		for (int i = 1; i <= row; i++) {
			array[i][0] += Math.min(array[i - 1][1], array[i - 1][2]);
			array[i][1] += Math.min(array[i - 1][0], array[i - 1][2]);
			array[i][2] += Math.min(array[i - 1][1], array[i - 1][0]);
		}
		return Math.min(array[row][0], Math.min(array[row][1], array[row][2]));
	}

	/**
	 * iven a list of non negative integers, arrange them such that they form the
	 * largest number. For example: Given [3, 30, 34, 5, 9], the largest formed
	 * number is 9534330. Link:
	 * https://www.interviewbit.com/problems/largest-number/ Note: The result may be
	 * very large, so you need to return a string instead of an integer.
	 * 
	 */
	public String largestNumber(final List<Integer> list) {
		String[] arr = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			arr[i] = String.valueOf(list.get(i));
		}

		Arrays.sort(arr, (c, b) -> {
			return (b + c).compareTo(c + b);
		});

		StringBuilder sb = new StringBuilder();
		for (String s : arr) {
			sb.append(s);
		}
		if (sb.charAt(0) == '0') {
			return String.valueOf(0);
		}
		return sb.toString();
	}

	/**
	 * Given a positive integer n and a string s consisting only of letters D or I,
	 * you have to find any permutation of first n positive integer that satisfy the
	 * given input string. D means the next number is smaller, while I means the
	 * next number is greater. Link:
	 * https://www.interviewbit.com/problems/find-permutation/
	 * 
	 */
	public ArrayList<Integer> findPerm(final String A, int B) {
		ArrayList<Integer> list = new ArrayList<>();
		if (B != 0) {
			int minimum = 1;
			int maximum = B;
			for (int i = 0; i < B - 1; i++) {
				if (A.charAt(i) == 'I') {
					list.add(minimum);
					minimum++;
				} else {
					list.add(maximum);
					maximum--;
				}
			}
			list.add(minimum);
		}
		return list;
	}

	/**
	 * Given the position of a Bishop (A, B) on an 8 * 8 chessboard. Your task is to
	 * count the total number of squares that can be visited by the Bishop in one
	 * move. The position of the Bishop is denoted using row and column number of
	 * the chessboard. Link:
	 * https://www.interviewbit.com/problems/total-moves-for-bishop/
	 * 
	 */
	public int maxPointsBishop(int A, int B) {
		int result = 0;
		result += Math.min(Math.abs(1 - A), Math.abs(1 - B));
		result += Math.min(Math.abs(8 - A), Math.abs(8 - B));
		result += Math.min(Math.abs(1 - A), Math.abs(8 - B));
		result += Math.min(Math.abs(8 - A), Math.abs(1 - B));
		return result;
	}

	/**
	 * There is a list of sorted integers from 1 to n. Starting from left to right,
	 * remove the first number and every other number afterward until you reach the
	 * end of the list. Repeat the previous step again, but this time from right to
	 * left, remove the right most number and every other number from the remaining
	 * numbers. We keep repeating the steps again, alternating left to right and
	 * right to left, until a single number remains. Find the last number that
	 * remains starting with a list of length n. Link:
	 * https://leetcode.com/problems/elimination-game/
	 */
	public int eliminationGame(int n) {
		boolean leftDirection = true;
		int head = 1;
		int step = 1;
		int remaining = n;
		while (remaining > 1) {
			if (leftDirection || remaining % 2 == 1) {
				head += step;
			}
			remaining /= 2;
			step *= 2;
			leftDirection = !leftDirection;
		}
		return head;
	}

	public int josepheus(int n, int k) {
		if (n == 1) {
			return 1;
		}
		return (josepheus(n - 1, k) + k - 1) % (n + 1);
	}

}
