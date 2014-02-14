import java.io.*;

class KnapsackApp {
	static int C;
	static int n;
	static int[] items;
	static int[][] M;

	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	public static void display() {
		for(int i = 0; i < n + 1; i++) {
			for(int j = 0; j < C + 1; j++)
				System.out.print(M[i][j] + "\t");
			System.out.println();
		}
		System.out.println();
	}

	public static void output() {
		int max = 0;
		int indexMax = 0;
		for(int i = 1; i < n + 1; i++) {
			if(M[i][C] > max) {
				max = M[i][C];
				indexMax = i;
			}
		}
		System.out.println(max);

		int volume = C;
		for(int i = indexMax; i > 0; i--) {
			if(M[i][volume] == M[i - 1][volume]) {
				// didn't use item i - 1
			}
			else {
				// use item i - 1
				System.out.print(items[i - 1] + " ");
				volume -= items[i - 1];
			}
		}
		System.out.println();
	}

	public static void knapsack() {
		// initial
		for(int i = 0; i < n + 1; i++)
			M[i][0] = 0;
		for(int i = 0; i < C + 1; i++)
			M[0][i] = 0;

		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < C + 1; j++) {
				if(j < items[i - 1])
					M[i][j] = M[i - 1][j];
				else
					M[i][j] = max(M[i - 1][j], M[i - 1][j - items[i - 1]] + items[i - 1]);
			}
		}
		// display();
		output();
	}

	public static void recKnapsack(int volume, int index) {
		if(index >= n) {
			return;
		}
		else if(volume < items[index]) {
			// don't use item
			recKnapsack(volume, index + 1);
		}
		else if(volume == items[index]) {
			System.out.println("Sucess!");
		}
		else {
			// use item
			recKnapsack(volume - items[index], index + 1);
			// don't use item
			recKnapsack(volume, index + 1);
		}
	}

	public static void main(String[] args) {
		C = 20;
		n = 5;
		M = new int[n + 1][C + 1];
		items = new int[n];
		items[0] = 11;
		items[1] = 8;
		items[2] = 7;
		items[3] = 6;
		items[4] = 5;

		knapsack();

		recKnapsack(C, 0);
	}
}


