/*
Design an algorithm to figure out if someone has won a game of tic-tac-toe.
*/

class Solution {
	static int N = 3;

	public static boolean checkColumn(char[][] board, char ch) {
		for(int i = 0; i < N; i++) {
			int j;
			for(j = 0; j < N; j++) {
				if(board[j][i] != ch) break;
			}
			if(j == N) return true;
		}
		return false;
	}

	public static boolean checkRow(char[][] board, char ch) {
		for(int i = 0; i < N; i++) {
			int j;
			for(j = 0; j < N; j++) {
				if(board[i][j] != ch) break;
			}
			if(j == N) return true;
		}
		return false;
	}

	public static boolean checkDiagonal(char[][] board, char ch) {
		int i, j;
		for(i = 0, j = 0; i < N && j < N; i++, j++) {
			if(board[i][j] != ch) break;
		}
		if(i == N) return true;
		for(i = N - 1, j = 0; i < N && j < N; i--, j++) {
			if(board[i][j] != ch) break;
		}
		if(i == 0) return true;
		return false;
	}

	public static void main(String[] args) {
		char[][] board = {
			{'O','O','X'},
			{'X','X','O'},
			{'X','X','X'}
		};

		if(checkColumn(board, 'O') || checkRow(board, 'O') || checkDiagonal(board, 'O')) {
			System.out.println("O has won.");
		}

		if(checkColumn(board, 'X') || checkRow(board, 'X') || checkDiagonal(board, 'X')) {
			System.out.println("X has won.");
		}
	}
}

