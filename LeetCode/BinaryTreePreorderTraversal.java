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
import LCLibrary.*;

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
}

class Solution2 {
    // iteratively
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
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
}
/*
    Second Round
*/
class Solution3 {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
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

// Morris Solution
// O(1) space
class Solution4 {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        TreeNode cur = root;
        while(cur != null) {
            if(cur.left == null) {
                result.add(cur.val);    // visit
                cur = cur.right;
            }
            else {
                TreeNode prec = cur.left;
                while(prec.right != null && prec.right != cur) prec = prec.right;
                if(prec.right == null) {    // not traversaled
                    result.add(cur.val);    // visit
                    prec.right = cur;
                    cur = cur.left;
                }
                if(prec.right == cur) {    // already traversaled
                    prec.right = null;
                    cur = cur.right;
                }
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        TreeNode root = Input.buildExampleTree();
        System.out.println(solution.preorderTraversal(root));
    }
}