public class Main {
	// driver class
	public static void main(String[] args) {
		int N = 10;

		// QuickFindUF uf = new QuickFindUF(N);
        // QuickUnionUF uf = new QuickUnionUF(N);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
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