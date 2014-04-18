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

class Solution3 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        LinkedList<TreeNode> level1 = new LinkedList<TreeNode>(), level2 = new LinkedList<TreeNode>();
        level1.add(p); level2.add(q);
        while(!level1.isEmpty() && !level2.isEmpty()) {
            LinkedList<TreeNode> temp1 = new LinkedList<TreeNode>(), temp2 = new LinkedList<TreeNode>();
            while(!level1.isEmpty() && !level2.isEmpty()) {
                TreeNode n1 = level1.poll(), n2 = level2.poll();
                if(n1 == null && n2 == null);
                else if(n1 == null || n2 == null) return false;
                else if(n1.val != n2.val) return false;
                if(n1 != null && n2 != null) {
                    temp1.add(n1.left); temp1.add(n1.right);
                    temp2.add(n2.left); temp2.add(n2.right);
                }
            }
            if(!level1.isEmpty() || !level2.isEmpty()) return false;
            level1 = temp1; level2 = temp2;
        }
        if(!level1.isEmpty() || !level2.isEmpty()) return false;
        return true;
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
