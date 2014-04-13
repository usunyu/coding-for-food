/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

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
}

class Solution2 {
    // iterative implementation
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
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
/*
    Second Round
*/
class Solution3 {
    private void combine(int n, int k, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if(k == 0) {
            result.add(path);
        }
        else {
            for(int i = index; i <= n; i++) {
                ArrayList<Integer> p = new ArrayList<Integer>(path);
                p.add(i);
                combine(n, k - 1, i + 1, p, result);
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(n <= 0 || k > n) return result;
        combine(n, k, 1, new ArrayList<Integer>(), result);
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.combine(4, 2));
    }
}