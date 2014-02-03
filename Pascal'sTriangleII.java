import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> getRow(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for(int j = 0; j <= i; j++) {
                if(j == 0 || j == i) temp.add(1);
                else temp.add(result.get(j - 1) + result.get(j));
            }
            result = temp;
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getRow(5));
    }
}
