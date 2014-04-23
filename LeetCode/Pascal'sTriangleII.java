/*
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
*/

import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> getRow(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) temp.add(1);
                else temp.add(result.get(j - 1) + result.get(j));
            }
            result = temp;
        }
        return result;
    }
}
/*
    Second Round
*/
class Solution2 {
    public ArrayList<Integer> getRow(int rowIndex) {
        ArrayList<Integer> level = new ArrayList<Integer>();
        for(int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> current = new ArrayList<Integer>();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i)
                    current.add(1);
                else
                    current.add(level.get(j - 1) + level.get(j));
            }
            level = current;
        }
        return level;
    }
}

class Solution3 {
    public ArrayList<Integer> getRow(int rowIndex) {
        ArrayList<Integer> level = new ArrayList<Integer>();
        int[] arr = new int[rowIndex + 1];
        arr[0] = 1;
        for(int i = 1; i <= rowIndex; i++) {
            arr[i] = 1;
            for(int j = i - 1; j >= 1; j--) {
                arr[j] = arr[j - 1] + arr[j];
            }
        }
        for(int i : arr) level.add(i);
        return level;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.getRow(5));
    }
}
