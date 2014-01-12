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

class Main {
    public static void print(ArrayList<Integer> spiral) {
        for(int i : spiral) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
            {1, 6},
            {2, 7},
            {3, 8},
            {4, 9},
            {5, 10}
        };
        print(solution.spiralOrder(matrix));
    }
}
