package greedy;

public class ArraysGreedyMain {

	public static void main(String[] args) {
		int[] arrival = new int[] { 1, 3, 2, 5, 8, 5 };
		int[] destination = new int[] { 2, 4, 6, 7, 9, 9 };
		ArraysGreedy greedyOperations = new ArraysGreedy();
		int maxtTasks = greedyOperations.getMaxTasksDone(arrival, destination, arrival.length);
		System.out.println("Max tasks = " + maxtTasks);
		int maxPlatforms = greedyOperations.maxPlatformsNeeded(arrival, destination, arrival.length);
		System.out.println("Max platforms needed = " + maxPlatforms);
		greedyOperations.nMeetingsInOneRoom(arrival, destination, arrival.length);
		greedyOperations.minCoins(new int[] { 1, 2, 5 }, 11);
		int[] price = new int[] { 60, 100, 120 };
		int[] weight = new int[] { 10, 20, 30 };
		greedyOperations.fractionalKnapsack(weight, price, 50);
	}

}
