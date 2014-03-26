public class FixedCapacityStack<Item> {
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
}