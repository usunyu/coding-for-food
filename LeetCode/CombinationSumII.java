/*
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 
*/

import java.util.*;

class Solution {
    private void combinationSum2(int[] num, int index, int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> current) {
        int prev = -1;
        for(int i = index; i < num.length; i++) {
            if(prev == num[i]) continue;    // get rid of repeat
            else prev = num[i];
            if(num[i] > target) {
                break;
            }
            else if(num[i] == target) {
                ArrayList<Integer> sub = new ArrayList<Integer>(current);
                sub.add(num[i]);
                result.add(sub);
                break;
            }
            else {
                ArrayList<Integer> sub = new ArrayList<Integer>(current);
                sub.add(num[i]);
                combinationSum2(num, i + 1, target - num[i], result, sub);
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(num);
        combinationSum2(num, 0, target, result, new ArrayList<Integer>());
        return result;
    }

    /*
        Second Round
    */
    private void combinationSum(int[] num, int target, ArrayList<Integer> path, int index, ArrayList<ArrayList<Integer>> result) {
        if(target == 0) {
            ArrayList<Integer> p = new ArrayList<Integer>(path);
            result.add(p);
        }
        else if(target >= 0 && index < num.length) {
            // use current index
            int nextIndex = index + 1;
            path.add(num[index]);
            combinationSum(num, target - num[index], path, nextIndex, result);
            path.remove(path.size() - 1);
            // skip repeated element
            while(nextIndex < num.length && num[nextIndex] == num[nextIndex - 1]) nextIndex++;
            // use next index directly
            combinationSum(num, target, path, nextIndex, result);
        }
    }
    
    public ArrayList<ArrayList<Integer>> combinationSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num.length == 0) return result;
        Arrays.sort(num);
        combinationSum(num, target, new ArrayList<Integer>(), 0, result);
        return result;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> combinations) {
        for(ArrayList<Integer> combination : combinations) {
            for(int i : combination) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {10,1,2,2,7,6,1,5};
        ArrayList<ArrayList<Integer>> combinations = solution.combinationSum(candidates, 8);
        print(combinations);
    }
}