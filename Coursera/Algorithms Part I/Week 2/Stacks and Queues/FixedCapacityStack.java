import java.util.Iterator;

public class FixedCapacityStack<Item> implements Iterable<Item> {
	private Item[] s;
	private int N;

	public FixedCapacityStack(int capacity) {
		s = (Item[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(Item item) {
		s[N++] = item;
	}

	public Item pop() {
		Item item = s[--N];
		s[N] = null;	// so there's no reference
		return item;
	}

	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Item> {
		private int i = N;

		public boolean hasNext() {
			return i > 0;
		}

		public void remove() {
			/* not supported */
		}

		public Item next() {
			return s[--i];
		}
	}
}