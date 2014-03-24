public class PercolationStats {
	int N, T;
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	private double[] fractions;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		checkArgument(N, T);
		this.N = N;
		this.T = T;
		this.fractions = new double[T];
		experiments();
		statistics();
	}
	
	public void experiments() {
		for(int t = 0; t < T; t++) {
			doExperiment(t);
		}
	}
	
	public void doExperiment(int t) {
		Percolation prec = new Percolation(N);
		// until it is percolates
		double count = 0;
		while(!prec.percolates()) {
			// pick a site to open
			int i = StdRandom.uniform(N) + 1;
			int j = StdRandom.uniform(N) + 1;
			if(!prec.isOpen(i, j)) {
				prec.open(i, j);
				count++;
			}
		}
		fractions[t] = count / (N * N);
	}
	
	public void statistics() {
		mean = StdStats.mean(fractions);
		stddev = StdStats.stddev(fractions);
		confidenceLo = mean - 1.96 * stddev / Math.sqrt(T);
		confidenceHi = mean + 1.96 * stddev / Math.sqrt(T);
	}

	// sample mean of percolation threshold
	public double mean() {
		return mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return stddev;
	}

	// returns lower bound of the 95% confidence interval
	public double confidenceLo() {
		return confidenceLo;
	}

	// returns upper bound of the 95% confidence interval
	public double confidenceHi() {
		return confidenceHi;
	}

	private void checkArgument(int N, int T) {
		if(N <= 0 || T <= 0) throw new IllegalArgumentException("N and T should be positive integer");
	}
	
	// test client, described below
	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);	// N-by-N percolation system
		int T = Integer.parseInt(args[1]);	// T times
		// Stopwatch watch = new Stopwatch();
		PercolationStats precStat = new PercolationStats(N, T);
		// StdOut.println("time\t\t\t = " + watch.elapsedTime() + "s");
		StdOut.println("mean\t\t\t = " + precStat.mean());
		StdOut.println("stddev\t\t\t = " + precStat.stddev());
		StdOut.println("95% confidence interval\t = " + precStat.confidenceLo() + ", " + precStat.confidenceHi());
	}
}
