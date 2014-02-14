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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numTrees(10));
    }
}
