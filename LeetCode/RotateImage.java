/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

import java.util.Arrays;
import LCLibrary.*;

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

/*
    Second Round
*/
class Solution2 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n / 2; i++) {
            for(int j = i; j < n - i - 1; j++) {
                int tmp = matrix[i][j];
                // left -> top
                matrix[i][j] = matrix[n - j - 1][i];
                // down -> left
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                // right -> down
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                // top -> right
                matrix[j][n - i - 1] = tmp;
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = Input.buildMatrix(5);
        Output.printMatrix(matrix);
        solution.rotate(matrix);
        System.out.println();
        Output.printMatrix(matrix);
    }
}
