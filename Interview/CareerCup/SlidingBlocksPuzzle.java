/**
 * @App:			Sliding Block Puzzle
 *
 * @Description:	Heuristic:
 *					H1(n) = number of misplaced tiles
 *					H2(n) = total distance (i.e., no. of squares from desired location of each tiles)
 *					H(n) = max(H1(n), H2(n))
 *
 * @Author:			Yu Sun
 * @Date:			2013-3-24
*/
import java.util.*;

class PuzzleApp {
	static class Point {
		int x;
		int y;
	}

	public enum Action {
		RightMove,
		LeftMove,
		UpMove,
		DownMove
	}

	// using to indicate the same state which action already have been taken
	static class Moved {
		int upMoved;
		int downMoved;
		int leftMoved;
		int rightMoved;
	}

	static int MAX = 999;
	static int N = 3;
	static boolean Debug = false;
	static Action prevAction;
	static Hashtable <String, Moved> exploredStates = new Hashtable<String, Moved>();
	// static int[][] puzzleGrid = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {0, 13, 14, 15}};
	static int[][] puzzleGrid = {{5, 4, 8}, {6, 1, 0}, {7, 3, 2}};
	// static int[][] puzzleGrid = {{1, 2, 3}, {4, 5, 0}, {6, 7, 8}};

	public static int absolute(int value) {
		if(value >= 0)
			return value;
		else
			return 0 - value;
	}

	public static int min(int m1, int m2, int m3, int m4) {
		int min = m1;
		if(min > m2)
			min = m2;
		if(min > m3)
			min = m3;
		if(min > m4)
			min = m4;
		return min;
	}

