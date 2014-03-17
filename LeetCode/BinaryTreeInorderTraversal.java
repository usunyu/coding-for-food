/*
Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?

confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public void inorderTraversal(TreeNode root, ArrayList<Integer> result) {
        if(root == null) return;
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    // recursion solution
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> result = new ArrayList<Integer>();
        inorderTraversal(root, result);
        return result;
    }

    // iterative solution
    public ArrayList<Integer> inorderTraversal2(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    /*
        Second Round
    */
    public ArrayList<Integer> inorderTraversal3(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        TreeNode ptr = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(ptr != null || !stack.isEmpty()) {
            if(ptr != null) {
                while(ptr != null) {
                    stack.push(ptr);
                    ptr = ptr.left;
                }
            }
            else {
                TreeNode node = stack.pop();
                result.add(node.val);
                ptr = node.right;
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = Input.buildExampleTree();
        System.out.println(solution.inorderTraversal3(root));
    }
}

