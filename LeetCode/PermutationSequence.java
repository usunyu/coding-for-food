/*
The set [1,2,3,…,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order,
We get the following sequence (ie, for n = 3):

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note: Given n will be between 1 and 9 inclusive.
*/

import java.util.*;

class Solution {
    private void shift(StringBuilder builder, int to, int from) {
        char tmp = builder.charAt(from);
        builder.deleteCharAt(from);
        builder.insert(to, tmp);
    }
    
    private void getPermutation(StringBuilder builder, int index, ArrayList<String> strList, int k) {
        if(strList.size() == k) return;
        if(index == builder.length() - 1) {
            strList.add(builder.toString());
            return;
        }
        for(int i = index; i < builder.length(); i++) {
            StringBuilder sb = new StringBuilder(builder);
            shift(sb, index, i);
            getPermutation(sb, index + 1, strList, k);
        }
    }
    
    // Time Limit Exceeded
    public String getPermutation(int n, int k) {
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i <= n; i++) builder.append(i);
        ArrayList<String> strList = new ArrayList<String>();
        getPermutation(builder, 0, strList, k);
        return strList.get(strList.size() - 1);
    }
}

class Solution2 {
    public String getPermutation(int n, int k) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int[] facts = new int[n];
        for(int i = 1; i <= n; i++) {
            nums.add(i);
            if(i == 1 || i == 2) facts[i - 1] = 1;
            else facts[i - 1] = facts[i - 2] * (i - 1);
        }
        StringBuilder builder = new StringBuilder();
        for(int i = n; i >= 1; i--) {
            int index = (k - 1) / facts[i - 1];
            builder.append(nums.get(index));
            nums.remove(index);
            k -= index * facts[i - 1];
        }
        return builder.toString();
    }
}

/*
    Second Round
*/
class Solution3 {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int factorial = 1;
        for(int i = 1; i <= n; i++) {
            factorial *= i;
            nums.add(i);
        }
        for(int i = n; i >= 1; i--) {
            factorial /= i;
            int index = (k - 1) / factorial;
            int num = nums.get(index);
            sb.append(num);
            nums.remove(index);
            k = (k - 1) % factorial + 1;
        }
        return sb.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        System.out.println(solution.getPermutation(9, 278082));
    }
}

