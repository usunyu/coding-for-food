/*
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    private void subsetsWithDup(int[] num, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        ArrayList<Integer> copy = new ArrayList<Integer>(path);
        copy.add(num[index]);
        result.add(copy);
        for(int i = index + 1; i < num.length; i++) {
            if(i != index + 1 && num[i] == num[i - 1]) continue;
            subsetsWithDup(num, i, copy, result);
        }
    }
    
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        result.add(path);
        Arrays.sort(num);
        for(int i = 0; i < num.length; i++) {
            if(i > 0 && num[i] == num[i - 1]) continue;
            subsetsWithDup(num, i, path, result);
        }
        return result;
    }
}
/*
    Second Round
*/
class Solution2 {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num == null || num.length == 0) return result;
        ArrayList<Integer> first = new ArrayList<Integer>();
        result.add(first);
        Arrays.sort(num);
        for(int i = 0; i < num.length; i++) {
            int size = result.size();
            int count = 1;
            // process duolicate
            while(i + 1 < num.length && num[i + 1] == num[i]) {
                i++;
                count++;
            }
            for(int j = 0; j < size; j++) {
                ArrayList<Integer> list = new ArrayList<Integer>(result.get(j));
                for(int k = 0; k < count; k++) {
                    list.add(num[i]);
                    ArrayList<Integer> copy = new ArrayList<Integer>(list);
                    result.add(copy);
                }
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] num = {5,5,5,5,5};
        Output.printLevelLists(solution.subsetsWithDup(num));
    }
}
