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
        ArrayList<ArrayList<Integer>> combinations = solution.combinationSum2(candidates, 8);
        print(combinations);
    }
}