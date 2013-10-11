import java.util.*;

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
        int[] num = {1, 2, 3};
        ArrayList<ArrayList<Integer>> result = solution.permute(num);
        print(result);
    }
}
