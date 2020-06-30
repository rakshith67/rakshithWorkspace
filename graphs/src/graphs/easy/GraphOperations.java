package graphs.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class GraphOperations {

	private int index;

	/**
	 * BFS of a graph. Link:
	 * https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
	 */
	public List<Integer> bfs(Graph graph) {
		int vertices = graph.getVertices();
		boolean[] visited = new boolean[vertices];
		ArrayList<Integer> list = new ArrayList<>();
		Arrays.fill(visited, false);
		List<ArrayList<Integer>> graphList = graph.getEdges();

		Deque<Integer> deque = new ArrayDeque<>();
		deque.push(0);
		visited[0] = true;
		while (!deque.isEmpty()) {
			int current = deque.pop();
			list.add(current);
			List<Integer> adjacents = graphList.get(current);
			for (int i = 0; i < adjacents.size(); i++) {
				int integer = adjacents.get(i);
				if (visited[integer] == false) {
					deque.push(integer);
					visited[integer] = true;
				}
			}
		}
		return list;
	}

	/**
	 * DFS of a graph. Link:
	 * https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
	 */
	public ArrayList<Integer> dfs(Graph graph) {
		int vertices = graph.getVertices();
		List<ArrayList<Integer>> graphList = graph.getEdges();
		ArrayList<Integer> list = new ArrayList<>();
		boolean[] visited = new boolean[vertices];
		Arrays.fill(visited, false);
		dfs_recursive(0, graphList, list, visited);
		return list;
	}

	void dfs_recursive(int current, List<ArrayList<Integer>> graph, ArrayList<Integer> list, boolean[] visited) {
		list.add(current);
		visited[current] = true;
		ArrayList<Integer> adjacents = graph.get(current);
		for (Integer adjacent : adjacents) {
			if (!visited[adjacent]) {
				dfs_recursive(adjacent, graph, list, visited);
			}
		}
	}

	/**
	 * Topological sort. Sorts with the dependency.
	 * 
	 * Link: https://practice.geeksforgeeks.org/problems/topological-sort/1
	 */
	public int[] topologicalSort(Graph graph) {
		int vertices = graph.getVertices();
		index = vertices - 1;
		int[] result = new int[vertices];
		boolean[] visited = new boolean[vertices];
		Arrays.fill(visited, false);

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				formTopoList(i, graph.getEdges(), result, visited);
			}
		}
		return result;
	}

	private void formTopoList(int source, List<ArrayList<Integer>> graph, int[] result, boolean[] visited) {
		visited[source] = true;
		for (Integer integer : graph.get(source)) {
			if (!visited[integer]) {
				formTopoList(integer, graph, result, visited);
			}
		}
		result[index--] = source;
	}

	/**
	 * Dijkstra's algorithm to find the shortest path between vertices. Link:
	 * https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
	 * 
	 */
	public int[] dijkstra(Graph graph, int src, int V) {
		List<ArrayList<Integer>> g = graph.getEdges();
		int[] minimumDistance = new int[V];
		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			minimumDistance[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		minimumDistance[src] = 0;
		int currentVertex = src;
		while (currentVertex != -1) {
			ArrayList<Integer> list = g.get(currentVertex);
			visited[currentVertex] = true;
			for (int i = 0; i < V; i++) {
				int edge = list.get(i);
				if (!visited[i] && edge != 0) {
					int distance = minimumDistance[currentVertex] + edge;
					if (distance < minimumDistance[i]) {
						minimumDistance[i] = distance;
					}
				}
			}
			currentVertex = getMinimumDistanceVertex(minimumDistance, visited);
		}
		return minimumDistance;
	}

	private int getMinimumDistanceVertex(int[] minimumDistance, boolean[] visited) {
		int minimum = Integer.MAX_VALUE;
		int minimumIndex = -1;
		for (int i = 0; i < minimumDistance.length; i++) {
			if (!visited[i] && minimumDistance[i] < minimum) {
				minimum = minimumDistance[i];
				minimumIndex = i;
			}
		}
		return minimumIndex;
	}

	/**
	 * Bellman-Ford algorithm to find the negative cycle in the graph returns true.
	 * Link: https://practice.geeksforgeeks.org/problems/negative-weight-cycle/0
	 *
	 */
	public void negativeCycle(Graph weightedGraph, int V) {
		List<ArrayList<Integer>> edgesOfGraph = weightedGraph.getEdges();
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (edgesOfGraph.get(i).get(j) != 0) {
					Edge edge = new Edge(i, j, edgesOfGraph.get(i).get(j));
					edges.add(edge);
				}
			}
		}
		int[] distance = new int[V];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[0] = 0;
		for (int i = 0; i < V - 1; i++) {
			for (Edge edge : edges) {
				int source = edge.source;
				int destination = edge.destination;
				int weight = edge.weight;
				if (distance[source] != Integer.MAX_VALUE && distance[destination] > distance[source] + weight) {
					distance[destination] = distance[source] + weight;
				}
			}
		}
		for (Edge edge : edges) {
			int source = edge.source;
			int destination = edge.destination;
			int weight = edge.weight;
			if (distance[source] != Integer.MAX_VALUE && distance[destination] > distance[source] + weight) {
				System.out.println("Is Negative Cycle");
			}
		}
		System.out.println("Not a Negative Cycle");
	}

	/**
	 * FLoyd Warshall's algorithm to find the all pair shortest path of vertices.
	 * Link:
	 * https://practice.geeksforgeeks.org/problems/implementing-floyd-warshall/0
	 * 
	 */
	public int[][] floydWarshallAgorithmForAPSP(Graph graph, int V) {
		List<ArrayList<Integer>> edges = graph.getEdges();
		int[][] distance = new int[V][V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				if (i != j) {
					if (edges.get(i).get(j) == 0) {
						distance[i][j] = Integer.MAX_VALUE;
					} else {
						distance[i][j] = edges.get(i).get(j);
					}
				}
			}
		}
		for (int k = 0; k < V; k++) {
			for (int i = 0; i < V; i++) {
				for (int j = 0; j < V; j++) {
					if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE
							&& distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		return distance;
	}

	/**
	 * Kruskal's Algorithm to find the minimum spanning tree and its weight. Link:
	 * https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1
	 * 
	 */
	public int kruskalsAlgorithmMinSpanningTreeWeight(Graph weightedGraph, int vertices) {
		List<ArrayList<Integer>> edgesOfGraph = weightedGraph.getEdges();
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (edgesOfGraph.get(i).get(j) != Integer.MAX_VALUE) {
					Edge edge = new Edge(i, j, edgesOfGraph.get(i).get(j));
					edges.add(edge);
				}
			}
		}
		edges.sort(null);
		ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
		DisjointSet disjointSet = new DisjointSet();
		disjointSet.makeSet(sets, vertices);
		int treeWeight = 0;
		for (Edge edge : edges) {
			int index1 = disjointSet.find(sets, edge.source);
			int index2 = disjointSet.find(sets, edge.destination);
			if (index1 != index2) {
				treeWeight += edge.weight;
				disjointSet.merge(sets, index1, index2);
			}
		}
		return treeWeight;
	}

	/**
	 * Prim's Algorithm to find the minimum spanning tree and its weight. Link:
	 * https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1
	 * 
	 */
	public int primsAlgorithmMinSpanningTreeWeight(Graph graph, int vertices) {
		List<ArrayList<Integer>> edges = graph.getEdges();
		int[] minimumDistance = new int[vertices];
		boolean[] visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			minimumDistance[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		minimumDistance[0] = 0;
		int currentVertex = 0;
		while (currentVertex != -1) {
			ArrayList<Integer> list = edges.get(currentVertex);
			visited[currentVertex] = true;
			for (int i = 0; i < vertices; i++) {
				int edge = list.get(i);
				if (!visited[i] && edge != 0) {
					if (edge < minimumDistance[i]) {
						minimumDistance[i] = edge;
					}
				}
			}
			currentVertex = getMinimumDistanceVertex(minimumDistance, visited);
		}
		int treeWeight = 0;
		for (int i = 0; i < vertices; i++) {
			treeWeight += minimumDistance[i];
		}
		return treeWeight;
	}

	/**
	 * Checks whether the given undirected graph is cyclic or not. The point is
	 * using back edge, if destination vertex of the edge has edge from any other
	 * vertex than source vertex it is cyclic. Used BFS to check cycle. Link:
	 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
	 * 
	 */
	public boolean isCyclicUndirectedGraph(ArrayList<ArrayList<Integer>> edges, int vertices) {
		int[] visited = new int[vertices];
		Arrays.fill(visited, -1);
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < vertices; i++) {
			if (visited[i] == -1) {
				deque.push(0);
				visited[0] = 0;
				while (!deque.isEmpty()) {
					int current = deque.pop();
					visited[current] = 1;
					List<Integer> adjacents = edges.get(current);
					for (int j = 0; j < adjacents.size(); j++) {
						int integer = adjacents.get(j);
						if (visited[integer] == -1) {
							deque.push(integer);
							visited[integer] = 0;
						} else if (visited[integer] == 0) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Detects the cycle in a directed graph. There is a cycle in a graph only if
	 * there is a back edge present in the graph. A back edge is an edge that is
	 * from a node to itself (self-loop) or one of its ancestors in the tree
	 * produced by DFS. Link:
	 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
	 * 
	 */
	public boolean isCyclic(ArrayList<ArrayList<Integer>> graph, int V) {
		boolean[] visited = new boolean[V];
		boolean[] stack = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (hasCycle(i, visited, stack, graph)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCycle(int source, boolean[] visited, boolean[] stack, ArrayList<ArrayList<Integer>> edges) {
		if (stack[source]) {
			return true;
		}
		if (visited[source]) {
			return false;
		}
		visited[source] = true;
		stack[source] = true;
		for (Integer destination : edges.get(source)) {
			if (hasCycle(destination, visited, stack, edges)) {
				return true;
			}
		}
		stack[source] = false;
		return false;
	}

	/**
	 * Finds the number of islands with land(1) with water(0) surrounded in a 2
	 * dimensional matrix. Link:
	 * https://practice.geeksforgeeks.org/problems/find-the-number-of-islands/1
	 * 
	 */
	public int numberOfIslands(ArrayList<ArrayList<Integer>> edges, int N, int M) {
		int numberOfIslands = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if (edges.get(x).get(y) == 1) {
					numberOfIslands++;
				}
				if (edges.get(x).get(y) != 0) {
					markIslandVisited(edges, x, y, N, M);
				}
			}
		}
		return numberOfIslands;
	}

	private void markIslandVisited(ArrayList<ArrayList<Integer>> list, int x, int y, int N, int M) {
		if (x < 0 || y < 0 || x >= N || y >= M) {
			return;
		}
		if (list.get(x).get(y) == 1) {
			list.get(x).set(y, 2);
			markIslandVisited(list, x - 1, y - 1, N, M);
			markIslandVisited(list, x - 1, y, N, M);
			markIslandVisited(list, x - 1, y + 1, N, M);
			markIslandVisited(list, x, y - 1, N, M);
			markIslandVisited(list, x, y + 1, N, M);
			markIslandVisited(list, x + 1, y - 1, N, M);
			markIslandVisited(list, x + 1, y, N, M);
			markIslandVisited(list, x + 1, y + 1, N, M);
		}
	}

	/**
	 * finds the max area with the connected ones across the eight directions
	 * including diagonals. Link:
	 * https://practice.geeksforgeeks.org/problems/length-of-largest-region-of-1s-1587115620/1
	 * 
	 */
	public int findMaxArea(int N, int M, int A[][]) {
		int maxArea = Integer.MIN_VALUE;
		int[] area = new int[0];
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if (A[x][y] == 1) {
					area[0] = 0;
				}
				if (A[x][y] != 0) {
					fillArrayWithMaxArea(A, x, y, N, M, area);
				}
				if (area[0] > maxArea) {
					maxArea = area[0];
				}
			}
		}
		return maxArea;
	}

	private void fillArrayWithMaxArea(int[][] list, int x, int y, int N, int M, int[] area) {
		if (x < 0 || y < 0 || x >= N || y >= M) {
			return;
		}
		if (list[x][y] == 1) {
			list[x][y] = 2;
			area[0]++;
			fillArrayWithMaxArea(list, x - 1, y - 1, N, M, area);
			fillArrayWithMaxArea(list, x - 1, y, N, M, area);
			fillArrayWithMaxArea(list, x - 1, y + 1, N, M, area);
			fillArrayWithMaxArea(list, x, y - 1, N, M, area);
			fillArrayWithMaxArea(list, x, y + 1, N, M, area);
			fillArrayWithMaxArea(list, x + 1, y - 1, N, M, area);
			fillArrayWithMaxArea(list, x + 1, y, N, M, area);
			fillArrayWithMaxArea(list, x + 1, y + 1, N, M, area);
		}
	}

	public boolean hasPath(int[][] matrix, int size) {
		int x = -1;
		int y = -1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (matrix[i][j] == 1) {
					x = i;
					y = j;
				}
			}
		}
		return hasPathFromOneToTwo(matrix, x, y, size);
	}

	private boolean hasPathFromOneToTwo(int[][] list, int x, int y, int size) {
		if (x < 0 || y < 0 || x >= size || y >= size || list[x][y] == 0 || list[x][y] == 4) {
			return false;
		}
		if (list[x][y] == 2) {
			return true;
		}
		list[x][y] = 4;
		return hasPathFromOneToTwo(list, x - 1, y, size) || hasPathFromOneToTwo(list, x, y - 1, size)
				|| hasPathFromOneToTwo(list, x, y + 1, size) || hasPathFromOneToTwo(list, x + 1, y, size);
	}
}
