/*
	Find the k-th Smallest Element in the Union of Two Sorted Arrays

	Given two sorted arrays A, B of size m and n respectively.
	Find the k-th smallest element in the union of A and B.
	You can assume that there are no duplicate elements.

	http://www.careercup.com/page?pid=google-interview-questions
	http://blog.csdn.net/yutianzuijin/article/details/11499917
*/

class Main {
	public static int findKth(int[] A, int aStart, int aLength, int[] B, int bStart, int bLength, int k) {
		// always assume aLength is smaller than bLength
		if(aLength > bLength) {
			return findKth(B, bStart, bLength, A, aStart, aLength, k);
		}
		if(aLength == 0) {
			return B[k - 1];
		}
		if(k == 1) {
			return Math.min(A[aStart], B[bStart]);
		}
		int length = Math.min(k / 2, aLength);
		int pA = aStart + length;
		int pB = bStart + (k - length);
		if(A[pA - 1] < B[pB - 1]) {
			return findKth(A, pA, aLength - length, B, bStart, bLength, k - length);
		}
		else if(A[pA - 1] > B[pB - 1]) {
			return findKth(A, aStart, aLength, B, pB, bLength - (k - length), length);
		}
		else {
			return A[pA - 1];
		}
	}

	public static int findKth(int[] A, int[] B, int k) {
		return findKth(A, 0, A.length, B, 0, B.length, k);
	}

	public static void main(String[] args) {
		int A[] = {1, 2, 3};
        int B[] = {2, 3, 4, 5, 6, 7, 8};
        System.out.println(findKth(A, B, 6));
	}
}