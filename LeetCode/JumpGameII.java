/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
*/

class Solution {
    public int jump(int[] A) {
        if(A.length <= 1) return 0;
        int next = A.length - 1;
        int min = 0;
        while(next > 0) {
            int current = 0;
            for(int i = next - 1; i >= 0; i--) {
                if(A[i] >= (next - i)) {
                    current = i;
                }
            }
            next = current;
            min++;
        }
        return min;
    }
    /*
        Second Round
    */
    public int jump2(int[] A) {
        int count = 0;
        int next = A.length - 1;
        while(next > 0) {
            // search earliest place which can jump to current place
            int place = -1;
            for(int j = next - 1; j >= 0; j--) {
                if(j + A[j] >= next) place = j;
            }
            if(place == -1) return -1;  // cannot jump
            next = place;
            count++;
        }
        return count;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {2,3,1,1,4};
        System.out.println(solution.jump2(A));
    }
}
