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

    // time complexity: O(N^2)
    // space complexity: O(1)
	public ArrayList<ArrayList<Integer>> threeSum2(int[] num) {  
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();  
		if(num.length < 3) { return result; }
		Arrays.sort(num);
		for(int i = 0; i < num.length - 2; i++) {
			// remove duplicate
			if(i > 0 && num[i - 1] == num[i]) { continue; }
			int target = -num[i];
			int p1 = i + 1; int p2 = num.length - 1;
			while(p1 < p2) {
				int sum = num[p1] + num[p2];
				if(sum == target) {	// we find triple
					ArrayList<Integer> subResult = new ArrayList<Integer>();
					subResult.add(num[i]); subResult.add(num[p1]); subResult.add(num[p2]);
					result.add(subResult);
					// remove duplicate
					do {
						p1++;
					}
					while(num[p1] == num[p1 - 1] && p1 < p2);
				}
				else if(sum > target) {
					p2--;
				}
				else {
					p1++;
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
        // int[] S = {0,0,0};
        ArrayList<ArrayList<Integer>> result = solution.threeSum2(S);
        print(result);
    }
}