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
        int[] candidates = {2,3,6,7};
        ArrayList<ArrayList<Integer>> combinations = solution.combinationSum(candidates, 7);
        print(combinations);
    }
}
