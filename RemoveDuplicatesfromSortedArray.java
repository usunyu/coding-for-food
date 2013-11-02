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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 1, 2};
        int size = solution.removeDuplicates(A);
        for(int i = 0; i < size; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }
}