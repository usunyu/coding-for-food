/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result.add(new ArrayList<Integer>());
        for(int n : num) {
            ArrayList<ArrayList<Integer>> current = new ArrayList<ArrayList<Integer>>();
            HashSet<String> uniqueSet = new HashSet<String>();
            for(ArrayList<Integer> list : result) {
                for(int i = 0; i < list.size() + 1; i++) {
                    ArrayList<Integer> temp = new ArrayList<Integer>(list);
                    temp.add(i, n);
                    String key = temp.toString();
                    if(!uniqueSet.contains(key)) {
                        current.add(temp);
                        uniqueSet.add(key);
                    }
                }
            }
            result = current;
        }
        return result;
    }
}

class Solution2 {
    private void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    
    private void permuteUnique(int[] num, int index, ArrayList<ArrayList<Integer>> permutations) {
        if(index == num.length - 1) {
            ArrayList<Integer> p = new ArrayList<Integer>();
            for(int i = 0; i < num.length; i++) p.add(num[i]);
            permutations.add(p);
            return;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i = index; i < num.length; i++) {
            if(set.contains(num[i])) continue; // skip duplicate
            else {
                swap(num, index, i);
                permuteUnique(num, index + 1, permutations);
                swap(num, index, i);    // backtracking
                set.add(num[i]);
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        if(num == null || num.length == 0) return null;
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
        permuteUnique(num, 0, permutations);
        return permutations;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] num = {0,1,0,0,9};
        Output.printLevelLists(solution.permuteUnique(num));
    }
}

