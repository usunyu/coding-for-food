/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public void shift(int[] num, int start) {
        int temp = num[start];
        for(int i = start; i < num.length - 1; i++) {
            num[i] = num[i + 1];
        }
        num[num.length - 1] = temp;
    }

    public ArrayList<Integer> copy(int[] num) {
        ArrayList<Integer> result = new ArrayList<Integer>(num.length);
        for(int i = 0; i < num.length; i++) {
            result.add(num[i]);
        }
        return result;
    }
    
    public void permute(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
        if(num == null) {
            return;
        }
        if(start == num.length - 1) {
            ArrayList<Integer> sub = copy(num);
            result.add(sub);
            return;
        }
        for(int i = start; i < num.length; i++) {
            int[] temp = num.clone();
            permute(temp, start + 1, result);
            shift(num, start);
        }
    }
    
    // time complexity : O(N!)
    // space complexity : O(N!)
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        permute(num, 0, result);
        return result;
    }
}

class Solution2 {
    public ArrayList<ArrayList<Integer>> insert(ArrayList<ArrayList<Integer>> lists, int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(); 
        for(ArrayList<Integer> list : lists) {
            for(int i = 0; i < list.size() + 1; i++) {
                ArrayList<Integer> temp = new ArrayList<Integer>(list);
                temp.add(i, n);
                result.add(temp);
            }
        }
        return result;
    }

    // time complexity : O(N!)
    // space complexity : O(N!)
    public ArrayList<ArrayList<Integer>> permute2(int[] num) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num.length > 0) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(num[0]);
            result.add(list);
            for(int i = 1; i < num.length; i++) {
                result = insert(result, num[i]);
            }
        }
        return result;
    }
}

/*
    Second Round
*/
class Solution3 {
    private void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    
    private void permute(int[] num, int index, ArrayList<ArrayList<Integer>> permutations) {
        if(index == num.length - 1) {
            ArrayList<Integer> p = new ArrayList<Integer>();
            for(int i = 0; i < num.length; i++) p.add(num[i]);
            permutations.add(p);
            return;
        }
        for(int i = index; i < num.length; i++) {
            swap(num, i, index);
            permute(num, index + 1, permutations);
            swap(num, i, index); // backtracking
        }
    }
    
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
        permute(num, 0, permutations);
        return permutations;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] num = {1, 2, 3};
        Output.printLevelLists(solution.permute(num));
    }
}
