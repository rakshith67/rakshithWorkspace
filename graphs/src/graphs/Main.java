package graphs;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		int vertices = 6;
		List<ArrayList<Integer>> undirectedEdges = new ArrayList<>();
		createUnWeightedUnDirectedGraph(undirectedEdges);
		List<ArrayList<Integer>> directedWeightdEdges = new ArrayList<>();
		createWeightedDirectedGraph(directedWeightdEdges);
		Graph weightedGraph = new Graph(directedWeightdEdges, vertices);
		Graph unWeightedGraph = new Graph(undirectedEdges, vertices);
		GraphOperations graphOperations = new GraphOperations();

		List<Integer> dfsList = graphOperations.dfs(unWeightedGraph);
		for (Integer integer : dfsList) {
			System.out.print(integer + " ");
		}
		System.out.println();

		List<Integer> bfsList = graphOperations.bfs(unWeightedGraph);
		for (Integer integer : bfsList) {
			System.out.print(integer + " ");
		}
		System.out.println();

		int[] result = graphOperations.topologicalSort(unWeightedGraph);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println();

		int[] shortestPath = graphOperations.dijkstra(weightedGraph, 0, 6);
		for (int i = 0; i < 6; i++) {
			System.out.print(shortestPath[i] + " ");
		}
		System.out.println();

		graphOperations.negativeCycle(weightedGraph, 6);

		int[][] allPairShortestPath = graphOperations.floydWarshallAgorithmForAPSP(weightedGraph, 6);
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(allPairShortestPath[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void createWeightedDirectedGraph(List<ArrayList<Integer>> directedWeightdEdges) {
		ArrayList<Integer> list0 = new ArrayList<>();
		list0.add(0);
		list0.add(0);
		list0.add(0);
		list0.add(0);
		list0.add(2);
		list0.add(6);
		directedWeightdEdges.add(list0);
		ArrayList<Integer> list1 = new ArrayList<>();
		list1.add(4);
		list1.add(0);
		list1.add(0);
		list1.add(0);
		list1.add(0);
		list1.add(0);
		directedWeightdEdges.add(list1);
		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(0);
		list2.add(0);
		list2.add(0);
		list2.add(1);
		list2.add(0);
		list2.add(0);
		directedWeightdEdges.add(list2);
		ArrayList<Integer> list3 = new ArrayList<>();
		list3.add(0);
		list3.add(0);
		list3.add(0);
		list3.add(0);
		list3.add(5);
		list3.add(0);
		directedWeightdEdges.add(list3);
		ArrayList<Integer> list4 = new ArrayList<>();
		list4.add(0);
		list4.add(3);
		list4.add(0);
		list4.add(0);
		list4.add(0);
		list4.add(0);
		directedWeightdEdges.add(list4);
		ArrayList<Integer> list5 = new ArrayList<>();
		list5.add(0);
		list5.add(0);
		list5.add(7);
		list5.add(0);
		list5.add(0);
		list5.add(0);
		directedWeightdEdges.add(list5);
	}

	private static void createUnWeightedUnDirectedGraph(List<ArrayList<Integer>> unWeightedEdges) {
		ArrayList<Integer> list0 = new ArrayList<>();
		list0.add(1);
		list0.add(4);
		list0.add(5);
		unWeightedEdges.add(list0);
		ArrayList<Integer> list1 = new ArrayList<>();
		list1.add(0);
		list1.add(4);
		unWeightedEdges.add(list1);
		ArrayList<Integer> list2 = new ArrayList<>();
		list2.add(3);
		list2.add(5);
		unWeightedEdges.add(list2);
		ArrayList<Integer> list3 = new ArrayList<>();
		list3.add(2);
		list3.add(4);
		unWeightedEdges.add(list3);
		ArrayList<Integer> list4 = new ArrayList<>();
		list4.add(0);
		list4.add(1);
		list4.add(3);
		unWeightedEdges.add(list4);
		ArrayList<Integer> list5 = new ArrayList<>();
		list5.add(0);
		list5.add(2);
		unWeightedEdges.add(list5);
	}

}
