/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
*/

import LCLibrary.*;
import java.util.*;

class Solution {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(matrix.length == 0 || matrix[0].length == 0) return result;
        int num = Math.max(matrix.length, matrix[0].length);
        int length = matrix[0].length - 1, height = matrix.length - 1;
        for(int i = 0; i <= num / 2; i++) {
            int count = result.size();
            // top
            for(int j = i; j <= length - i; j++) result.add(matrix[i][j]);
            if(result.size() - count == 0) break;
            else count = result.size();
            // right
            for(int j = i + 1; j <= height - i; j++) result.add(matrix[j][length - i]);
            if(result.size() - count == 0) break;
            else count = result.size();
            // down
            for(int j = length - i - 1; j >= i; j--) result.add(matrix[height - i][j]);
            if(result.size() - count == 0) break;
            else count = result.size();
            // left
            for(int j = height - i - 1; j > i; j--) result.add(matrix[j][i]);
            if(result.size() - count == 0) break;
            else count = result.size();
        }
        return result;
    }
}

/*
    Second Round
*/
class Solution2 {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
        int height = matrix.length, width = matrix[0].length;
        int length = Math.min(height, width);
        if(length % 2 == 1) length++;
        for(int i = 0; i < length / 2; i++) {
            // top side
            for(int j = i; j < width - i - 1; j++)
                result.add(matrix[i][j]);
            // right side
            for(int j = i; j < height - i - 1; j++)
                result.add(matrix[j][width - i - 1]);
            // bottom side
            for(int j = i; j < width - i - 1; j++) {
                result.add(matrix[height - i - 1][width - j - 1]);
                if(height - i - 1 == i) break;
            }
            // left side
            for(int j = i; j < height - i - 1; j++) {
                result.add(matrix[height - j - 1][i]);
                if(width - i - 1 == i) break;
            }
        }
        if(width == height && width % 2 == 1)
            result.add(matrix[height / 2][width / 2]);
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[][] matrix = Input.buildMatrix2(3, 3);
        Output.printMatrix(matrix);
        System.out.println();
        System.out.println(solution.spiralOrder(matrix));
    }
}
