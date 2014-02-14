import java.util.*;

class Q1_6App {
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