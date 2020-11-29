package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomizedCollection {

	List<Integer> list;
	Map<Integer, Set<Integer>> map;
	Random random;

	public RandomizedCollection() {
		list = new ArrayList<>();
		map = new HashMap<>();
		random = new Random();
	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */
	public boolean insert(int val) {
		Set<Integer> current = map.get(val);
		if (current == null) {
			current = new HashSet<>();
			map.put(val, current);
		}
		current.add(list.size());
		list.add(val);
		return true;
	}

	/**
	 * Removes a value from the collection. Returns true if the collection contained
	 * the specified element.
	 */
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}
		int location = map.get(val).iterator().next();
		int tailElement = list.get(list.size() - 1);

		map.get(val).remove(location);
		map.get(tailElement).remove(list.size() - 1);
		map.get(tailElement).add(location);

		list.set(location, tailElement);
		list.remove(list.size() - 1);

		if (map.get(val).isEmpty()) {
			map.remove(val);
		}
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		return list.get(random.nextInt(list.size()));
	}
}
