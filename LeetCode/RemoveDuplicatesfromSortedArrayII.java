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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1,1,1,2,2,3};
        int size = solution.removeDuplicates(A);
        for(int i = 0; i < size; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }
}
