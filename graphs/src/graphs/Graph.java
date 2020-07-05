package graphs;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	private int vertices;

	private List<ArrayList<Integer>> edges = new ArrayList<>();

	public int getVertices() {
		return vertices;
	}

	public void setVertices(int vertices) {
		this.vertices = vertices;
	}

	public List<ArrayList<Integer>> getEdges() {
		return edges;
	}

	public void setEdges(List<ArrayList<Integer>> edges) {
		this.edges = edges;
	}

	public Graph(List<ArrayList<Integer>> graph, int vertices) {
		this.edges = graph;
		this.vertices = vertices;
	}
}
