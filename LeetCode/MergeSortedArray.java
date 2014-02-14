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
}

class Main {
    public static void print(int[] A) {
        for(int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 0, 0, 0, 0};
        int[] B = {2, 0, 0, 0};
        solution.merge2(A, 1, B, 1);
        print(A);
    }
}



