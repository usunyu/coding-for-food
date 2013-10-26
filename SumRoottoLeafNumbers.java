import java.util.*;

// Definition for binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public int dfs(TreeNode root, int num, int sum) {
        num = num * 10 + root.val;
        if(root.left == null && root.right == null) {
            sum = sum + num;
        }
        if(root.left != null) {
            sum = dfs(root.left, num, sum);
        }
        if(root.right != null) {
            sum = dfs(root.right, num, sum);
        }
        return sum;
    }
    
    public int sumNumbers(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(root == null) {
            return 0;
        }
        int sum = dfs(root, 0, 0);
        return sum;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(solution.sumNumbers(root));
    }
}