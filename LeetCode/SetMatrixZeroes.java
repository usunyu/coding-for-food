/*
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

import java.util.*;
import LCLibrary.*;

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
}

class Solution2 {
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

    // space complixity : O(1)
    public void setZeroes(int[][] matrix) {
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

/*
    Second Round
*/
class Solution3 {
    private void setCol(int[][] matrix, int col) {
        for(int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
    }
    
    private void setRow(int[][] matrix, int row) {
        for(int i = 0; i < matrix[0].length; i++) {
            matrix[row][i] = 0;
        }
    }
    
    public void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        boolean firstRow = false, firstCol = false;
        int M = matrix.length, N = matrix[0].length;
        for(int i = 0; i < M; i++) {
            if(matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        for(int i = 0; i < N; i++) {
            if(matrix[0][i] == 0) {
                firstRow = true;
                break;
            }
        }
        for(int i = 1; i < M; i++) {
            for(int j = 1; j < N; j++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i = 1; i < M; i++) {
            if(matrix[i][0] == 0) {
                setRow(matrix, i);
            }
        }
        for(int i = 1; i < N; i++) {
            if(matrix[0][i] == 0) {
                setCol(matrix, i);
            }
        }
        if(firstRow) {
            setRow(matrix, 0);
        }
        if(firstCol) {
            setCol(matrix, 0);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
		int[][] matrix = Input.buildMatrixWithZeros(10, 8);
        Output.printMatrix(matrix);
        System.out.println();
		solution.setZeroes(matrix);
		Output.printMatrix(matrix);
    }
}