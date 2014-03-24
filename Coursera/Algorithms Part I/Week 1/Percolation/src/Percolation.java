public class Percolation {
	private int N;
	private int[][] sites;
	private WeightedQuickUnionUF uf;
	private boolean percolates;
	
	// create N-by-N grid, with all sites blocked
	public Percolation(int N) {
		this.N = N;
		// 0 :	blocked
		// 1 :	opened
		this.sites = new int[N + 1][N + 1];
		// 0 :	virtual top site
		// N * N + 1 ~ N * N + N :	virtual bottom site
		this.uf = new WeightedQuickUnionUF((N + 1) * N + 1);
		// avoid check percolates repeat
		this.percolates = false;
	}
	
	// throw exception if out of bounds
	private void checkIndex(int i, int j) {
		if(i <= 0 || i > N) throw new IndexOutOfBoundsException("row index i out of bounds");
		if(j <= 0 || j > N) throw new IndexOutOfBoundsException("row index j out of bounds");
	}
	
	// from 1 to N * N
	private int xyTo1D(int i, int j) {
		checkIndex(i, j);
		return (i - 1) * N + j;
	}
	
	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		checkIndex(i, j);
		int id = xyTo1D(i, j);
		if(!isOpen(i, j)) {
			// open it
			sites[i][j] = 1;
			// connect neighbor sites
			// up site is open
			if(i > 1 && isOpen(i - 1, j)) {
				uf.union(xyTo1D(i - 1, j), id);
			}
			else if(i == 1) {	// top row
				// connect with virtual top site
				uf.union(0, id);
			}
			// down site is open
			if(i < N && isOpen(i + 1, j)) {
				uf.union(xyTo1D(i + 1, j), id);
			}
			else if(i == N) {	// bottom row
				// connect with virtual bottom site
				uf.union(N * N + j, id);	// different bottom entry
			}
			// left site is open
			if(j > 1 && isOpen(i, j - 1)) {
				uf.union(xyTo1D(i, j - 1), id);
			}
			// right site is open
			if(j < N && isOpen(i, j + 1)) {
				uf.union(xyTo1D(i, j + 1), id);
			}
		}
	}

	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		checkIndex(i, j);
		return sites[i][j] == 1;
	}

	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		checkIndex(i, j);
		return uf.connected(xyTo1D(i, j), 0);
	}

	// does the system percolate?
	public boolean percolates() {
		if(percolates) return true;
		for(int j = 1; j < N; j++) {
			if(uf.connected(0, N * N + j)) {
				percolates = true;
				return true;
			}		
		}
		return false;
	}
	
	public static void main(String[] args) {
		Percolation prec = new Percolation(2);
		prec.open(1, 1);
		prec.open(1, 2);
		prec.open(2, 2);
		System.out.println(prec.percolates());
	}
}
