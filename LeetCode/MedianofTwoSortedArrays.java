/*
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/
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
    /*
        Second Round
    */
    private double findKthSortedArrays(int A[], int aStart, int aLength, int B[], int bStart, int bLength, int K) {
        if(aLength > bLength) return findKthSortedArrays(B, bStart, bLength, A, aStart, aLength, K);
        if(aLength == 0) return B[K - 1];
        if(K == 1) return Math.min(A[aStart], B[bStart]);
        int aMid = Math.min(K / 2, aLength), bMid = K - aMid;
        int aPointer = aStart + aMid, bPointer = bStart + bMid;
        if(A[aPointer - 1] < B[bPointer - 1]) return findKthSortedArrays(A, aPointer, aLength - aMid, B, bStart, bLength, K - aMid);
        else if(A[aPointer - 1] > B[bPointer - 1]) return findKthSortedArrays(A, aStart, aLength, B, bPointer, bLength - bMid, K - bMid);
        else return A[aPointer - 1];
    }
    
    public double findMedianSortedArrays2(int A[], int B[]) {
        int m = A.length, n = B.length;
        if((m + n) % 2 == 1) return findKthSortedArrays(A, 0, A.length, B, 0, B.length, (m + n) / 2 + 1);
        else return (findKthSortedArrays(A, 0, m, B, 0, n, (m + n) / 2 + 1) + 
                    findKthSortedArrays(A, 0, m, B, 0, n, (m + n) / 2)) / 2.0;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int A[] = {1, 2, 3};
        int B[] = {2, 3, 4, 5, 6, 7, 8};
        System.out.println(solution.findMedianSortedArrays2(A, B));
    }
}
