/*
The longest Increasing Subsequence (LIS) problem is to find the length of the longest subsequence of a 
given sequence such that all elements of the subsequence are sorted in increasing order. 
For example, length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33, 50, 60, 80}.

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