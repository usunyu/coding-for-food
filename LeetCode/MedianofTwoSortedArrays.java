
class Solution {
    public int findKth(int[] A, int aStart, int aLength, int[] B, int bStart, int bLength, int k) {
        // always assume that A is equal or smaller than B
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

    // http://blog.csdn.net/yutianzuijin/article/details/11499917
    // time complexity : O(log(M+N))
    // space complexity : O(1)
    public double findMedianSortedArrays(int A[], int B[]) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int m = A.length, n = B.length;
        if((m + n) % 2 == 1) {
            return findKth(A, 0, m, B, 0, n, (m + n) / 2 + 1);
        }
        else {
            return (findKth(A, 0, m, B, 0, n, (m + n) / 2) + findKth(A, 0, m, B, 0, n, (m + n) / 2 + 1)) / 2.0;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int A[] = {1, 2, 3};
        int B[] = {2, 3, 4, 5, 6, 7, 8};
        System.out.println(solution.findMedianSortedArrays(A, B));
    }
}
