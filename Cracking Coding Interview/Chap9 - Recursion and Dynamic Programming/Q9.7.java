/*
Implement the "paint fill" function that one might see on many image editing programs. 
That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color, 
fill in the surrounding area until the color changes from the original color.
*/

class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {
	static int M = 10;
	static int[][] screen = new int[M][M];

	public static void output() {
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++)
				System.out.print(screen[i][j] + "\t");
			System.out.println();
		}
		System.out.println();
	}

	public static void draw(int color) {
		for(int i = 2; i < 7; i++)
			screen[2][i] = color;
		for(int i = 1; i < 8; i++)
			screen[3][i] = color;
		for(int i = 2; i < 7; i++)
			screen[4][i] = color;
		for(int i = 2; i < 6; i++)
			screen[5][i] = color;
		for(int i = 3; i < 7; i++)
			screen[6][i] = color;
		for(int i = 3; i < 8; i++)
			screen[7][i] = color;
	}

	public static void paint(int from, int to, Point p) {
		int x = p.x;
		int y = p.y;
		if(x < 0 || y < 0 || x >= M || y >= M )
			return;
		if(screen[x][y] == from) {
			screen[x][y] = to;
			paint(from, to, new Point(x, y + 1));
			paint(from, to, new Point(x, y - 1));
			paint(from, to, new Point(x + 1, y));
			paint(from, to, new Point(x - 1, y));
		}
	}

	public static void main(String[] args) {
		// output();
		draw(5);
		output();
		paint(0, 3, new Point(0, 0));
		output();
	}
}