/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
*/

import LCLibrary.*;

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        if(n == 0) return false;
        // search in the first column
        int start = 0, end = m - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(matrix[mid][0] == target) return true;
            else if(matrix[mid][0] > target) end = mid - 1;
            else start = mid + 1;
        }
        // search in the corresponding row
        int row = end;
        if(row < 0) return false;
        start = 0; end = n - 1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(matrix[row][mid] == target) return true;
            else if(matrix[row][mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return false;
    }
}

/*
    Second Round
*/
class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int M = matrix.length;
        if(M == 0) return false;
        int N = matrix[0].length;
        if(N == 0) return false;
        // binary search first column
        int up = 0, down = M - 1;
        while(up <= down) {
            int mid = up + (down - up) / 2;
            if(matrix[mid][0] == target) return true;
            else if(matrix[mid][0] > target) down = mid - 1;
            else up = mid + 1;
        }
        if(down < 0) return false;
        // binary search that row
        int left = 0, right = N - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(matrix[down][mid] == target) return true;
            else if(matrix[down][mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
}

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] matrix = Input.buildMatrix(5);
		System.out.println(solution.searchMatrix(matrix, 10));
	}
}