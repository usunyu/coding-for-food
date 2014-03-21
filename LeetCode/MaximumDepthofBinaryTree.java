/*
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*/

import LCLibrary.*;

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    /*
        Second Round
    */
    public int maxDepth2(TreeNode root) {
        if(root == null) return 0;
        else return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

class Main {
    public static void main(String[] args) {
        TreeNode root = Input.buildExampleTree();
        Solution solution = new Solution();
        System.out.println(solution.maxDepth(root));
    }
}
