import java.io.*;

class MakeBranchesApp {
	static int lineSpace;
	static int colSpace;
	static int workSpace[][];


	public static void displaySpace() {
		for(int i = 0; i < lineSpace; i++) {
			for(int j = 0; j < colSpace; j++) {
				if(workSpace[i][j] == 0)
					System.out.print("- ");
				else
					System.out.print("X ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void makeBranches(int left, int right, int level) {
		if(left > right || level >= lineSpace)
			return;
		int mid = (left + right) / 2;
		workSpace[level][mid] = 1;
		makeBranches(left, mid, level + 1);
		makeBranches(mid + 1, right, level + 1);
	}

	public static int calColumn(int line) {
		int column = 1;
		for(int i = 0; i < line - 1; i++)
			column *= 2;
		return column;
	}

	public static void main(String[] args) {
		lineSpace = 7;
		colSpace = calColumn(lineSpace);
		workSpace = new int[lineSpace][colSpace];
		makeBranches(0, colSpace - 1, 0);
		displaySpace();
	}
}




