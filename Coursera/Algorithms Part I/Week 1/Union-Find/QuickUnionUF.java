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

	public static void main(String[] args) {
		int N = 10;

        QuickUnionUF uf = new QuickUnionUF(N);
        uf.printIdArray();

        System.out.println("union(4, 3)");
        uf.union(4, 3);
        uf.printIdArray();

        System.out.println("union(3, 8)");
        uf.union(3, 8);
        uf.printIdArray();

        System.out.println("union(6, 5)");
        uf.union(6, 5);
        uf.printIdArray();

        System.out.println("union(9, 4)");
        uf.union(9, 4);
        uf.printIdArray();

        System.out.println("union(2, 1)");
        uf.union(2, 1);
        uf.printIdArray();

        System.out.println("connected(8, 9)");
        System.out.println(uf.connected(8, 9));
        System.out.println();

        System.out.println("connected(5, 0)");
        System.out.println(uf.connected(5, 0));
        System.out.println();

        System.out.println("union(5, 0)");
        uf.union(5, 0);
        uf.printIdArray();

        System.out.println("union(7, 2)");
        uf.union(7, 2);
        uf.printIdArray();

        System.out.println("union(6, 1)");
        uf.union(6, 1);
        uf.printIdArray();
	}
}