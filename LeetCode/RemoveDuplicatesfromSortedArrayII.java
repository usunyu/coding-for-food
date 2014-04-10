/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
*/

import java.util.Arrays;

class Solution {
    public int removeDuplicates(int[] A) {
        int fast = 0, slow = 0;
        while(fast < A.length) {
            int prev = A[fast], count = 0;
            while(fast < A.length && A[fast] == prev && count < 2) {
                count++; A[slow++] = A[fast++];
            }
            while(fast < A.length && A[fast] == prev) {
                fast++;
            }
        }
        return slow;
    }
}
/*
    Second Round
*/
class Solution2 {
    public int removeDuplicates(int[] A) {
        int prev = -1, count = 1;
        for(int i = 0; i < A.length; i++) {
            if(prev > -1 && A[prev] != A[i]) count = 1;
            if(prev > -1 && A[prev] == A[i]) count++;
            if(prev == -1 || A[prev] != A[i] || count <= 2) {
                A[++prev] = A[i];
            }
        }
        return prev + 1;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] A = {1,1,1,2,2,3};
        System.out.println(solution.removeDuplicates(A));
        System.out.println(Arrays.toString(A));
    }
}
