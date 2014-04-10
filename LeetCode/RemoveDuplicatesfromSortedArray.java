/*
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
*/

import java.util.Arrays;

class Solution {
    public int removeDuplicates(int[] A) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int fast = 0, slow = 0;
        while(fast < A.length) {
            int prev = A[fast];
            while(fast < A.length && A[fast] == prev) {
                fast++;
            }
            A[slow++] = prev;
        }
        return slow;
    }
}

/*
    Second Round
*/
class Solution2 {
    public int removeDuplicates(int[] A) {
        int prev = -1;
        for(int i = 0; i < A.length; i++) {
            if(prev == -1 || A[i] != A[prev]) {
                A[++prev] = A[i];
            }
        }
        return prev + 1;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] A = {1, 1, 2};
        System.out.println(solution.removeDuplicates(A));
        System.out.println(Arrays.toString(A));
    }
}