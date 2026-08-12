package graphs.undirected;

public class DisjointSet {

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2},
                {2, 3},
                {4, 5},
                {6, 7},
                {5, 6},
                {3, 7}
        };
        int n = 7;
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        int[] numberOfComponents = new int[]{n};
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int[] edge : edges) {
            if (!union(parent, rank, edge[0], edge[1], numberOfComponents)) {
                System.out.println("Is a cycle");
            }
        }
        boolean isValidTree = numberOfComponents[0] == 1;
        System.out.println("Valid Tree: " + isValidTree);
        System.out.println("Is not a cycle");
    }

    public static boolean union(int[] parent, int[] rank, int u, int v, int[] numberOfComponents) {
        int parent1 = find(parent, u);
        int parent2 = find(parent, v);

        if (parent1 == parent2) {
            return false;
        }
        numberOfComponents[0]--;
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
