/*
http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
*/

class Main {
	public static int LIS(int[] array) {
		int[] dp = new int[array.length];
		// initial
		for(int i = 0; i < array.length; i++) dp[i] = 1;
		// dp
		for(int i = 1; i < array.length; i++) {
			int max = 1;
			for(int j = 0; j < i; j++) {
				if(array[i] > array[j]) {
					max = Math.max(dp[j] + 1, max);
				}
			}
			dp[i] = max;
		}
		// max
		int max = 0;
		for(int i = 0; i < dp.length; i++) max = Math.max(dp[i], max);
		return max;
	}

	public static void main(String[] args) {
		int[] array = { 10, 22, 9, 33, 21, 50, 41, 60 };
		System.out.println(LIS(array));
	}
}