import java.util.*;

class Q9_1App {
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