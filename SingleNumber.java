class Solution {
    public int singleNumber(int[] A) {
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