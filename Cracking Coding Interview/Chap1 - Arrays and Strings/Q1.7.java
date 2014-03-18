/*
Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0.
*/

import java.util.*;
import CtCILibrary.AssortedMethods;
/*
class Index {
	public int i, j;

	public Index(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
*/
class Solution {
	/*
	static int M = 10, N = 8;
	static int[][] matrix = new int[M][N];

	public static void initial() {
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

	public static void setZero() {
		ArrayList<Index> indexList = new ArrayList<Index>();
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] == 0) {
					Index id = new Index(i, j);
					indexList.add(id);
				}
			}
		}
		doSet(indexList);
	}

	private static void doSet(ArrayList<Index> list) {
		for(int i = 0; i < list.size(); i++) {
			setRow(list.get(i).i);
			setColumn(list.get(i).j);
		}
	}

	private static void setRow(int row) {
		for(int i = 0; i < N; i++)
			matrix[row][i] = 0;
	}

	private static void setColumn(int column) {
		for(int i = 0; i < M; i++)
			matrix[i][column] = 0;
	}

	public static void display() {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("Initial:");
		initial();
		display();
		System.out.println("Set Zero:");
		setZero();
		display();
	}
	*/
	/*
		Second Round
	*/
	public static void setZeros(int[][] matrix) {
		ArrayList<Integer> indexs = new ArrayList<Integer>();
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0)
					indexs.add(i * matrix[0].length + j);
			}
		}
		for(int index : indexs) {
			int i = index / matrix[0].length;	// row
			int j = index % matrix[0].length;	// column
			setRow(matrix, i);
			setColumn(matrix, j);
		}
	}

	public static void setRow(int[][] matrix, int i) {
		for(int j = 0; j < matrix[0].length; j++) 
			matrix[i][j] = 0;
	}

	public static void setColumn(int[][] matrix, int j) {
		for(int i = 0; i < matrix.length; i++)
			matrix[i][j] = 0;
	}

	// mark the first row and first column to set zeros
	// no extra space need
	public static void setZeros2(int[][] matrix) {
		boolean firstRowHasZero = false;
		boolean firstColumnHasZero = false;
		// Check if first row has a zero
		for(int i = 0; i < matrix[0].length; i++) {
			if(matrix[0][i] == 0) {
				firstRowHasZero = true;
				break;
			}
		}
		// Check if first column has a zero
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[i][0] == 0) {
				firstColumnHasZero = true;
				break;
			}
		}
		// Check for zeros in the rest of the array
		for(int i = 1; i < matrix.length; i++) {
			for(int j = 1; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0) {	// set in first column and first row
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		// Nullify rows based on values in first column
		for(int i = 0; i < matrix.length; i++) {
			if(matrix[i][0] == 0)
				setRow(matrix, i);
		}
		// Nullify columns based on values in first row
		for(int i = 0; i < matrix[0].length; i++) {
			if(matrix[0][i] == 0)
				setColumn(matrix, i);
		}
		// Nullify first row
		if(firstRowHasZero)
			setRow(matrix, 0);
		// Nullify first column
		if(firstColumnHasZero)
			setColumn(matrix, 0);
	}

	public static void main(String[] args) {
		int nrows = 10;
		int ncols = 15;
		int[][] matrix1 = AssortedMethods.randomMatrix(nrows, ncols, 0, 100);		
		int[][] matrix2 = cloneMatrix(matrix1);

		AssortedMethods.printMatrix(matrix1);

		setZeros(matrix1);
		setZeros2(matrix2);

		System.out.println();

		AssortedMethods.printMatrix(matrix1);
		System.out.println();
		AssortedMethods.printMatrix(matrix2);

		if (matricesAreEqual(matrix1, matrix2)) {
			System.out.println("Equal");
		} else {
			System.out.println("Not Equal");
		}
	}

	public static boolean matricesAreEqual(int[][] m1, int[][] m2) {
		if (m1.length != m2.length || m1[0].length != m2[0].length) {
			return false;
		}

		for (int k = 0; k < m1.length; k++) {
			for (int j = 0; j < m1[0].length; j++) {
				if (m1[k][j] != m2[k][j]) {
					return false;
				}
			}
		}	
		return true;
	}

	public static int[][] cloneMatrix(int[][] matrix) {
		int[][] c = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				c[i][j] = matrix[i][j];
			}
		}
		return c;
	}
}