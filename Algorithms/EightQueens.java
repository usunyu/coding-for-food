import java.io.*;

class QueensApp {
	static int[][] m;
	static int num;

	public static void output() {
		System.out.println("Solution " + num + ":");
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public static boolean check(int row, int column) {
		if(row == 0)
			return true;
		int i, j;
		// check column
		for(i = 0; i < row; i++) {
			if(m[i][column] == 1)
				return false;
		}
		// check top left to bottom right
		i = row - 1;
		j = column - 1;
		while(i >= 0 && j >= 0) {
			if(m[i][j] == 1)
				return false;
			i--;
			j--;
		}
		// check top right to bottom left
		i = row - 1;
		j = column + 1;
		while(i >= 0 && j <= 7) {
			if(m[i][j] == 1)
				return false;
			i--;
			j++;
		}
		return true;
	}

	public static void solve(int row) {
		for(int i = 0; i < 8; i++) {
			m[row][i] = 1;
			if(check(row, i)) {
				if(row == 7) {
					num++;
					output();
				}
				else
					solve(row + 1);
			}
			m[row][i] = 0;
		}
	}

	public static void main(String[] args) {
		m = new int[8][8];
		num = 0;
		solve(0);
	}
}
