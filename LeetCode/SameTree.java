/*
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
*/

import LCLibrary.*;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	if(p == null && q == null) return true;
        if(p != null && q != null && p.val == q.val) {
        	return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}

/*
    Second Round
*/
class Solution2 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        else return false;
    }
}

class Main {
    public static void main(String[] args) {
        TreeNode root1 = Input.buildExampleTree();
        TreeNode root2 = Input.buildExampleTree2();
        Solution solution = new Solution();
        System.out.println(solution.isSameTree(root1, root2));
    }
}
