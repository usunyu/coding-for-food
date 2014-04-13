/*
Given a sorted array and a target value, return the index if the target is found. 
If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

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

/*
    Second Round
*/
class Solution2 {
    private int binarySearch(int[] A, int target) {
        int left = 0, right = A.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(A[mid] > target) right = mid - 1;
            else if(A[mid] < target) left = mid + 1;
            else return mid;
        }
        return left;
    }

    public int searchInsert(int[] A, int target) {
        return binarySearch(A, target);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {1, 3, 5, 6};
        System.out.println(solution.searchInsert(A, 7));
    }
}
