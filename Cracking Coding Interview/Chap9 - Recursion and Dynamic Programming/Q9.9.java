/*
Write an algorithm to prim all ways of arranging eight queens on an 8x8 chess board so that none of them 
share the same row, column or diagonal. In this case, "diagonal" means all diagonals, not just the two 
that bisect the board.
*/

class Solution {
	static int count = 0;

	public static void print(int[][] board) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public static boolean check(int[][] board, int level, int column) {
		boolean result = true;
		// check column
		for(int i = 0; i < level; i++) {
			if(board[i][column] == 1) {
				result = false;
				break;
			}
		}
		// check diag
		for(int i = level - 1, add = 1; i >= 0; i--, add++) {
			int left = column - add;
			int right = column + add; 
			if(left >= 0 && board[i][left] == 1) {
				result = false;
				break;
			}
			if(right < 8 && board[i][right] == 1) {
				result = false;
				break;
			}
		}
		return result;
	}

	public static void eightQueens(int[][] board, int level) {
		if(level == 8) {
			print(board);
			count++;
			return;
		}

		for(int i = 0; i < 8; i++) {
			if(check(board, level, i)) {
				board[level][i] = 1;
				eightQueens(board, level + 1);
				board[level][i] = 0;
			}
		}
	}

	public static void main(String[] args) {
		int[][] board = new int[8][8];
		eightQueens(board, 0);
		System.out.println("There are " + count + " ways.");
	}
}