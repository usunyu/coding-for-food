class Q9_2App {
	static int count = 0;
	static int noStepX = 10;
	static int noStepY = 5;

	public static int robotMoveCombination(int x, int y) {
		long cX = 0, cY = 0, cX_Y = 0, sum = 1;
		for(int i = 1; i <= x + y; i++) {
			sum *= i;
			if(i == x)
				cX = sum;
			if(i == y)
				cY = sum;
			if(i == (x + y))
				cX_Y = sum;
		}
		int combination = (int)(cX_Y / (cX * cY));
		return combination;
	}

	public static void robotMoveCount(int x, int y) {
		if(x == noStepX && y == noStepY) {
			return;
		}
		if(x == 0 && y == 0) {
			count++;
			return;
		}
		if(x > 0)	// move right
			robotMoveCount(x - 1, y);
		if(y > 0)	// move down
			robotMoveCount(x, y - 1);
	}

	public static void main(String[] args) {
		int x = 10;
		int y = 4;
		robotMoveCount(x, y);
		System.out.println("There are " + count + " possibiles.");
		System.out.println("There are " + robotMoveCombination(x, y) + " possibiles.");
	}
}
