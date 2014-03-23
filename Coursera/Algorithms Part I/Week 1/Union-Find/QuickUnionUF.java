public class QuickUnionUF {
	private int[] id;

	public QuickUnionUF(int N) {
		id = new int[N];
		for(int i = 0; i < N; i++)
			id[i] = i;
	}

	private int root(int i) {
		while(i != id[i]) i = id[i];
		return i;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
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