	public static String puzzleGridString() {
		String str = "";
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				str += puzzleGrid[i][j];
			}
		}
		return str;
	}

	public static int calRightX(int i, int j) {
		return (puzzleGrid[i][j] - 1) / N;
	}

	public static int calRightY(int i, int j) {
		return (puzzleGrid[i][j] - 1) % N;
	}

	// H1(n) = number of misplaced tiles
	public static int calH1() {
		int misplacedTiles = 0;
		int rightNumbers = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(puzzleGrid[i][j] != rightNumbers) {
					if(rightNumbers != N * N)
						misplacedTiles++;
				}
				rightNumbers++;
			}
		}
		return misplacedTiles;
	}

	// H2(n) = total distance (i.e., no. of squares from desired location of each tiles)
	public static int calH2() {
		int totalDistance = 0;
		int rightX, rightY;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(puzzleGrid[i][j] != 0) {
					rightX = calRightX(i, j);
					rightY = calRightY(i, j);
					totalDistance += absolute(rightX - i) + absolute(rightY - j);
				}
			}
		}
		return totalDistance;
	}

	// H(n) = max(H1(n), H2(n))
	public static int calH(int h1, int h2) {
		if(h1 < h2)
			return h2;
		else
			return h1;
	}

	// G(n) = the no. of same action
	public static int calG(Action action) {
		int sameMoves = 0;
		String statesString = puzzleGridString();
		if(exploredStates.containsKey(statesString)) {
			Moved moved = exploredStates.get(statesString);
			switch(action) {
				case LeftMove:
					sameMoves = moved.leftMoved;
					break;
				case RightMove:
					sameMoves = moved.rightMoved;
					break;
				case UpMove:
					sameMoves = moved.upMoved;
					break;
				case DownMove:
					sameMoves = moved.downMoved;
					break;
				default:
					break;
			}
		}
		return sameMoves;
	}

	public static Point findBlankPoint() {
		Point blankPoint = new Point();
		boolean found = false;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(puzzleGrid[i][j] == 0) {
					blankPoint.x = i;
					blankPoint.y = j;
					found = true;
					break;
				}
			}
			if(found)
				break;
		}
		return blankPoint;
	}

	public static void showGrid() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(puzzleGrid[i][j] == 0)
					System.out.print("\t");
				else
					System.out.print(puzzleGrid[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void moveDown(int x, int y) {
		int temp = puzzleGrid[x + 1][y];
		puzzleGrid[x + 1][y] = puzzleGrid[x][y];
		puzzleGrid[x][y] = temp;
	}

	public static void moveUp(int x, int y) {
		int temp = puzzleGrid[x - 1][y];
		puzzleGrid[x - 1][y] = puzzleGrid[x][y];
		puzzleGrid[x][y] = temp;
	}

	public static void moveLeft(int x, int y) {
		int temp = puzzleGrid[x][y - 1];
		puzzleGrid[x][y - 1] = puzzleGrid[x][y];
		puzzleGrid[x][y] = temp;
	}

	public static void moveRight(int x, int y) {
		int temp = puzzleGrid[x][y + 1];
		puzzleGrid[x][y + 1] = puzzleGrid[x][y];
		puzzleGrid[x][y] = temp;
	}

	public static boolean checkRepeat(Action action) {
		String statesString = puzzleGridString();
		if(exploredStates.containsKey(statesString)) {
			Moved moved = exploredStates.get(statesString);
			switch(action) {
				case LeftMove:
					if(moved.leftMoved >= 10)
						return true;
					else
						return false;
				case RightMove:
					if(moved.rightMoved >= 10)
						return true;
					else
						return false;
				case UpMove:
					if(moved.upMoved >= 10)
						return true;
					else
						return false;
				case DownMove:
					if(moved.downMoved >= 10)
						return true;
					else
						return false;
				default:
					return false;
			}
		}
		else
			return false;
	}

	public static Action makeDecision(Point blankPoint) {
		int upF, downF, rightF, leftF;
		int x  = blankPoint.x;
		int y = blankPoint.y;
		if(x == 0 || prevAction == Action.DownMove/* || checkRepeat(Action.UpMove)*/)
			upF = MAX;
		else {
			int g = calG(Action.UpMove);
			// move the blank point's up point
			moveDown(x - 1, y);
			if(Debug) {
				System.out.println();
				showGrid();
			}
			int h1 = calH1();
			int h2 = calH2();
			upF = calH(h1, h2) + g;
			// restore to orginal grid
			moveUp(x, y);
		}

		if(x == N - 1 || prevAction == Action.UpMove/* || checkRepeat(Action.DownMove)*/)
			downF = MAX;
		else {
			int g = calG(Action.DownMove);
			// move the blank point's down point
			moveUp(x + 1, y);
			if(Debug) {
				System.out.println();
				showGrid();
			}
			int h1 = calH1();
			int h2 = calH2();
			downF = calH(h1, h2) + g;
			// restore to orginal grid
			moveDown(x, y);
		}

		if(y == 0 || prevAction == Action.RightMove/* || checkRepeat(Action.LeftMove)*/)
			leftF = MAX;
		else {
			int g = calG(Action.LeftMove);
			// move the blank point's left point
			moveRight(x, y - 1);
			if(Debug) {
				System.out.println();
				showGrid();
			}
			int h1 = calH1();
			int h2 = calH2();
			leftF = calH(h1, h2) + g;
			// restore to orginal grid
			moveLeft(x, y);
		}

		if(y == N - 1 || prevAction == Action.LeftMove/* || checkRepeat(Action.RightMove)*/)
			rightF = MAX;
		else {
			int g = calG(Action.RightMove);
			// move the blank point's right point
			moveLeft(x, y + 1);
			if(Debug) {
				System.out.println();
				showGrid();
			}
			int h1 = calH1();
			int h2 = calH2();
			rightF = calH(h1, h2) + g;
			// restore to orginal grid
			moveRight(x, y);
		}
		int min = min(upF, downF, rightF, leftF);
		if(min == upF)
			return Action.UpMove;
		else if(min == downF)
			return Action.DownMove;
		else if(min == rightF)
			return Action.RightMove;
		else
			return Action.LeftMove;
	}

	public static void makeAction(Point blankPoint, Action action) {
		int x  = blankPoint.x;
		int y = blankPoint.y;
		String statesString = puzzleGridString();
		Moved moved;
		if(!exploredStates.containsKey(statesString)) {
			moved = new Moved();
			// moved.upMoved = 0;
			// moved.downMoved = 0;
			// moved.leftMoved = 0;
			// moved.rightMoved = 0;
		}
		else {
			moved = exploredStates.get(statesString);
		}
		switch(action) {
			case LeftMove:
				moved.leftMoved++;
				prevAction = Action.LeftMove;
				moveRight(x, y - 1);
				break;
			case RightMove:
				moved.rightMoved++;
				prevAction = Action.RightMove;
				moveLeft(x, y + 1);
				break;
			case UpMove:
				moved.upMoved++;
				prevAction = Action.UpMove;
				moveDown(x - 1, y);
				break;
			case DownMove:
				moved.downMoved++;
				prevAction = Action.DownMove;
				moveUp(x + 1, y);
				break;
			default:
				break;
		}
		// store the state and action to avoid repeating
		exploredStates.put(statesString, moved);
	}

	public static void main(String[] args) {
		int step = 1;
		System.out.println("Initial State:");
		showGrid();
		Point blankPoint = new Point();
		while(calH1() != 0) {
			blankPoint = findBlankPoint();
			Action action = makeDecision(blankPoint);
			makeAction(blankPoint, action);
			System.out.println("Move " + step + " State:");
			showGrid();
			step++;
		}
	}
}
