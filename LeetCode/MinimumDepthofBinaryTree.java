/*
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
*/

import LCLibrary.*;

class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(left != 0 && right != 0) return Math.min(left, right) + 1;
        else if(left == 0) return right + 1;
        else return left + 1;
    }
    /*
        Second Round
    */
    public int minDepth2(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null) return minDepth2(root.right) + 1;
        if(root.right == null) return minDepth2(root.left) + 1;
        return Math.min(minDepth2(root.left), minDepth2(root.right)) + 1;
    }
}

class Main {
    public static void main(String[] args) {
        TreeNode root = Input.buildExampleTree();
        Solution solution = new Solution();
        System.out.println(solution.minDepth2(root));
    }
}
