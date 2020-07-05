package graphs;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {

	public void makeSet(ArrayList<ArrayList<Integer>> sets, int V) {
		for (int i = 0; i < V; i++) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(i);
			sets.add(list);
		}
	}

	public int find(ArrayList<ArrayList<Integer>> sets, int vertex) {
		for (int i = 0; i < sets.size(); i++) {
			List<Integer> list = sets.get(i);
			if (list.contains(vertex)) {
				return i;
			}
		}
		return -1;
	}

	public void merge(ArrayList<ArrayList<Integer>> sets, int index1, int index2) {
		if (sets.get(index1).size() > sets.get(index2).size()) {
			sets.get(index1).addAll(sets.get(index2));
			sets.get(index2).clear();
		} else {
			sets.get(index2).addAll(sets.get(index1));
			sets.get(index1).clear();
		}
	}
}
