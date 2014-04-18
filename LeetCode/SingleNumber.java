/*
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/

class Solution {
    public int singleNumber(int[] A) {
        int result = 0;
        for(int i = 0; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }
}

/*
    Second Round
*/
class Solution2 {
    public int singleNumber(int[] A) {
        if(A == null || A.length == 0) return 0;
        int result = 0;
        for(int i = 0; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 3, 1, 5, 3, 5, 1};
        System.out.println(solution.singleNumber(nums));
    }
}