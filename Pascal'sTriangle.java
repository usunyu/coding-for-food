import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < numRows; i++) {
            ArrayList<Integer> sub = new ArrayList<Integer>();
            ArrayList<Integer> prev = result.isEmpty() ? null : result.get(result.size() - 1);
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) sub.add(1);
                else sub.add(prev.get(j - 1) + prev.get(j));
            }
            result.add(sub);
        }
        return result;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> result) {
        for(ArrayList<Integer> list : result)
            System.out.println(list);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.generate(5));
    }
}
