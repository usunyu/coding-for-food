public class WeightedQuickUnionUF {
	private int[] id;	// id[i] = parent of i
	private int[] sz;	// sz[i] = number of objects in subtree rooted at i

	public WeightedQuickUnionUF(int N) {
		id = new int[N];
		sz = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	private int root(int i) {
		while(i != id[i]) {
			id[i] = id[id[i]];	// path compression
			i = id[i];
		}
		return i;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if(i == j) return;
		// make smaller root point to larger one
		if(sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		}
		else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}

	public void printIdArray() {
		System.out.print("[");
		for(int i = 0; i < id.length; i++) {
			if(i < id.length - 1) System.out.print(id[i] + "|");
			else System.out.println(id[i] + "]");
		}
		System.out.println();
	}
}