class Solution {
    public int searchInsert(int[] A, int target) {
        int left = 0, right = A.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(target == A[mid]) return mid;
            else if(target > A[mid]) left = mid + 1;
            else right = mid - 1;
        }
        // check valid index
        if(right < 0) right = 0;
        if(right > A.length - 1) right = A.length - 1;
        if(A[right] >= target) return right;
        else return right + 1;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(A, 7));
    }
}
