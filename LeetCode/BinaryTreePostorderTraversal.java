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
import java.util.Collections;
import LCLibrary.TreeNode;
import LCLibrary.*;

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
}

class Solution2 {
    // iteratively
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
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
}

/*
    Second Round
*/
class Solution3 {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
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

// post-order: left -> right -> curr
// pre-order: curr -> left -> right
// mirror of pre-order: curr -> right -> left. Reverse to get the post-order
class Solution4 {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<TreeNode>();
        while (root != null){
            st.push(root);
            res.add(root.val);
            root = root.right;
        }
        while (!st.isEmpty()){
            TreeNode curr = st.pop();
            curr = curr.left;
            while (curr != null){
                st.push(curr);
                res.add(curr.val);
                curr = curr.right;
            }
        }
        Collections.reverse(res);
        return res;
    }
}  

class Main {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        TreeNode root = Input.buildExampleTree();
        System.out.println(solution.postorderTraversal(root));
    }
}