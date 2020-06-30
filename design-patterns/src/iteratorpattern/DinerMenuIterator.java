package iteratorpattern;

public class DinerMenuIterator implements Iterator {

	MenuItem[] items;
	int position = 0;

	public DinerMenuIterator(MenuItem[] items) {
		this.items = items;
	}

	@Override
	public boolean hasNext() {
		if (position >= items.length || items[position] == null) {
			return false;
		}
		return true;
	}

	@Override
	public MenuItem next() {
		MenuItem item = items[position];
		position++;
		return item;
	}

}
