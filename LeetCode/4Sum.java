/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
Find all unique quadruplets in the array which gives the sum of target.

Note:
    Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
    The solution set must not contain duplicate quadruplets.

    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
*/

import java.util.*;

class Solution {
    // Time Limit Exceeded
    // Time Complexity: O(N^4)
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if(num.length < 4) return results;
        Arrays.sort(num);
        for(int i1 = 0; i1 <= num.length - 4; i1++) {
            for(int i2 = i1 + 1; i2 <= num.length - 3; i2++) {
                for(int i3 = i2 + 1; i3 <= num.length - 2; i3++) {
                    for(int i4 = i3 + 1; i4 <= num.length - 1; i4++) {
                        if(num[i1] + num[i2] + num[i3] + num[i4] == target) {
                            ArrayList<Integer> quadruplets = new ArrayList<Integer>();
                            quadruplets.add(num[i1]);
                            quadruplets.add(num[i2]);
                            quadruplets.add(num[i3]);
                            quadruplets.add(num[i4]);
                            results.add(quadruplets);
                        }
                    }
                }
            }
        }
        return results;
    }

    // Time Complexity: O(N^3)
    public ArrayList<ArrayList<Integer>> fourSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if(num.length < 4) return results;
        Arrays.sort(num);
        HashSet<String> check = new HashSet<String>();
        for(int i1 = 0; i1 <= num.length - 4; i1++) {
            for(int i2 = i1 + 1; i2 <= num.length - 3; i2++) {
                int left = i2 + 1, right = num.length - 1;
                int need = target - num[i1] - num[i2];
                while(left < right) {
                    if(num[left] + num[right] == need) {
                        ArrayList<Integer> quadruplets = new ArrayList<Integer>();
                        quadruplets.add(num[i1]);
                        quadruplets.add(num[i2]);
                        quadruplets.add(num[left]);
                        quadruplets.add(num[right]);
                        String str = quadruplets.toString();
                        if(!check.contains(str)) {
                            results.add(quadruplets);
                            check.add(str);
                        }
                        left++;
                        right--;
                    }
                    else if(num[left] + num[right] > need) {
                        right--;
                    }
                    else {
                        left++;
                    }
                }
            }
        }
        return results;
    }

    /*
        Second Round
    */
    public ArrayList<ArrayList<Integer>> fourSum3(int[] num, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(num.length < 4) return result;
        Arrays.sort(num);
        for(int i = 0; i < num.length - 3; i++) {
            // skip duplicate
            if(i > 0 && num[i - 1] == num[i]) continue;
            for(int j = i + 1; j < num.length - 2; j++) {
                // skip duplicate
                if(j > i + 1 && num[j - 1] == num[j]) continue;
                int start = j + 1, end = num.length - 1;
                int part = num[i] + num[j];
                while(start < end) {
                    int sum = part + num[start] + num[end];
                    if(sum < target) {
                        start++;
                    }
                    else if(sum > target) {
                        end--;
                    }
                    else {
                        ArrayList<Integer> subResult = new ArrayList<Integer>();
                        subResult.add(num[i]); subResult.add(num[j]); subResult.add(num[start]); subResult.add(num[end]);
                        result.add(subResult);
                        // skip duplicate
                        do { start++; } while(start < end && num[start - 1] == num[start]);
                    }
                }
            }
        }
        return result;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> results) {
        for(ArrayList<Integer> quadruplets : results) {
            for(int n : quadruplets) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {1, 0, -1, 0, -2, 2};
        print(solution.fourSum3(array, 0));
    }
}
