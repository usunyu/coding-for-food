import java.util.*;

class Solution {
    private void subsetsWithDup(int[] num, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        ArrayList<Integer> copy = new ArrayList<Integer>(path);
        copy.add(num[index]);
        result.add(copy);
        for(int i = index + 1; i < num.length; i++) {
            if(i != index + 1 && num[i] == num[i - 1]) continue;
            subsetsWithDup(num, i, copy, result);
        }
    }
    
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        result.add(path);
        Arrays.sort(num);
        for(int i = 0; i < num.length; i++) {
            if(i > 0 && num[i] == num[i - 1]) continue;
            subsetsWithDup(num, i, path, result);
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {1,2,2};
        System.out.println(solution.subsetsWithDup(num));
    }
}
