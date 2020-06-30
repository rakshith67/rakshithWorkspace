package graphs.easy;

public class Edge {

	int source;
	int destination;
	int weight;

	public Edge(int source, int destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Source: " + source + " Destination: " + destination + " Weight: " + weight;
	}
}
