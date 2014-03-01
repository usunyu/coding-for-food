/*
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the 
depth of the two subtrees of every node never differ by more than 1.
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private int getHeight(TreeNode root) {
        if(root == null) return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if(leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        if(getHeight(root) != -1) return true;
        else return false;
    }

    /*
        Second Round
    */
    private int getHeight2(TreeNode root) {
        if(root == null) return 0;
        int left = getHeight2(root.left);
        if(left == -1) return -1;
        int right = getHeight2(root.right);
        if(right == -1) return -1;
        if(Math.abs(left - right) > 1) return -1;
        else return Math.max(left, right) + 1;
    }

    public boolean isBalanced2(TreeNode root) {
        return getHeight2(root) != -1;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        root.left = t1;
        TreeNode t2 = new TreeNode(2);
        root.right = t2;
        TreeNode t3 = new TreeNode(3);
        t1.left = t3;
        TreeNode t4 = new TreeNode(4);
        t1.right = t4;
        TreeNode t5 = new TreeNode(4);
        t2.left = t5;
        TreeNode t6 = new TreeNode(3);
        t2.right = t6;
        System.out.println(solution.isBalanced2(root));
    }
}