/*
	假设你是harry potter，在grid的左上角，你现在要走到右下角，grid中有
	正数也有负数，遇到正数表示你的strength增加那么多，遇到负数表示strength减少那
	么多，在任何时刻如果你的strength小于等于0，那么你就挂了。在一开始你有一定的
	初始的strength，现在问这个初始的strength最少是多少，才能保证你能够找到一条路
	走到右下角。每一步只能向右或者向下。

	http://www.mitbbs.com/article_t/JobHunting/32611137.html
*/

class Status {
	int strengthNeedMax;
	int strengthLeft;

	public Status(int a, int b) {
		strengthNeedMax = a;
		strengthLeft = b;
	}
}

class Main {
	private static void search(int[][] grid, Status min, Status st, int row, int col) {
		if(row >= grid.length || col >= grid[0].length) return;
		int needMax = st.strengthNeedMax;
		int left = st.strengthLeft + grid[row][col];
		if(grid[row][col] < 0) {	// cost strength
			if(left < 0) {	// need more
				if(st.strengthNeedMax < Math.abs(left)) {
					needMax = Math.abs(left);
				}
			}
		}
		Status nst = new Status(needMax, left);
		// arrive end
		if(row == grid.length - 1 && col == grid[0].length - 1) {
			min.strengthNeedMax = (min.strengthNeedMax < nst.strengthNeedMax ? min.strengthNeedMax : nst.strengthNeedMax);
			return;
		}
		// move right
		int right = col + 1;
		if(right < grid[0].length) {
			search(grid, min, nst, row, right);
		}
		// move dowm
		int down = row + 1;
		if(down < grid.length) {
			search(grid, min, nst, down, col);
		}
	}

	public static int search(int[][] grid) {
		Status min = new Status(Integer.MAX_VALUE, 0);
		search(grid, min, new Status(0, 0), 0, 0);
		return min.strengthNeedMax;
	}

	public static void main(String[] args) {
		int[][] grid = {
			{-1, 5, -7, 1},
			{-2, -12, -11, 2},
			{-1, 2, -1, 0},
		};
		System.out.println(search(grid));
	}
}



