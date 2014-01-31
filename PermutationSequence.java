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

    public String getPermutation2(int n, int k) {
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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getPermutation2(9, 278082));
    }
}

