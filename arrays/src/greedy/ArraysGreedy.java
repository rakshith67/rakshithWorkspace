package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArraysGreedy {

	/**
	 * Given N activities with their start and finish times. Select the maximum
	 * number of activities that can be performed by a single person, assuming that
	 * a person can only work on a single activity at a time. Note : The start time
	 * and end time of two activities may coincide. Link:
	 * https://practice.geeksforgeeks.org/problems/activity-selection/0
	 */
	public int getMaxTasksDone(int[] arrival, int[] destination, int size) {
		Activity[] activities = new Activity[size];
		for (int i = 0; i < size; i++) {
			activities[i] = new Activity(arrival[i], destination[i]);
		}
//		Arrays.sort(activities, (element1, element2) -> {
//			return element1.finish - element2.finish;
//		});
		Arrays.sort(activities);
		int currentDestination = 0;
		int maxTasks = 0;
		for (int i = 0; i < size; i++) {
			if (activities[i].start >= currentDestination) {
				currentDestination = activities[i].finish;
				maxTasks++;
			}
		}
		return maxTasks;
	}

	/**
	 * Given arrival and departure times of all trains that reach a railway station.
	 * Your task is to find the minimum number of platforms required for the railway
	 * station so that no train waits. Note: Consider that all the trains arrive on
	 * the same day and leave on the same day. Also, arrival and departure times
	 * will not be same for a train, but we can have arrival time of one train equal
	 * to departure of the other. In such cases, we need different platforms, i.e at
	 * any given instance of time, same platform can not be used for both departure
	 * of a train and arrival of another. Link:
	 * https://practice.geeksforgeeks.org/problems/minimum-platforms/0
	 * 
	 */
	public int maxPlatformsNeeded(int[] arrival, int[] destination, int size) {
		int maxPlatforms = 1;
		int currentOccupiedPlatforms = 1;
		int i = 1;
		int j = 0;
		Arrays.sort(arrival);
		Arrays.sort(destination);
		while (i < size && j < size) {
			if (arrival[i] <= destination[j]) {
				currentOccupiedPlatforms++;
				i++;
				if (currentOccupiedPlatforms > maxPlatforms) {
					maxPlatforms = currentOccupiedPlatforms;
				}
			} else if (arrival[i] > destination[j]) {
				currentOccupiedPlatforms--;
				j++;
			}
		}
		return maxPlatforms;
	}

	/**
	 * There is one meeting room in a firm. There are N meetings in the form of
	 * (S[i], F[i]) where S[i] is start time of meeting i and F[i] is finish time of
	 * meeting i. What is the maximum number of meetings that can be accommodated in
	 * the meeting room? Link:
	 * https://practice.geeksforgeeks.org/problems/n-meetings-in-one-room/0
	 * 
	 */
	public void nMeetingsInOneRoom(int[] arrival, int[] destination, int size) {
		Activity[] activities = new Activity[size];
		for (int i = 0; i < size; i++) {
			activities[i] = new Activity(arrival[i], destination[i], i + 1);
		}
		Arrays.sort(activities);
		int currentDestination = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			if (activities[i].start >= currentDestination) {
				currentDestination = activities[i].finish;
				list.add(activities[i].number);
			}
		}
		for (Integer time : list) {
			System.out.print(time + " ");
		}
		System.out.println();
	}

	/**
	 * Given an array A[ ] denoting heights of N towers and a positive integer K,
	 * modify the heights of each tower either by increasing or decreasing them by K
	 * only once and then find out the minimum difference of the heights of shortest
	 * and longest towers. Link:
	 * https://practice.geeksforgeeks.org/problems/minimize-the-heights/0
	 * 
	 */
	public void minimizeHeights(int[] heights, int size, int k) {
		Arrays.sort(heights);
		int small = heights[0] + k;
		int big = heights[size - 1] - k;
		if (small > big) {
			int temp = small;
			small = big;
			big = temp;
		}
		int result = heights[size - 1] - heights[0];
		for (int i = 0; i < size; i++) {
			int add = heights[i] + k;
			int subtract = heights[i] - k;
			if (subtract >= small || add <= big) {
				continue;
			}
			if (big - subtract <= add - small) {
				small = subtract;
			} else {
				big = add;
			}
		}
		System.out.println(Math.min(result, big - small));
	}

	/**
	 * You are given N pairs of numbers. In every pair, the first number is always
	 * smaller than the second number. A pair (c, d) can follow another pair (a, b)
	 * if b < c. Chain of pairs can be formed in this fashion. You have to find the
	 * longest chain which can be formed from the given set of pairs. Link:
	 * https://practice.geeksforgeeks.org/problems/max-length-chain/1
	 * 
	 */
	int maxChainLength(Pair arr[], int n) {
		Arrays.sort(arr, new Comparator<Pair>() {
			public int compare(Pair o1, Pair o2) {
				return o1.y - o2.y;
			}
		});
		int currentLast = -1;
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i].x > currentLast) {
				result++;
				currentLast = arr[i].y;
			}
		}
		return result;
	}

	/**
	 * Given a collection of intervals, find the minimum number of intervals you
	 * need to remove to make the rest of the intervals non-overlapping. Link:
	 * https://leetcode.com/problems/non-overlapping-intervals/
	 * 
	 */
	public int eraseOverlapIntervals(int[][] intervals) {
		Activity[] activities = new Activity[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			activities[i] = new Activity(intervals[i][0], intervals[i][1]);
		}
		Arrays.sort(activities);
		int currentFinish = Integer.MIN_VALUE;
		int count = 0;
		for (int i = 0; i < intervals.length; i++) {
			if (activities[i].start >= currentFinish) {
				currentFinish = activities[i].finish;
			} else {
				count++;
			}
		}
		return count;
	}

	private int[] cache;

	/**
	 * Given a list of coins of distinct denominations and total amount of money.
	 * Output the minimum number of coins required to make up that amount. Output -1
	 * if that money cannot be made up using given coins. You may assume that there
	 * are infinite numbers of coins of each type. Link:
	 * https://practice.geeksforgeeks.org/problems/min-coin/0,
	 * https://leetcode.com/problems/coin-change/submissions/
	 * 
	 */
	public int minCoins(int[] coins, int amount) {
		cache = new int[amount + 1];
		cache[0] = 1;
		return util(coins, amount);
	}

	public int util(int[] coins, int amount) {
		if (amount == 0) {
			return 0;
		} else if (amount < 0) {
			return Integer.MAX_VALUE;
		}
		if (cache[amount] != 0) {
			return cache[amount];
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < coins.length; i++) {
			int res = util(coins, amount - coins[i]);
			if (res != Integer.MAX_VALUE) {
				ans = Math.min(ans, res + 1);
			}
		}
		cache[amount] = ans;
		return ans;
	}

	/**
	 * Given weights and values of N items, we need to put these items in a knapsack
	 * of capacity W to get the maximum total value in the knapsack. Note: Unlike
	 * 0/1 knapsack, you are allowed to break the item. Link:
	 * https://practice.geeksforgeeks.org/problems/fractional-knapsack/0
	 * 
	 */
	public void fractionalKnapsack(int[] weight, int[] price, int maxWeight) {
		Weight[] weights = new Weight[weight.length];
		for (int i = 0; i < weight.length; i++) {
			weights[i] = new Weight(weight[i], price[i]);
		}
		Arrays.sort(weights);
		double maxPrice = 0;
		for (int i = 0; i < weights.length; i++) {
			int currentWeight = weights[i].quantity;
			int currentPrice = weights[i].price;
			if (currentWeight != 0) {
				if (currentWeight <= maxWeight) {
					maxPrice += currentPrice;
					maxWeight -= currentWeight;
				} else {
					maxPrice += weights[i].density * maxWeight;
					break;
				}
			}
		}
		String strDouble = String.format("%.2f", maxPrice);
		System.out.println(strDouble);
	}
}
