/*
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

import java.util.ArrayList;
import LCLibrary.*;

class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < numRows; i++) {
            ArrayList<Integer> sub = new ArrayList<Integer>();
            ArrayList<Integer> prev = result.isEmpty() ? null : result.get(result.size() - 1);
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) sub.add(1);
                else sub.add(prev.get(j - 1) + prev.get(j));
            }
            result.add(sub);
        }
        return result;
    }
}
/*
    Second Round
*/
class Solution2 {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
        if(numRows <= 0) return triangle;
        for(int level = 0; level < numRows; level++) {
            ArrayList<Integer> current = new ArrayList<Integer>();
            for(int col = 0; col <= level; col++) {
                if(col == 0 || level == col) current.add(1);
                else {
                    ArrayList<Integer> previous = triangle.get(level - 1);
                    current.add(previous.get(col - 1) + previous.get(col));
                }
            }
            triangle.add(current);
        }
        return triangle;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Output.printLevelLists(solution.generate(5));
    }
}
