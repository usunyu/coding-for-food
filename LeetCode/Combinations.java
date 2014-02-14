import java.util.*;

class Solution {
    public void combine(int[] space, int start, int k, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> sub) {
        if(start + k >= space.length) {
            return;
        }
        if(k == 0) {
            result.add(sub);
        }
        else {
            for(int i = start + 1; i < space.length; i++) {
                ArrayList<Integer> newSub = new ArrayList<Integer>(sub);
                newSub.add(space[i]);
                combine(space, i, k - 1, result, newSub);
            }
        }
    }
    
    // time complexity : O(MN^2)
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int[] space = new int[n];
        for(int i = 0; i < n; i++) {
            space[i] = i + 1;
        }
        for(int i = 0; i < n; i++) {
            ArrayList<Integer> sub = new ArrayList<Integer>();
            sub.add(space[i]);
            combine(space, i, k - 1, result, sub);
        }
        return result;
    }

    // iterative implementation
    public ArrayList<ArrayList<Integer>> combine2(int n, int k) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        for(; k > 0; k--) {
            int num = results.size();
            // initial
            if(num == 0) {
                for(int i = 1; i <= n - k + 1; i++) {
                    ArrayList<Integer> result = new ArrayList<Integer>();
                    result.add(i);
                    results.add(result);
                }
            }
            for(int i = 0; i < num; i++) {
                ArrayList<Integer> result = results.get(i);
                int last = result.get(result.size() - 1);
                for(last += 1; last < n - k + 1; last++) {
                    ArrayList<Integer> temp = new ArrayList<Integer>(result);
                    temp.add(last);
                    results.add(temp);
                }
                result.add(last);
            }
        }
        return results;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> lists) {
        for(ArrayList<Integer> list : lists) {
            for(int n : list) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> result = solution.combine2(4, 2);
        print(result);
    }
}