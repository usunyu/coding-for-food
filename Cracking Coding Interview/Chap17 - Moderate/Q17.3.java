/*
Write an algorithm which computes the number of trailing zeros in n factorial.
*/

class Solution {
	public static int trailZeros(int n) {
		if(n < 0) return -1;
		int count = 0;
		for(int i = 5; n / i > 0; i *= 5) {
			count += n / i;
		}
		return count;
	}

	public static void main(String[] args) {
		int n = 99;
		System.out.println(n + ": " + trailZeros(n));
	}
}
/*
	Second Round
*/
class Solution2 {
	public static int trailZeros(int n) {
		if(n < 0) return -1;
		int th_5 = 1;
		while(th_5 < n) th_5 *= 5;
		th_5 /= 5;
		int count = n / 5;
		while(th_5 > 5) {
			count += n / th_5;
			th_5 /= 5;
		}
		return count;
	}

	public static void main(String[] args) {
		int n = 99;
		System.out.println(n + ": " + trailZeros(n));
	}
}