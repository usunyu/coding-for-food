import java.util.ArrayList;
import java.util.HashMap;

class Solution {
	private String getStrId(int row, int col) {
		return row + "," + col;
	}

    private int minSubTotal(ArrayList<ArrayList<Integer>> triangle, int row, int col, HashMap<String, Integer> cache) {
    	String strId = getStrId(row, col);
    	if(cache.containsKey(strId)) return cache.get(strId);
    	int ret;
        if(row == triangle.size() - 1) {	// if reach bottom
        	ret = triangle.get(row).get(col);
        }
        else {	// pick the min
        	ret = Math.min(minSubTotal(triangle, row + 1, col, cache), minSubTotal(triangle, row + 1, col + 1, cache)) + triangle.get(row).get(col);
        }
        cache.put(strId, ret);
        return ret;
    }
    
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        return minSubTotal(triangle, 0, 0, new HashMap<String, Integer>());
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row1 = new ArrayList<Integer>(); row1.add(2);
        ArrayList<Integer> row2 = new ArrayList<Integer>(); row2.add(3); row2.add(4);
        ArrayList<Integer> row3 = new ArrayList<Integer>(); row3.add(6); row3.add(5); row3.add(7);
        ArrayList<Integer> row4 = new ArrayList<Integer>(); row4.add(4); row4.add(1); row4.add(8); row4.add(3);
        triangle.add(row1); triangle.add(row2); triangle.add(row3); triangle.add(row4);
        System.out.println(solution.minimumTotal(triangle));
    }
}
