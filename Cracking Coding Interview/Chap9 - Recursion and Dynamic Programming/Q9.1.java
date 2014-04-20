/*
A child is running up a staircase with n steps, and can hop either 1step, 2 steps, or 3 steps at a time. 
Implement a method to count how many possible ways the child can run up the stairs.
*/

import java.util.*;

class Solution {
	public static long[] cache;

	public static long countWaysDP(int n, long[] map) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (map[n] > -1) {
			return map[n];
		} else {
			map[n] = countWaysDP(n - 1, map) + 
					 countWaysDP(n - 2, map) + 
					 countWaysDP(n - 3, map);
			return map[n];
		}
	}

	public static long countPossibile(int n) {
		if(n <= 0)
			return 0;

		long count = 0;
		if(n <= 3 && n >= 1) {
			if(n == 1) {
				count = 1;
			}
			if(n == 2) {
				count = 2;
			}
			if(n == 3) {
				count = 4;
			}
		}
		else {
			if(cache[n] != 0)
				count = cache[n];
			else
				count = countPossibile(n - 1) + countPossibile(n - 2) + countPossibile(n - 3);
		}

		cache[n] = count;
		return count;
	}

	public static void main(String[] args) {
		int n = 100;
		cache = new long[n + 1];
		long[] map = new long[n + 1];
		for (int j = 0; j < map.length; j++) {
			map[j] = -1;
		}
		System.out.println("There is " + countPossibile(n) + " ways.");
		System.out.println("There is " + countWaysDP(n, map) + " ways.");
	}
}
/*
	Second Round
*/
class Solution2 {
	public static long countWaysDP(int n) {
		long[] dp = new long[n + 1];
		dp[1] = 1; dp[2] = 2; dp[3] = 4;
		for(int i = 4; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}
		return dp[n];
	}

	public static void main(String[] args) {
		int N = 100;
		long count = countWaysDP(N);
		System.out.println("There is " + count + " ways.");
	}
}
