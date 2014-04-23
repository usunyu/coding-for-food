/*
Amagic index in an array A[1...n-1] is defined to be an index such that A[i] = i. 
Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in array A.
FOLLOW UP
What if the values are not distinct?
*/
import java.util.ArrayList;

class Solution {

	public static int findMagicDup(int[] A, int start, int end) {
		if(start > end)
			return -1;

		int mid = (start + end) / 2;

		if(A[mid] == mid)
			return mid;

		// search left
		int leftIndex = Math.min(mid - 1, A[mid]);
		int left = findMagicDup(A, start, leftIndex);

		if(left >= 0)
			return left;

		// search right
		int rightIndex = Math.max(mid + 1, A[mid]);
		int right = findMagicDup(A, rightIndex, end);

		return right;
	}

	public static int findMagic(int[] A) {
		int magic = -1;
		for(int i = 0; i < A.length; i++) {
			if(A[i] > i)
				break;
			if(A[i] == i) {
				magic = i;
				break;
			}
		}
		return magic;
	}

	public static int findMagic(int[] A, int start, int end) {
		if(start > end)
			return -1;

		int mid = (start + end) / 2;

		if(A[mid] == mid)
			return mid;

		if(A[mid] < mid)
			return findMagic(A, mid + 1, end);

		if(A[mid] > mid)
			return findMagic(A, start, mid - 1);

		return -1;
	}

	public static void main(String[] args) {
		int[] A = {-9, -5, 1, 3, 9, 10, 11, 12};
		System.out.println("The magic number is " + findMagic(A));
		System.out.println("The magic number is " + findMagic(A, 0, A.length - 1));
		int[] D = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
		System.out.println("The magic number(has dup) is " + findMagicDup(D, 0, D.length - 1));
	}
}
/*
	Second Round
*/
class Solution2 {
	private static void findMagicDup(int[] A, int start, int end, ArrayList<Integer> result) {
		if(start > end) return;
		int mid = start + (end - start) / 2;
		if(mid == A[mid]) result.add(mid);
		if(A[end] >= end) findMagicDup(A, mid + 1, end, result);
		if(A[start] <= start) findMagicDup(A, start, mid - 1, result);
	}

	public static ArrayList<Integer> findMagicDup(int[] A) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		findMagicDup(A, 0, A.length - 1, result);
		return result;
	}

	// only can find one magic number
	public static int findMagicDupBad(int[] A, int start, int end) {
		int left = start, right = end;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(mid == A[mid]) return mid;
			else if(A[mid] < mid) {
				if(A[right] < right) right = mid - 1;
				else left = mid + 1;
			}
			else if(A[mid] > mid) {
				if(A[left] > left) left = mid + 1;
				else right = mid - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] D = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
		System.out.println("The magic number(has dup) is " + findMagicDup(D));
	}
}