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
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.println();
		}
	}

    public static void main(String[] args) {
        Solution solution = new Solution();
		int[][] matrix = new int[M][N];
		//initial(matrix);
		//display(matrix);
		//System.out.println();
		matrix = new int[10][1];
		solution.setZeroes(matrix);
		// display(matrix);
    }
}