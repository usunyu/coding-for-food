import java.util.ArrayList;
import java.util.HashMap;

class Solution {
	private String getStrId(int row, int col) {
		StringBuilder b = new StringBuilder();
		b.append(row);
		b.append(",");
		b.append(col);
		return b.toString();
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
    
    // space complexity : O(n^2)
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        return minSubTotal(triangle, 0, 0, new HashMap<String, Integer>());
    }

    private int minSubTotal2(ArrayList<ArrayList<Integer>> triangle, int row, int col, HashMap<Integer, Integer> cache) {
    	int ret;
        if(row == triangle.size() - 1) {	// if reach bottom
        	ret = triangle.get(row).get(col);
        }
        else {	// pick the min
        	if(cache.containsKey(row + 1)) {
				ret = Math.min(cache.get(row + 1), minSubTotal2(triangle, row + 1, col + 1, cache)) + triangle.get(row).get(col);
        	}
        	else {
        		ret = Math.min(minSubTotal2(triangle, row + 1, col, cache), minSubTotal2(triangle, row + 1, col + 1, cache)) + triangle.get(row).get(col);
        	}
        }
        cache.put(row, ret);
        return ret;
    }

    // space complexity : O(n)
    public int minimumTotal2(ArrayList<ArrayList<Integer>> triangle) {
        return minSubTotal2(triangle, 0, 0, new HashMap<Integer, Integer>());
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
        System.out.println(solution.minimumTotal2(triangle));
    }
}
