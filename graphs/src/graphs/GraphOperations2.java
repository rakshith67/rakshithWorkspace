package graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphOperations2 {

	/**
	 * finds the number of adjacent X's shapes that can be formed using the given
	 * char matrix. Link:
	 * https://practice.geeksforgeeks.org/problems/x-total-shapes/0
	 * 
	 */
	public int xTotalShapesInGraph(char[][] matrix, int rows, int columns) {
		int[][] visited = new int[rows][columns];
		int count = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (matrix[i][j] == 'X' && visited[i][j] == 0) {
					fillXVistedUsingDFS(matrix, i, j, rows, columns, visited);
					count++;
				}
			}
		}
		return count;
	}

	private void fillXVistedUsingDFS(char[][] a, int i, int j, int r, int c, int[][] visited) {
		if (i >= r || j >= c || i < 0 || j < 0 || a[i][j] == 'O' || visited[i][j] == 1) {
			return;
		}
		visited[i][j] = 1;
		fillXVistedUsingDFS(a, i, j + 1, r, c, visited);
		fillXVistedUsingDFS(a, i, j - 1, r, c, visited);
		fillXVistedUsingDFS(a, i + 1, j, r, c, visited);
		fillXVistedUsingDFS(a, i - 1, j, r, c, visited);
	}

	/**
	 * Given a 2D screen, location of a pixel in the screen i.e (x,y) and a
	 * color(K), your task is to replace color of the given pixel and all
	 * adjacent(excluding diagonally adjacent) same colored pixels with the given
	 * color K. Link:
	 * https://practice.geeksforgeeks.org/problems/flood-fill-algorithm/0
	 * 
	 */
	public int[][] floodFillAlgorithm(int[][] matrix, int rows, int columns, int x, int y, int replaceValue) {
		dfs(matrix, x, y, rows, columns, replaceValue, matrix[x][y]);
		return matrix;
	}

	private void dfs(int[][] a, int i, int j, int r, int c, int x, int current) {
		if (i >= r || j >= c || i < 0 || j < 0 || a[i][j] != current) {
			return;
		}
		a[i][j] = x;
		dfs(a, i, j + 1, r, c, x, current);
		dfs(a, i, j - 1, r, c, x, current);
		dfs(a, i + 1, j, r, c, x, current);
		dfs(a, i - 1, j, r, c, x, current);
	}

	/**
	 * Given an undirected graph and an edge, the task is to find if the given edge
	 * is a bridge in graph, i.e., removing the edge disconnects the graph. Link:
	 * https://practice.geeksforgeeks.org/problems/bridge-edge-in-graph/1
	 * 
	 */
	public boolean isBridgeEdge(Graph graph, int s, int e) {
		int[] time = new int[0];
		List<ArrayList<Integer>> edges = graph.getEdges();
		int vertices = graph.getVertices();
		boolean[] visited = new boolean[vertices];
		int[] low = new int[vertices];
		int[] discoveryTime = new int[vertices];
		int[] parent = new int[vertices];
		Arrays.fill(parent, -1);
		List<Point> list = new ArrayList<>();

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				fillBridgeEdgesDFS(i, edges, visited, low, discoveryTime, parent, list, time);
			}
		}

		for (Point point : list) {
			if ((point.x == s && point.y == e) || (point.x == e && point.y == s)) {
				return true;
			}
		}
		return false;
	}

	private void fillBridgeEdgesDFS(int source, List<ArrayList<Integer>> edges, boolean[] visited, int[] low,
			int[] discoveryTime, int[] parent, List<Point> list, int[] time) {
		visited[source] = true;
		discoveryTime[source] = low[source] = ++time[0];

		ArrayList<Integer> adjacents = edges.get(source);
		for (Integer destination : adjacents) {
			if (!visited[destination]) {
				parent[destination] = source;
				fillBridgeEdgesDFS(destination, edges, visited, low, discoveryTime, parent, list, time);
				low[source] = Math.min(low[source], low[destination]);
				if (low[destination] > discoveryTime[source]) {
					list.add(new Point(source, destination, 0));
				}
			} else if (destination != parent[source]) {
				low[source] = Math.min(low[source], discoveryTime[destination]);
			}
		}
	}

	/**
	 * A vertex in an undirected connected graph is an articulation point (or cut
	 * vertex) iff removing it (and edges through it) disconnects the graph.
	 * Articulation points represent vulnerabilities in a connected network – single
	 * points whose failure would split the network into 2 or more components. They
	 * are useful for designing reliable networks.
	 * 
	 */
	public boolean[] articulationPoints(Graph graph) {
		int[] time = new int[0];
		List<ArrayList<Integer>> edges = graph.getEdges();
		int vertices = graph.getVertices();
		boolean[] visited = new boolean[vertices];
		int[] low = new int[vertices];
		int[] discoveryTime = new int[vertices];
		int[] parent = new int[vertices];
		Arrays.fill(parent, -1);

		boolean[] points = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				fillArticulationPointsDFS(i, edges, visited, low, discoveryTime, parent, points, time);
			}
		}

		return points;
	}

	private void fillArticulationPointsDFS(int source, List<ArrayList<Integer>> edges, boolean[] visited, int[] low,
			int[] discoveryTime, int[] parent, boolean[] points, int[] time) {
		visited[source] = true;
		discoveryTime[source] = low[source] = ++time[0];
		ArrayList<Integer> adjacents = edges.get(source);
		int children = 0;

		for (Integer destination : adjacents) {
			if (!visited[destination]) {
				children++;
				parent[destination] = source;
				fillArticulationPointsDFS(destination, edges, visited, low, discoveryTime, parent, points, time);
				low[source] = Math.min(low[source], low[destination]);
				if (parent[source] == 1 && children > 1) {
					points[source] = true;
				}
				if (parent[source] != -1 && low[destination] >= discoveryTime[source]) {
					points[source] = true;
				}
			} else if (destination != parent[source]) {
				low[source] = Math.min(low[source], discoveryTime[destination]);
			}
		}
	}

	/**
	 * Given a square chess board of N x N size, the position of Knight and position
	 * of a target is given. We need to find out minimum steps a Knight will take to
	 * reach the target position. Link:
	 * https://practice.geeksforgeeks.org/problems/steps-by-knight/0
	 * 
	 */
	public int findMinStepsSquareChess(boolean[][] visited, int x1, int y1, int x2, int y2, int size) {
		Deque<Point> deque = new ArrayDeque<>();
		deque.add(new Point(x1, y1, 0));
		while (!deque.isEmpty()) {
			Point point = deque.removeLast();
			visited[point.x][point.y] = true;
			if (point.x == x2 && point.y == y2) {
				return point.distance;
			}
			if (isValidPoint(point.x + 1, point.y + 2, size, size) && !visited[point.x + 1][point.y + 2]) {
				deque.addFirst(new Point(point.x + 1, point.y + 2, point.distance + 1));
			}
			if (isValidPoint(point.x + 1, point.y - 2, size, size) && !visited[point.x + 1][point.y - 2]) {
				deque.addFirst(new Point(point.x + 1, point.y - 2, point.distance + 1));
			}
			if (isValidPoint(point.x - 1, point.y + 2, size, size) && !visited[point.x - 1][point.y + 2]) {
				deque.addFirst(new Point(point.x - 1, point.y + 2, point.distance + 1));
			}
			if (isValidPoint(point.x - 1, point.y - 2, size, size) && !visited[point.x - 1][point.y - 2]) {
				deque.addFirst(new Point(point.x - 1, point.y - 2, point.distance + 1));
			}
			if (isValidPoint(point.x + 2, point.y + 1, size, size) && !visited[point.x + 2][point.y + 1]) {
				deque.addFirst(new Point(point.x + 2, point.y + 1, point.distance + 1));
			}
			if (isValidPoint(point.x + 2, point.y - 1, size, size) && !visited[point.x + 2][point.y - 1]) {
				deque.addFirst(new Point(point.x + 2, point.y - 1, point.distance + 1));
			}
			if (isValidPoint(point.x - 2, point.y + 1, size, size) && !visited[point.x - 2][point.y + 1]) {
				deque.addFirst(new Point(point.x - 2, point.y + 1, point.distance + 1));
			}
			if (isValidPoint(point.x - 2, point.y - 1, size, size) && !visited[point.x - 2][point.y - 1]) {
				deque.addFirst(new Point(point.x - 2, point.y - 1, point.distance + 1));
			}
		}
		return -1;
	}

	/**
	 * Given a chess board of order N x M and source points (x1, y1) and destination
	 * points (x2, y2). The task to find minimum number of moves required by the
	 * Knight to go to the destination cell. Link:
	 * https://practice.geeksforgeeks.org/problems/knight-walk/0
	 * 
	 */
	public int findMinStepsArbituaryShapeChess(boolean[][] visited, int x1, int y1, int x2, int y2, int xsize,
			int ysize) {
		Deque<Point> deque = new ArrayDeque<>();
		deque.add(new Point(x1, y1, 0));
		while (!deque.isEmpty()) {
			Point point = deque.removeLast();
			visited[point.x][point.y] = true;
			if (point.x == x2 && point.y == y2) {
				return point.distance;
			}
			if (isValidPoint(point.x + 1, point.y + 2, xsize, ysize) && !visited[point.x + 1][point.y + 2]) {
				deque.addFirst(new Point(point.x + 1, point.y + 2, point.distance + 1));
			}
			if (isValidPoint(point.x + 1, point.y - 2, xsize, ysize) && !visited[point.x + 1][point.y - 2]) {
				deque.addFirst(new Point(point.x + 1, point.y - 2, point.distance + 1));
			}
			if (isValidPoint(point.x - 1, point.y + 2, xsize, ysize) && !visited[point.x - 1][point.y + 2]) {
				deque.addFirst(new Point(point.x - 1, point.y + 2, point.distance + 1));
			}
			if (isValidPoint(point.x - 1, point.y - 2, xsize, ysize) && !visited[point.x - 1][point.y - 2]) {
				deque.addFirst(new Point(point.x - 1, point.y - 2, point.distance + 1));
			}
			if (isValidPoint(point.x + 2, point.y + 1, xsize, ysize) && !visited[point.x + 2][point.y + 1]) {
				deque.addFirst(new Point(point.x + 2, point.y + 1, point.distance + 1));
			}
			if (isValidPoint(point.x + 2, point.y - 1, xsize, ysize) && !visited[point.x + 2][point.y - 1]) {
				deque.addFirst(new Point(point.x + 2, point.y - 1, point.distance + 1));
			}
			if (isValidPoint(point.x - 2, point.y + 1, xsize, ysize) && !visited[point.x - 2][point.y + 1]) {
				deque.addFirst(new Point(point.x - 2, point.y + 1, point.distance + 1));
			}
			if (isValidPoint(point.x - 2, point.y - 1, xsize, ysize) && !visited[point.x - 2][point.y - 1]) {
				deque.addFirst(new Point(point.x - 2, point.y - 1, point.distance + 1));
			}
		}
		return -1;
	}

	private boolean isValidPoint(int x1, int y1, int xsize, int ysize) {
		if (x1 < 0 || y1 < 0 || x1 >= xsize || y1 >= ysize) {
			return false;
		}
		return true;
	}

	/**
	 * Given a binary matrix of size N x M. The task is to find the distance of
	 * nearest 1 in the matrix for each cell. The distance is calculated as |i1 –
	 * i2| + |j1 – j2|, where i1, j1 are the row number and column number of the
	 * current cell and i2, j2 are the row number and column number of the nearest
	 * cell having value 1. Link:
	 * https://practice.geeksforgeeks.org/problems/distance-of-nearest-cell-having-1/0
	 * 
	 */
	public int[][] distanceOfNearestCellHaving1(int[][] matrix, int rows, int columns) {
		int result[][] = new int[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				result[i][j] = findMinDistanceTo1(matrix, i, j, rows, columns);
			}
		}
		return result;
	}

	private int findMinDistanceTo1(int mat[][], int a, int b, int rows, int columns) {
		Point point = new Point(a, b, 0);
		Deque<Point> q = new ArrayDeque<Point>();
		q.add(point);
		while (!q.isEmpty()) {
			Point current = q.removeLast();
			int i = current.x;
			int j = current.y;
			int distance = current.distance;
			if (mat[i][j] == 1) {
				return distance;
			}
			if (isValidPoint(i + 1, j, rows, columns)) {
				q.addFirst(new Point(i + 1, j, distance + 1));
			}
			if (isValidPoint(i - 1, j, rows, columns)) {
				q.addFirst(new Point(i - 1, j, distance + 1));
			}
			if (isValidPoint(i, j + 1, rows, columns)) {
				q.addFirst(new Point(i, j + 1, distance + 1));
			}
			if (isValidPoint(i, j - 1, rows, columns)) {
				q.addFirst(new Point(i, j - 1, distance + 1));
			}
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * Given a snake and ladder board of order 10*10, find the minimum number of
	 * dice throws required to reach the destination or last cell (100th cell) from
	 * source (1st cell) . Link1:
	 * https://practice.geeksforgeeks.org/problems/snake-and-ladder-problem/0 Link2:
	 * https://www.interviewbit.com/problems/snake-ladder-problem/
	 * 
	 */
	public int snakeLadderProblem(ArrayList<ArrayList<Integer>> ladders, ArrayList<ArrayList<Integer>> snakes) {
		int[] move = new int[101];
		boolean[] visited = new boolean[101];
		for (ArrayList<Integer> ladder : ladders) {
			int source = ladder.get(0);
			int dest = ladder.get(1);
			move[source] = dest;
		}
		for (ArrayList<Integer> snake : snakes) {
			int source = snake.get(0);
			int dest = snake.get(1);
			move[source] = dest;
		}
		return minStepsToReachEnd(visited, move);
	}

	private int minStepsToReachEnd(boolean[] visited, int[] move) {
		Point point = new Point(1, 0, 0);
		Deque<Point> deque = new ArrayDeque<>();
		deque.add(point);
		while (!deque.isEmpty()) {
			point = deque.removeLast();
			System.out.print(point.x + ":" + point.distance + "; ");
			int cordinate = point.x;
			visited[cordinate] = true;
			if (cordinate == 30) {
				break;
			}
			for (int j = cordinate + 1; j <= cordinate + 6 && j < 31; j++) {
				if (!visited[j]) {
					if (move[j] == -1) {
						deque.addFirst(new Point(j, 0, point.distance + 1));
					} else {
						deque.addFirst(new Point(move[j], 0, point.distance + 1));
					}
				}
			}
		}
		return point.distance;
	}

	private int index;

	/**
	 * There are a total of numCourses courses you have to take, labeled from 0 to
	 * numCourses-1. Some courses may have prerequisites, for example to take course
	 * 0 you have to first take course 1, which is expressed as a pair: [0,1] Given
	 * the total number of courses and a list of prerequisite pairs, is it possible
	 * for you to finish all courses? Link:
	 * https://leetcode.com/problems/course-schedule/
	 * 
	 */
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			list.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			int[] current = prerequisites[i];
			int src = current[1];
			int dest = current[0];
			list.get(src).add(dest);
		}
		int[] visited = new int[numCourses];
		int[] orderOfCourses = new int[numCourses];
		index = numCourses - 1;
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0) {
				topoSortDFS(list, visited, i, orderOfCourses);
			}
		}
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] != 2) {
				return new int[0];
			}
		}
		return orderOfCourses;
	}

	private void topoSortDFS(ArrayList<ArrayList<Integer>> list, int[] visited, int source, int[] orderOfCourses) {
		visited[source] = 1;
		List<Integer> adjacents = list.get(source);
		boolean flag = false;
		for (Integer integer : adjacents) {
			if (visited[integer] == 0) {
				topoSortDFS(list, visited, integer, orderOfCourses);
			} else if (visited[integer] == 1) {
				flag = true;
			}
		}
		if (!flag) {
			visited[source] = 2;
			orderOfCourses[index] = source;
			index--;
		}
	}

	/**
	 * There are a total of numCourses courses you have to take, labeled from 0 to
	 * numCourses-1. Some courses may have prerequisites, for example to take course
	 * 0 you have to first take course 1, which is expressed as a pair: [0,1] Given
	 * the total number of courses and a list of prerequisite pairs, is it possible
	 * for you to finish all courses? Link:
	 * https://leetcode.com/problems/course-schedule/
	 * 
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			list.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < prerequisites.length; i++) {
			int[] current = prerequisites[i];
			int src = current[1];
			int dest = current[0];
			list.get(src).add(dest);
		}
		int[] visited = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0) {
				topoSortDFS(list, visited, i);
			}
		}
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] != 2) {
				return false;
			}
		}
		return true;
	}

	private void topoSortDFS(ArrayList<ArrayList<Integer>> list, int[] visited, int source) {
		visited[source] = 1;
		List<Integer> adjacents = list.get(source);
		boolean flag = false;
		for (Integer integer : adjacents) {
			if (visited[integer] == 0) {
				topoSortDFS(list, visited, integer);
			} else if (visited[integer] == 1) {
				flag = true;
			}
		}
		if (!flag) {
			visited[source] = 2;
		}
	}

	/**
	 * In a town, there are N people labelled from 1 to N. There is a rumor that one
	 * of these people is secretly the town judge. If the town judge exists, then:
	 * The town judge trusts nobody. Everybody (except for the town judge) trusts
	 * the town judge. There is exactly one person that satisfies properties 1 and
	 * 2. You are given trust, an array of pairs trust[i] = [a, b] representing that
	 * the person labelled a trusts the person labelled b. If the town judge exists
	 * and can be identified, return the label of the town judge. Otherwise, return
	 * -1. Link: https://leetcode.com/problems/find-the-town-judge/
	 * 
	 */
	public int findJudge(int N, int[][] trust) {
		int[] count = new int[N + 1];
		for (int[] t : trust) {
			count[t[0]]--;
			count[t[1]]++;
		}
		for (int i = 1; i <= N; i++) {
			if (count[i] == N - 1)
				return i;
		}
		return -1;
	}

	/**
	 * Given an array of non-negative integers arr, you are initially positioned at
	 * start index of the array. When you are at index i, you can jump to i + arr[i]
	 * or i - arr[i], check if you can reach to any index with value 0. Notice that
	 * you can not jump outside of the array at any time. Link:
	 * https://leetcode.com/problems/jump-game-iii/
	 * 
	 */
	public boolean canReach(int[] arr, int start) {
		boolean[] visited = new boolean[arr.length];
		Deque<Integer> deque = new ArrayDeque<>();
		deque.add(start);
		while (!deque.isEmpty()) {
			int current = deque.removeFirst();
			visited[current] = true;
			if (arr[current] == 0) {
				return true;
			}
			int left = current - arr[current];
			int right = current + arr[current];
			if (left >= 0 && !visited[left]) {
				deque.add(left);
			}
			if (right < arr.length && !visited[right]) {
				deque.add(right);
			}
		}
		return false;
	}

	/**
	 * There are a total of n courses you have to take, labeled from 0 to n-1. Some
	 * courses may have direct prerequisites, for example, to take course 0 you have
	 * first to take course 1, which is expressed as a pair: [1,0] Given the total
	 * number of courses n, a list of direct prerequisite pairs and a list of
	 * queries pairs. You should answer for each queries[i] whether the course
	 * queries[i][0] is a prerequisite of the course queries[i][1] or not. Return a
	 * list of boolean, the answers to the given queries. Please note that if course
	 * a is a prerequisite of course b and course b is a prerequisite of course c,
	 * then, course a is a prerequisite of course c. Link:
	 * https://leetcode.com/problems/course-schedule-iv/
	 * 
	 */
	public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
		int[] in = new int[n];
		List<Set<Integer>> data = new ArrayList<>();
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
			data.add(new HashSet<>());
		}
		for (int[] e : prerequisites) {
			graph.get(e[0]).add(e[1]);
			in[e[1]]++;
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (in[i] == 0) {
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph.get(cur)) {
				data.get(next).add(cur);
				data.get(next).addAll(data.get(cur));
				if (--in[next] == 0) {
					q.offer(next);
				}
			}
		}

		List<Boolean> res = new ArrayList<>();
		for (int[] query : queries) {
			res.add(data.get(query[1]).contains(query[0]));
		}
		return res;
	}
}
