import java.util.*;

class Solution {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>((int)Math.pow(2, n));
        result.add(0);
        for(int i = 0; i < n; i++) {
            ArrayList<Integer> sub = new ArrayList<Integer>(result.size());
            int bit = 1 << i;
            for(int j = 0; j < result.size(); j++) {
                int val = result.get(j) | bit;
                sub.add(val);
            }
            Collections.reverse(sub);
            result.addAll(sub);
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.grayCode(3).toString());
    }
}