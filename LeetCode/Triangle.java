/*
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, 
where n is the total number of rows in the triangle.
*/

import java.util.ArrayList;
import java.util.HashMap;
import LCLibrary.*;

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
}

class Solution2 {
    private int minSubTotal(ArrayList<ArrayList<Integer>> triangle, int row, int col, HashMap<Integer, Integer> cache) {
    	int ret;
        if(row == triangle.size() - 1) {	// if reach bottom
        	ret = triangle.get(row).get(col);
        }
        else {	// pick the min
        	if(cache.containsKey(row + 1)) {
				ret = Math.min(cache.get(row + 1), minSubTotal(triangle, row + 1, col + 1, cache)) + triangle.get(row).get(col);
        	}
        	else {
        		ret = Math.min(minSubTotal(triangle, row + 1, col, cache), minSubTotal(triangle, row + 1, col + 1, cache)) + triangle.get(row).get(col);
        	}
        }
        cache.put(row, ret);
        return ret;
    }

    // space complexity : O(n)
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        return minSubTotal(triangle, 0, 0, new HashMap<Integer, Integer>());
    }
}
/*
    Second Round
*/
class Solution3 {
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();
        int[] space = new int[n];
        for(ArrayList<Integer> level : triangle) {
            for(int i = level.size() - 1; i >= 0; i--) {
                if(i == 0)  // first one
                    space[i] += level.get(i);
                else if(i == level.size() - 1)  // last one
                    space[i] += space[i - 1] + level.get(i);
                else    // middle one
                    space[i] = Math.min(space[i], space[i - 1]) + level.get(i);
                    
            }
        }
        // get minimum one
        int min = space[0];
        for(int i : space) min = Math.min(min, i);
        return min;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        ArrayList<ArrayList<Integer>> triangle = Input.buildTriangle();
        System.out.println(solution.minimumTotal(triangle));
    }
}
