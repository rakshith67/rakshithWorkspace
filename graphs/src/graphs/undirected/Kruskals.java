package graphs.undirected;

import java.util.Arrays;
import java.util.Comparator;

public class Kruskals {

    public static void main(String[] args) {
        int[][] edges = {
                {7, 1, 2},
                {2, 2, 3},
                {4, 4, 5},
                {8, 6, 7},
                {3, 5, 6},
                {4, 3, 7}
        };
        Arrays.sort(edges, Comparator.comparingInt(edge -> edge[0]));
        int weight = 0;
        int length = 8;
        int[] parent = new int[length];
        int[] rank = new int[length];
        Arrays.fill(rank, 1);
        for (int i = 0; i < length; i++) {
            parent[i] = i;
        }

        for (int[] edge: edges) {
            // Kruskals Algorithm
            if (union(parent, rank, edge[1], edge[2])) {
                weight += edge[0];
            }
        }
        System.out.println(weight);
    }
    public static boolean union(int[] parent, int[] rank, int u, int v) {
        int parent1 = find(parent, u);
        int parent2 = find(parent, v);

        if (parent1 == parent2) {
            return false;
        }
        if (rank[parent1] >= rank[parent2]) {
            parent[parent2] = parent1;
            rank[parent1] += rank[parent2];
        } else {
            parent[parent1] = parent2;
            rank[parent2] += rank[parent1];
        }
        return true;
    }

    public static int find(int[] parent, int u) {
        int p = parent[u];
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
