class Solution {
    // referecne: http://discuss.leetcode.com/questions/260/maximal-rectangle
    // H(i, j): the length of "hanging line" at (i, j)
    // L(i, j): the leftmost of point at (i, j)
    // R(i, j): the rightmost of point at (i, j)
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, result = 0;
        int[] H = new int[n];
        int[] L = new int[n];
        int[] R = new int[n];
        for(int i = 0; i < n; i++) R[i] = n;
        for(int i = 0; i < m; i++) {
            int left = 0, right = n;
            // calculate L(i, j) from left to right
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    H[j]++;
                    L[j] = Math.max(L[j], left);
                }
                else {
                    left = j + 1;
                    H[j] = 0; L[j] = 0; R[j] = n;
                }
            }
            // calculate R(i, j) from right to right
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] == '1') {
                    R[j] = Math.min(R[j], right);
                    result = Math.max(result, (R[j] - L[j]) * H[j]);
                }
                else {
                    right = j;
                }
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix = {
            {'0','1','1','1','0'},
            {'0','1','1','1','1'},
            {'0','0','1','0','0'}
        };
        System.out.println(solution.maximalRectangle(matrix));
    }
}
