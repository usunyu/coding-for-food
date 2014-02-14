import java.util.*;

class Index {
	public int i, j;

	public Index(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

class Q1_7App {
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
}