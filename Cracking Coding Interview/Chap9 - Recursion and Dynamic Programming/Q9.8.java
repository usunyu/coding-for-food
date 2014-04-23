/*
Given an infinite number of quarters (25 cents), dimes (10 cents), nickels (5 cents) and pennies (1 cent), 
write code to calculate the number of ways of representing n cents.
*/
class Solution {
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
		for(int n = 1; n <= 100; n++) {
			getChange(n, 25);
			System.out.println("getChange(" + n + "): " + count);
			count = 0;	// reset
		}
	}
}
/*
	Second Round
*/
class Solution2 {
	// coin: 1, 5, 10, 25
	public static int getChange(int n, int coin) {
		if(n < 0) return 0;
		if(n == 0) return 1;
		int next = 0;
		switch(coin) {
			case 25:
				next = 10;
				break;
			case 10:
				next = 5;
				break;
			case 5:
				next = 1;
				break;
			case 1:
				next = 0;
				break;
			default:
				return 0;
		}
		int count = 0;
		for(int i = 0; i * coin <= n; i++) {
			count += getChange(n - i * coin, next);
		}
		return count;
	}

	public static void main(String[] args) {
		for(int n = 1; n <= 100; n++) {
			System.out.println("getChange(" + n + "): " + getChange(n, 25));
		}
	}
}