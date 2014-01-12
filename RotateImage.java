class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++) {
            for(int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                // top left
                matrix[i][j] = matrix[n - j - 1][i];
                // left down
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                // down right
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                // right top
                matrix[j][n - i - 1] = temp;
            }
        }
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
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        print(matrix);
        solution.rotate(matrix);
        System.out.println();
        print(matrix);
    }
}
