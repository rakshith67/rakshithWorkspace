package arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {

	Map<Integer, Integer> map;
	List<Integer> list;
	Random rand = new Random();

	/** Initialize your data structure here. */
	public RandomizedSet() {
		map = new HashMap<>();
		list = new ArrayList<>();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain
	 * the specified element.
	 */
	public boolean insert(int val) {
		if (map.containsKey(val)) {
			return false;
		}
		map.put(val, list.size());
		list.add(val);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified
	 * element.
	 */
	public boolean remove(int val) {
		if (!map.containsKey(val)) {
			return false;
		}
		int position = map.get(val);
		if (position != list.size() - 1) {
			int last = list.get(list.size() - 1);
			list.set(position, last);
			map.put(last, position);
		}
		map.remove(val);
		list.remove(list.size() - 1);
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return list.get(rand.nextInt(list.size()));
	}
}
