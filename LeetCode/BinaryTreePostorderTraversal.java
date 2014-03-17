/*
Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

Note: Recursive solution is trivial, could you do it iteratively?
*/

import java.util.ArrayList;
import java.util.Stack;
import LCLibrary.TreeNode;
import LCLibrary.Input;

class Solution {
    private void postorderTraversal(TreeNode root, ArrayList<Integer> traversal) {
        if(root == null) return;
        postorderTraversal(root.left, traversal);
        postorderTraversal(root.right, traversal);
        traversal.add(root.val);
    }
    
    // recursive
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        postorderTraversal(root, traversal);
        return traversal;
    }

    // iteratively
    public ArrayList<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        TreeNode pointer = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(!stack.isEmpty() || pointer != null) {
            if(pointer != null) {
                while(pointer != null) {
                    stack.push(pointer);
                    pointer = pointer.left;
                }
                pointer = stack.peek().right;
            }
            else {
                TreeNode node = stack.pop();
                traversal.add(node.val);
                if(!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if(parent.left == node) {
                        pointer = parent.right;
                    }
                }
            }
        }
        return traversal;
    }

    /*
        Second Round
    */
    public ArrayList<Integer> postorderTraversal3(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        TreeNode ptr = root, last = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(!stack.isEmpty() || ptr != null) {
            if(ptr != null) {
                while(ptr != null) {
                    stack.push(ptr);
                    ptr = ptr.left;
                }
            }
            else {
                TreeNode node = stack.peek();
                if(node.right != null && node.right != last) {  // we haven't traversal node.right
                    ptr = node.right;
                }
                else {
                    stack.pop();
                    last = node;    // record last node
                    result.add(node.val);
                }
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = Input.buildExampleTree();
        System.out.println(solution.postorderTraversal3(root));
    }
}