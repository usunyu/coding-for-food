/*
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. 
The number of elements initialized in A and B are m and n respectively.
*/
import java.util.Arrays;

class Solution {
    public void shift(int[] A, int pA, int shift, int m) {
        for(int i = m + shift - 1; i >= (pA + shift); i--) {
            A[i] = A[i - shift];
        }
    }
    
    public void copy(int[]A, int pA, int[] B, int sB, int shift) {
        for(int i = 0; i < shift; i++) {
            A[pA + i] = B[sB + i];
        }
    }
    
    // time complexity : O(MN)
    // space complexity : O(1)
    public void merge(int A[], int m, int B[], int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(m == 0) {
            for(int i = 0; i < n; i++) {
                A[i] = B[i];
            }
        }
        int pA = 0;
        int pB = 0;
        int shift = 0;
        int sB = 0;
        while(pA < m && pB < n) {
            if(A[pA] <= B[pB]) {
                if(shift != 0) {    // need shift
                    shift(A, pA, shift, m);
                    copy(A, pA, B, sB, shift);
                    m += shift;
                    pA += shift;
                    shift = 0;
                }
                else {
                    pA++;
                }
            }
            else {
                if(shift == 0) {    // record the start of B
                    sB = pB;
                }
                pB++;
                shift++;
            }
        }
        if(shift != 0) {    // shift rest
            shift(A, pA, shift, m);
            copy(A, pA, B, sB, shift);
            m += shift;
            pA += shift;
            shift = 0;
        }
        while(pB < n) {
            A[pA++] = B[pB++];
        }
    }

    // time complexity : O(M+N)
    // space complexity : O(1)
    public void merge2(int A[], int m, int B[], int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(m == 0) {
            for(int i = 0; i < n; i++) {
                A[i] = B[i];
            }
        }
        int pA = m - 1;
        int pB = n - 1;
        int index = m + n - 1;
        while(pA >= 0 && pB >= 0) {
            if(A[pA] >= B[pB]) {
                A[index--] = A[pA--];
            }
            else {
                A[index--] = B[pB--];
            }
        }
        while(pB >= 0) {
            A[index--] = B[pB--];
        }
    }
    /*
        Second Round
    */
    public void merge3(int A[], int m, int B[], int n) {
        // from end to start
        int pa = m - 1, pb = n - 1, end = m + n - 1;
        while (pa >= 0 && pb >= 0) {
            if(A[pa] > B[pb]) {
                A[end--] = A[pa--];
            }
            else {
                A[end--] = B[pb--];
            }
        }
        while(pb >= 0) {
            A[end--] = B[pb--];
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 0, 0, 0, 0};
        int[] B = {2, 0, 0, 0};
        solution.merge3(A, 1, B, 1);
        System.out.println(Arrays.toString(A));
    }
}



