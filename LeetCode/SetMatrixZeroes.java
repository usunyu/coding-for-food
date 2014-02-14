import java.util.*;

class Solution {
    public void setRowZeroes(int[][] matrix, int row) {
        for(int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = 0;
        }
    }
    
    public void setColZeroes(int[][] matrix, int col) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }
    
    // space complixity : O(M + N)
    public void setZeroes(int[][] matrix) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(matrix == null) {
        	return;
        }
        boolean[] row = new boolean[matrix.length];
        boolean[] col = null;
        if(matrix.length > 0) {
        	col = new boolean[matrix[0].length];
        }
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for(int i = 0; i < row.length; i++) {
            if(row[i]) {
                setRowZeroes(matrix, i);
            }
        }
        for(int i = 0; col != null && i < col.length; i++) {
            if(col[i]) {
                setColZeroes(matrix, i);
            }
        }
    }

    // space complixity : O(1)
    public void setZeroes2(int[][] matrix) {
    	// row number
    	int M = matrix.length;
    	if(M <= 0) { return; }
    	// col number
    	int N = matrix[0].length;
    	if(N <= 0) { return; }
    	boolean firstRowHasZero = false;
    	boolean firstColHasZero = false;
    	for(int i = 0; i < M; i++) {
    		if(matrix[i][0] == 0) {
    			firstColHasZero = true;
    			break;
    		}
    	}
    	for(int i = 0; i < N; i++) {
    		if(matrix[0][i] == 0) {
    			firstRowHasZero = true;
    			break;
    		}
    	}
    	// mark in first rows and cols
    	for(int i = 1; i < M; i++) {
    		for(int j = 1; j < N; j++) {
    			if(matrix[i][j] == 0) {
    				matrix[0][j] = 0;
    				matrix[i][0] = 0;
    			}
    		}
    	}
    	// set rows
    	for(int i = 1; i < M; i++) {
    		if(matrix[i][0] == 0) {
    			setRowZeroes(matrix, i);
    		}
    	}
    	// set cols
    	for(int j = 1; j < N; j++) {
    		if(matrix[0][j] == 0) {
    			setColZeroes(matrix, j);
    		}
    	}
    	// set first row
    	if(firstRowHasZero) {
    		setRowZeroes(matrix, 0);
    	}
    	// set first col
    	if(firstColHasZero) {
    		setColZeroes(matrix, 0);
    	}
    }
}

class Main {
	static int M = 10, N = 8;

	public static void initial(int[][] matrix) {
		int zero = 0;	// allowed 3 zero
		for(int i = 0; i < M; i++)
			for(int j = 0; j < N; j++) {
				Random random = new Random();
				int rint = random.nextInt(10);
				if(rint == 0) {
					zero++;
					if(zero > 3)
						rint++;
				}
				matrix[i][j] = rint;
			}
	}

	public static void display(int[][] matrix) {
		int M = matrix.length;
		int N = matrix[0].length;
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.println();
		}
	}

    public static void main(String[] args) {
        Solution solution = new Solution();
		int[][] matrix = {{0,0,0,5}, {4,3,1,4}, {0,1,1,4}, {1,2,1,3}, {0,0,1,1}};
		// initial(matrix);
		// display(matrix);
		// System.out.println();
		solution.setZeroes2(matrix);
		display(matrix);
    }
}