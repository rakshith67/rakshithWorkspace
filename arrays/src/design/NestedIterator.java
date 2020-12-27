package design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedIterator implements Iterator<Integer> {
	Queue<Integer> queue;

	public NestedIterator(List<NestedInteger> nestedList) {
		queue = new LinkedList<>();
		prepareQueue(nestedList);
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			return null;
		}
		return queue.poll();
	}

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}

	private void prepareQueue(List<NestedInteger> nestedList) {
		for (NestedInteger integer : nestedList) {
			if (integer.isInteger()) {
				queue.add(integer.getInteger());
			} else {
				prepareQueue(integer.getList());
			}
		}
	}
}

class NestedInteger {

	private boolean isInteger = false;

	Integer integer = null;

	List<NestedInteger> list = new ArrayList<>();

	public boolean isInteger() {
		return isInteger;
	}

	public List<NestedInteger> getList() {
		return list;
	}

	public Integer getInteger() {
		return integer;
	}

}