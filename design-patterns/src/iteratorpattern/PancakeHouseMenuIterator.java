package iteratorpattern;

import java.util.ArrayList;

public class PancakeHouseMenuIterator implements Iterator {

	ArrayList<MenuItem> items;
	int position = 0;

	public PancakeHouseMenuIterator(ArrayList<MenuItem> items) {
		this.items = items;
	}

	@Override
	public boolean hasNext() {
		if (position >= items.size() || items.get(position) == null) {
			return false;
		}
		return true;
	}

	@Override
	public MenuItem next() {
		MenuItem item = items.get(position);
		position++;
		return item;
	}
}
