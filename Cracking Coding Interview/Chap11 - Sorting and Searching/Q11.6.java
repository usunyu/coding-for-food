/*
Given an M X N matrix in which each row and each column is sorted in ascending order, 
write a method to find an element.
*/

// time complexity : O(logN x logM)
// space complexity : O(1)

class Solution {

	// return the nearest smaller column
	public static int findInRow(int[][] matrix, int value, int start, int end) {
		if(start + 1 == end || start == end) {	// close
			if(matrix[0][end] <= value)
				return end;
			else
				return start;
		}

		int mid = (start + end) / 2;

		if(matrix[0][mid] > value) {
			return findInRow(matrix, value, start, mid - 1);
		}
		else if(matrix[0][mid] < value) {
			return findInRow(matrix, value, mid + 1, end);
		}
		else 
			return mid;	// found it
	}

	// find in column
	public static int findInColumn(int[][] matrix, int value, int start, int end, int column) {
		if(start > end)
			return -1;
		int mid = (start + end) / 2;
		if(matrix[mid][column] > value) {
			return findInColumn(matrix, value, start, mid - 1, column);
		}
		else if(matrix[mid][column] < value) {
			return findInColumn(matrix, value, mid + 1, end, column);
		}
		else
			return mid;
	}

	public static void main(String[] args) {
		int M = 4; int N = 4;
		int[][] matrix = new int[5][4];
		matrix[0][0] = 15; matrix[0][1] = 20; matrix[0][2] = 40; matrix[0][3] = 85;
		matrix[1][0] = 25; matrix[1][1] = 35; matrix[1][2] = 80; matrix[1][3] = 95;
		matrix[2][0] = 30; matrix[2][1] = 55; matrix[2][2] = 95; matrix[2][3] = 105;
		matrix[3][0] = 40; matrix[3][1] = 80; matrix[3][2] = 100; matrix[3][3] = 120;
		int value = 55;
		int column = findInRow(matrix, value, 0, N - 1);
		int row = -1;
		int col = -1;
		for(int i = 0; i <= column; i++) {
			row = findInColumn(matrix, value, 0, M - 1, i);
			if(row != -1) {
				col = i;
				break;
			}
		}
		System.out.println("Find it in : Row: " + row + " Col: " + col);
	}
}