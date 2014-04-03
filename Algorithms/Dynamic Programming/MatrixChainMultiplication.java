/*
http://www.geeksforgeeks.org/dynamic-programming-set-8-matrix-chain-multiplication/
*/

class Main {
	// Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
	public static int matrixChainOrder(int[] p) {
		/* For simplicity of the program, one extra row and one extra column are
			allocated in dp[][].  0th row and 0th column of dp[][] are not used */
		int n = p.length;
    	int dp[][] = new int[n][n];
    	/* dp[i,j] = Minimum number of scalar multiplications needed to compute
	       the matrix A[i]A[i+1]...A[j] = A[i..j] where dimention of A[i] is
	       p[i-1] x p[i] */
	    // cost is zero when multiplying one matrix.
	    int i, j, k, L, q;
	    // L is chain length.
	    for (L = 2; L < n; L++) {
	    	for (i = 1; i < n - L + 1; i++) {
	    		j = i + L - 1;
	    		dp[i][j] = Integer.MAX_VALUE;
	    		for (k = i; k <= j - 1; k++) {
	    			// q = cost/scalar multiplications
	    			q = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
	    			if (q < dp[i][j])
	    				dp[i][j] = q;
	    		}
	    	}
	    }
	    return dp[1][n - 1];
	}

	public static void main(String[] args) {
		int arr[] = {1, 2, 3, 4};
		System.out.println("Minimum number of multiplications is " + matrixChainOrder(arr));
	}
}