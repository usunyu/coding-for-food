class Q9_8App {
	static int count = 0;

	public static void getChange(int n, int coin) {
		if(n < 0)
			return;

		if(n == 0) {	// one combo
			count++;
			return;
		}

		int nextCoin = 0;

		switch(coin) {
			case 25:
				nextCoin = 10;
				break;
			case 10:
				nextCoin = 5;
				break;
			case 5:
				nextCoin = 1;
				break;
			case 1:
				nextCoin = -1;
				break;
			default:
				return;
		}

		for(int i = 0; i * coin <= n; i++) {
			getChange(n - i * coin, nextCoin);
		}
	}

	public static void main(String[] args) {
		int n = 100;
		getChange(n, 25);
		System.out.println("There are " + count + " ways to represent " + n);
	}
}