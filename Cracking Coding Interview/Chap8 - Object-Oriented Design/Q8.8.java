enum Color {
	White,
	Black
}

class Movement {
	int startX, startY;
	int toX, toY;

	public Movement(int startX, int startY, int toX, int toY) {
		this.startX = startX;
		this.startY = startY;
		this.toX = toX;
		this.toY = toY;
	}
}

class Player {
	int pieceCount;
	Othello game;
	boolean noMove;

	public Player(Othello game) {
		this.game = game;
	}

	public Movement getMove() {
		Movement move = null;	//... check
		if(move == null)
			noMove = true;
		return move;
	}

	public void applyMove(Movement move) {

	}

	public boolean isNoMove() {
		return noMove;
	}

	public boolean checkMoveUp(int x, int y) {
		//...
		return false;
	}

	public boolean checkMoveDown(int x, int y) {
		//...
		return false;
	}

	public boolean checkMoveLeft(int x, int y) {
		//...
		return false;
	}

	public boolean checkMoveRight(int x, int y) {
		//...
		return false;
	}
}

class Othello {
	int M = 8;
	int[][] gameBoard;

	public Othello() {
		gameBoard = new int[M][M];
	}

	public void play() {
		Player p1 = new Player(this);
		Player p2 = new Player(this);

		while(!p1.noMove && !p2.noMove) {
			Movement m1 = p1.getMove();
			p1.applyMove(m1);
			Movement m2 = p2.getMove();
			p2.applyMove(m2);
		}

		if(p1.pieceCount > p2.pieceCount)
			System.out.println("Player 1 win");
		else if(p2.pieceCount > p1.pieceCount)
			System.out.println("Player 2 win");
		else
			System.out.println("Draw");
	}
}

class Q8_8App {
	public static void main(String[] args) {

	}
}