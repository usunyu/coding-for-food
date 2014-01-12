import java.util.*;

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

class Main {
    public static void print(ArrayList<ArrayList<Integer>> lists) {
        for(ArrayList<Integer> list : lists) {
            for(int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {-1,-1,3,-1};
        ArrayList<ArrayList<Integer>> result = solution.permuteUnique(num);
        print(result);
    }
}

