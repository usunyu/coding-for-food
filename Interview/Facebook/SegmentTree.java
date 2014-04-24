class SegmentTree {
	int[] array;
	int[] tree;

	public SegmentTree(int[] array) {
		this.array = array;
		// array size : n, node size 2 * n - 1
		// full tree, tree height : Ceil(log2n), heap size : 2 * 2 ^ (Ceil(log2n)) - 1
		double x = Math.ceil(Math.log(array.length) / Math.log(2));
		int size = (int)(2 * Math.pow(2, x) - 1);
		tree = new int[size];

	}

	private void construct(int left, int right, int st) {
		if(left == right) tree[st] = array[left];
		else if(left < right) {
			int mid = left + (right - left) / 2;
			construct(left, mid - 1, 2 * st + 1);	// left child
			construct(mid + 1, right, 2 * st + 2);	// right child
			tree[st] = tree[2 * st + 1] + tree[2 * st + 2];
		}
	}

	private int sum(int ss, int se, int qs, int qe, int index) {
		if(qs <= ss && qe >= se)
			return tree[index];
		if(qs > se || qe < ss)
			return 0;
		int mid = ss + (se - ss) / 2;
		return sum(ss, mid, qs, qe, index * 2 + 1) + sum(mid + 1, se, qs, qe, 2 * index + 2);
	}

	public int sum(int left, int right) {
		if(left < 0 || right > array.length - 1 || left > right)
			return -1;
		return sum(0, array.length - 1, left, right, 0);
	}

	private void update(int ss, int se, int i, int diff, int index) {
		if(i < ss || i > se) return;
		tree[index] += diff;
		int mid = ss + (se - ss) / 2;
		if(ss < se) {
			update(ss, mid, i, diff, index * 2 + 1);
			update(mid + 1, se, i, diff, index * 2 + 2);
		}
	}

	public void update(int i, int value) {
		if(i < 0 || i >= array.length)
			return;
		int diff = value - array[i];
		array[i] = value;
		update(0, array.length - 1, i, diff, 0);
	}
}

