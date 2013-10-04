import java.util.*;

class Solution {
    
    // time complexity: O(N^2)
    // space complexity: O(N)
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        if(num.length < 3) {
        	return result;
        }
        // initial map
        for(int i = 0; i < num.length; i++) {
            if(numMap.containsKey(num[i])) {
            	int count = numMap.get(num[i]);
            	numMap.put(num[i], ++count);
            }
            else {
                numMap.put(num[i], 1);
            }
        }
        // search
        for(int i = 0; i < num.length; i++) {
            for(int j = i + 1; j < num.length; j++) {
                int sum = num[i] + num[j];
                int need = 0 - sum;
                if(numMap.containsKey(need)) {
                    int count = numMap.get(need);
                    if(need == num[i]) {
                        count--;
                    }
                    if(need == num[j]) {
                        count--;
                    }
                    if(count > 0) { // we find triple
                        ArrayList<Integer> subResult = new ArrayList<Integer>();
                        subResult.add(num[i]); subResult.add(num[j]); subResult.add(need);
                        Collections.sort(subResult);
                        if(!set.contains(subResult)) {
                            result.add(subResult);
                            set.add(subResult);
                        }
                    }
                }
            }
        }
        return result;
    }
}

class Main {
	public static void print(ArrayList<ArrayList<Integer>> result) {
		for(int i = 0; i < result.size(); i++) {
			ArrayList<Integer> subResult = result.get(i);
			System.out.print("(");
			for(int n : subResult) {
				System.out.print(n + " ");
			}
			System.out.println(")");
		}
	}

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] S = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        ArrayList<ArrayList<Integer>> result = solution.threeSum(S);
        print(result);
    }
}