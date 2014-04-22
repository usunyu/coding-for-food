/*
You are given an array of integers (both positive and negative). 
Find the contiguous sequence with the largest sum. Return the sum.
*/

class Solution {
	public static int largestSum(int[] array) {
		int max = 0, sum = 0;
		int maxLeft = 0, maxRight = -1;
		int left = 0, right = -1;
		for(int i = 0; i < array.length; i++) {
			sum += array[i];
			if(sum > max) {
				max = sum;
				right = i;
				maxLeft = left;
				maxRight = right;
			}
			if(sum < 0) {
				left = i + 1;
				sum = 0;
			}
		}
		System.out.println("Max is: " + max);
		System.out.print("[");
		for(int i = maxLeft; i <= maxRight; i++) {
			if(i == maxRight)
				System.out.print(array[i]);
			else
				System.out.print(array[i] + ", ");
		}
		System.out.println("]");
		return max;
	}

	public static void main(String[] args) {
		int[] array = {2,3,-8,-1,2,4,-2,3};
		largestSum(array);
	}
}
/*
	Second Round
*/
class Solution2 {
	public static int lcs(int[] array) {
		if(array == null || array.length == 0) return Integer.MIN_VALUE;
		int max = array[0], sum = 0;
		for(int i = 0; i < array.length; i++) {
			sum += array[i];
			max = Math.max(max, sum);
			if(sum < 0) sum = 0;
		}
		return max;
	}

	public static void main(String[] args) {
		int[] array = {2,3,-8,-1,2,4,-2,3};
		System.out.println(lcs(array));
	}
}