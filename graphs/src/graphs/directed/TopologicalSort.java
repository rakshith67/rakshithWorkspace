package graphs.directed;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {

    public static void main(String[] args) {
        int[][] edgesWithCycle = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 1}
        };
        int vertices = 4;
        
        boolean hasCycle = hasCycleDirectedGraphKahnAlgorithm(edgesWithCycle, vertices);
        System.out.println("Has cycle " + hasCycle);
    }

    private static boolean hasCycleDirectedGraphKahnAlgorithm(int[][] edges, int vertices) {
        int[] inDegree = new int[vertices];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            inDegree[edge[1]]++;
            graph.get(edge[0]).add(edge[1]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            count++;
            List<Integer> neighbors = graph.get(vertex);
            for (Integer neighbor: neighbors) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return count != vertices;
    }
}
