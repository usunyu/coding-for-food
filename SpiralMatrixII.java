class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int m = (n % 2 == 0 ? n / 2 : n / 2 + 1);
        int num = 1;
        for(int i = 0; i < m; i++) {
            // top
            for(int j = i; j < n - i; j++) matrix[i][j] = num++;
            // right
            for(int j = i + 1; j < n - i; j++) matrix[j][n - i - 1] = num++;
            // down
            for(int j = n - i - 2; j >= i; j--) matrix[n - i - 1][j] = num++;
            // left
            for(int j = n - i - 2; j > i; j--) matrix[j][i] = num++;
        }
        return matrix;
    }
}

class Main {
    public static void print(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.generateMatrix(4));
    }
}

