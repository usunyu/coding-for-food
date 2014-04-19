/*
Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes, 
write a method to rotate the image by 90 degrees. Canyou do this in place?
*/

import CtCILibrary.*;

class Solution {
	static int N = 10;
	static char[][] matrixImage = new char[N][N];

	public static void initial() {
		int ch = 97;
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++) {
				matrixImage[i][j] = (char)ch;
				ch++;
				if(ch > 122)
					ch = 97;
			}
	}

	public static void rotate() {
		for(int i = 0; i < N / 2; i++) {
			for(int j = i; j < N - i - 1; j++) {
				char temp = matrixImage[i][j];
				// first move
				matrixImage[i][j] = matrixImage[N - j - 1][i];
				// second move
				matrixImage[N - j - 1][i] = matrixImage[N - i - 1][N - j - 1];
				// third move
				matrixImage[N - i - 1][N - j - 1] = matrixImage[j][N - i - 1];
				// last move
				matrixImage[j][N - i - 1] = temp;
			}
		}
	}

	public static void display() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(matrixImage[i][j] + "   ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("Initial:");
		initial();
		display();
		System.out.println("Rotate:");
		rotate();
		display();
	}
}
/*
	Second Round
*/
class Solution2 {
	public static void rotate(int[][] matrix) {
		int N = matrix.length;
		for(int i = 0; i < N / 2; i++) {
			for(int j = i; j < N - i - 1; j++) {
				int tmp = matrix[i][j];
				// right --> top
				matrix[i][j] = matrix[i + j][N - i - 1];
				// bottom --> right
				matrix[i + j][N - i - 1] = matrix[N - i - 1][N - j - 1];
				// left --> bottom
				matrix[N - i - 1][N - j - 1] = matrix[N - j - 1][i];
				// top --> left
				matrix[N - j - 1][i] = tmp;
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(10, 10, 0, 9);
		AssortedMethods.printMatrix(matrix);
		rotate(matrix);
		System.out.println();
		AssortedMethods.printMatrix(matrix);
	}
}