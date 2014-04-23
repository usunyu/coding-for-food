/*
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3] 
*/

import java.util.*;

class Solution {
    public void combinationSum(int[] candidates, int target, int index, ArrayList<Integer> combination, ArrayList<ArrayList<Integer>> combinations) {
        if(target == 0) {
            combinations.add(combination);
            return;
        }
        if(index >= candidates.length || target < candidates[index]) {
            return;
        }
        combinationSum(candidates, target, index + 1, new ArrayList<Integer>(combination), combinations);
        while(target > 0) {
            target -= candidates[index];
            if(target >= 0) {
                combination.add(candidates[index]);
                ArrayList<Integer> localCombination = new ArrayList<Integer>(combination);
                combinationSum(candidates, target, index + 1, localCombination, combinations);
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<ArrayList<Integer>>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, new ArrayList<Integer>(), combinations);
        return combinations;
    }
}

/*
    Second Round
*/
class Solution2 {
    private void combinationSum(int[] candidates, int target, ArrayList<Integer> path, int index, ArrayList<ArrayList<Integer>> result) {
        if(target == 0) {   // we find a solution
            ArrayList<Integer> p = new ArrayList<Integer>(path);
            result.add(p);
        }
        else if(target > 0 && index < candidates.length) {
            int t = target;
            // ArrayList<Integer> p = new ArrayList<Integer>(path);
            int count = 0;
            while(t >= 0) {
                count++;
                t -= candidates[index];
                path.add(candidates[index]);
                combinationSum(candidates, t, path, index + 1, result);
            }
            // remove range
            path.subList(path.size() - count, path.size()).clear();; 
            combinationSum(candidates, target, path, index + 1, result);
        }
    }
    
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(candidates.length == 0) return result;
        Arrays.sort(candidates);
        combinationSum(candidates, target, new ArrayList<Integer>(), 0, result);
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2,3,6,7};
        System.out.println(solution.combinationSum(candidates, 7));
    }
}
