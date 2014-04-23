/*
Given an NxN matrix of positive and negative integers, write code to find the submatrix with the largest 
possible sum.
*/

import CtCILibrary.AssortedMethods;

class Solution {
	// Dynamic Programming Solution: O(N^4)
	public static int getMaxMatrix(int[][] original) {
		int maxArea = Integer.MIN_VALUE; // Important! Max could be < 0
		int rowCount = original.length;
		int columnCount = original[0].length;
		int[][] matrix = precomputeMatrix(original);
		for (int row1 = 0; row1 < rowCount; row1++) {
			for (int row2 = row1; row2 < rowCount; row2++) {
				for (int col1 = 0; col1 < columnCount; col1++) {
					for (int col2 = col1; col2 < columnCount; col2++) {
						int sum = computeSum(matrix, row1, row2, col1, col2);
						if (sum > maxArea) {
							System.out.println("New Max of " + sum + ": (rows " + row1 + " to " + row2 + ") and (columns " + col1 + " to " + col2 + ")");
							maxArea = sum;
						}
					}
				}
			}
		}
		return maxArea;
	}

	private static int computeSum(int[][] sumMatrix, int i1, int i2, int j1, int j2) {
		if (i1 == 0 && j1 == 0) { // starts at row 0, column 0
			return sumMatrix[i2][j2];
		} else if (i1 == 0) { // starts at row 0
			return sumMatrix[i2][j2] - sumMatrix[i2][j1 - 1];
		} else if (j1 == 0) { // starts at column 0
			return sumMatrix[i2][j2] - sumMatrix[i1 - 1][j2];
		} else {
			return sumMatrix[i2][j2] - sumMatrix[i2][j1 - 1] - sumMatrix[i1 - 1][j2] + sumMatrix[i1 - 1][j1 - 1];
		}
	}

	private static int[][] precomputeMatrix(int[][] matrix) {
		int[][] sumMatrix = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (i == 0 && j == 0) { // first cell
					sumMatrix[i][j] = matrix[i][j];
				} else if (j == 0) { // cell in first column
					sumMatrix[i][j] = sumMatrix[i - 1][j] + matrix[i][j];
				} else if (i == 0) { // cell in first row
					sumMatrix[i][j] = sumMatrix[i][j - 1] + matrix[i][j];
				} else {
					sumMatrix[i][j] = sumMatrix[i - 1][j] + 
					  sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1] +
					  matrix[i][j];
				}
			}
		}
		return sumMatrix;
	}

	public static void clearArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	// O(N^3)
	public static int maxSubMatrix(int[][] matrix) {
		int rowCount = matrix.length;
		int colCount = matrix[0].length;

		int[] partialSum = new int[colCount]; 
		int maxSum = 0; // Max sum is an empty matrix

		for (int rowStart = 0; rowStart < rowCount; rowStart++) {
			clearArray(partialSum);
			for (int rowEnd = rowStart; rowEnd < rowCount; rowEnd++) {
				for (int i = 0; i < colCount; i++) {
					partialSum[i] += matrix[rowEnd][i];
				}
				int tempMaxSum = maxSubArray(partialSum, colCount);
				// if you want to track the coordinates, add code here to do that
				maxSum = Math.max(maxSum, tempMaxSum);
			}
		}
		return maxSum;
	}

	// O(N)
	public static int maxSubArray(int array[], int N) {
		int maxSum = 0;
		int runningSum = 0;

		for (int i = 0; i < N; i++) {
			runningSum += array[i];
			maxSum = Math.max(maxSum, runningSum);

			/* If running_sum is < 0 no point in trying to continue the series. Reset. */
			if (runningSum < 0) {
				runningSum = 0;
			}
		}
		return maxSum;
	}

	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(10, 10, -5, 5);
		AssortedMethods.printMatrix(matrix);
		System.out.println(getMaxMatrix(matrix));
		System.out.println(maxSubMatrix(matrix));
	}
}