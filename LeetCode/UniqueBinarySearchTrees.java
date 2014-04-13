/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

class Solution {
    // time complexity: O(n^2)
    // space complexity: O(n)
    public int numTrees(int n) {
        if(n == 0) return 0;
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                count[i] += count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }
}

/*
    Second Round
*/
class Solution2 {
    private int numTrees(int n, HashMap<Integer, Integer> cache) {
        if(n == 0 || n == 1) return 1;
        if(cache.containsKey(n)) return cache.get(n);
        int ret = 0;
        for(int i = 0; i < n; i++) {
            ret = ret + numTrees(i) * numTrees(n - 1 - i);
        }
        cache.put(n, ret);
        return ret;
    }
    
    public int numTrees(int n) {
        HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();
        return numTrees(n, cache);
    }  
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numTrees(10));
    }
}
