/*
Given a binary tree, return the preorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,2,3].

Note: Recursive solution is trivial, could you do it iteratively?
*/

import java.util.ArrayList;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private void preorderTraversal(TreeNode root, ArrayList<Integer> traversal) {
        if(root == null) return;
        traversal.add(root.val);
        preorderTraversal(root.left, traversal);
        preorderTraversal(root.right, traversal);
    }
    
    // recursive
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        preorderTraversal(root, traversal);
        return traversal;
    }

    // iteratively
    public ArrayList<Integer> preorderTraversal2(TreeNode root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        if(root == null) return traversal;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            traversal.add(node.val);
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
        return traversal;
    }

    /*
        Second Round
    */
    public ArrayList<Integer> preorderTraversal3(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        System.out.println(solution.preorderTraversal2(root));
    }
}