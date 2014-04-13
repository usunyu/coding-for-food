/*
Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

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

class Solution2 {
    public int[][] generateMatrix(int n) {
        if (n < 0) return null;
        if (n==0)   return new int[0][0];
        int left = 0, right=n-1, top=0, bottom=n-1;
        int num = 1;
        int[][] matrix = new int[n][n];
        while (left <= right && top <= bottom){
            for (int i=left; i<=right; i++)  matrix[top][i] = num++;
            if (++top > bottom) break;
            for (int i=top; i<=bottom; i++)  matrix[i][right] = num++;
            if (--right < left) break;
            for (int i=right; i>=left; i--)  matrix[bottom][i] = num++;
            if (--bottom < top) break;
            for (int i=bottom; i>=top; i--)  matrix[i][left] = num++;
            if (++left > right) break;
        }
        return matrix;
    }
}

/*
    Second Round
*/
public class Solution3 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int val = 1;
        for(int i = 0; i < n / 2; i++) {
            for(int j = i; j < n - i - 1; j++) matrix[i][j] = val++;                    // top side
            for(int j = i; j < n - i - 1; j++) matrix[j][n - i - 1] = val++;            // right side
            for(int j = i; j < n - i - 1; j++) matrix[n - i - 1][n - j - 1] = val++;    // bottom side
            for(int j = i; j < n - i - 1; j++) matrix[n - j - 1][i] = val++;            // left side
        }
        if(n % 2 == 1) matrix[n / 2][n / 2] = val;
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

