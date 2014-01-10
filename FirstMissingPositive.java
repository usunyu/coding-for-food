import java.util.*;

class Solution {
    // sort solution
    // time complexity: O(NlogN)
    public int firstMissingPositive(int[] A) {
        if(A.length == 0) return 1;
        Arrays.sort(A);
        int i;
        for(i = 0; i < A.length - 1; i++) {
            if(A[i] > 0) break;
        }
        if(A[i] > 1) return 1;
        for(; i < A.length - 1; i++) {
            if(A[i + 1] - A[i] > 1) return A[i] + 1;
        }
        return A[A.length - 1] + 1;
    }

    // use index to indicate existed number
    // time complexity: O(N)
    public int firstMissingPositive2(int[] A) {
        if(A.length == 0) return 1;
        // find a positive number
        int pos = 0;
        for(int p : A) {
            if(p > 0) {
                pos = p;
                break;
            }
        }
        if(pos == 0) return 1;    // no positive number
        // change all negative number or zero to positive existed number
        int max = 0;
        for(int i = 0; i < A.length; i++) {
            if(A[i] <= 0) A[i] = pos;
            if(A[i] > max) max = A[i];
        }
        // change all index with number to negative, skip if exceed the array length
        for(int i = 0; i < A.length; i++) {
            if(Math.abs(A[i]) - 1 < A.length && A[Math.abs(A[i]) - 1] > 0) A[Math.abs(A[i]) - 1] = -A[Math.abs(A[i]) - 1];
        }
        // find missing positive
        for(int i = 0; i < A.length; i++) {
            if(A[i] > 0) return i + 1;
        }
        return max + 1;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {3,4,-1,1};
        System.out.println(solution.firstMissingPositive2(A));
    }
}
