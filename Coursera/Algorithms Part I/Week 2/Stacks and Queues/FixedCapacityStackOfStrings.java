public class FixedCapacityStackOfStrings {
	private String[] s;
	private int N;

	public FixedCapacityStackOfStrings(int capacity) {
		s = new String[capacity];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(String item) {
		s[N++] = item;
	}

	public String pop() {
		String item = s[--N];
		s[N] = null;	// so there's no reference
		return item;
	}
}