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
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            if (!unionFind.union(edge[0], edge[1])) {
                System.out.println("Is a cycle");
            }
        }
        boolean isValidTree = unionFind.getNumberOfComponents() == 1;
        System.out.println("Valid Tree: " + isValidTree);
        System.out.println("Is not a cycle");
    }
}

class UnionFind {
    private final int[] parent;
    private final int[] rank;
    private int numberOfComponents;

    public UnionFind(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <=n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        this.numberOfComponents = n;
    }

    public boolean union(int u, int v) {
        int parent1 = find(u);
        int parent2 = find(v);

        if (parent1 == parent2) {
            return false;
        }
        numberOfComponents--;
        if (rank[parent1] >= rank[parent2]) {
            parent[parent2] = parent1;
            rank[parent1] += rank[parent2];
        } else {
            parent[parent1] = parent2;
            rank[parent2] += rank[parent1];
        }
        return true;
    }

    public int find(int u) {
        int p = parent[u];
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public int getNumberOfComponents() {
        return numberOfComponents;
    }
}
