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

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] matrix = {{1}};
		System.out.println(solution.searchMatrix(matrix, 0));
	}
}