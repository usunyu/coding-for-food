/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
    public boolean isValidBST(TreeNode root, int min, int max) {
        if(root == null) {
            return true;
        }
        boolean isValidBST = true;
        // check this node
        if(root.val <= min || root.val >= max) {
            isValidBST = false;
        }
        else {
            // check its childs
            if(root.left != null) {
                isValidBST = (isValidBST(root.left, min, root.val) && isValidBST);
            }
            if(root.right != null) {
                isValidBST = (isValidBST(root.right, root.val, max) && isValidBST);
            }
        }
        return isValidBST;
    }

    // time complexity : O(N)
    // space complexity : O(1)
    public boolean isValidBST(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(root == null) {
            return true;
        }
        boolean isValidBST = true;
        if(root.left != null) {
            isValidBST = (isValidBST(root.left, Integer.MIN_VALUE, root.val) && isValidBST);
        }
        if(root.right != null) {
            isValidBST = (isValidBST(root.right, root.val, Integer.MAX_VALUE) && isValidBST);
        }
        return isValidBST;
    }
}

class Solution2 {
    class IntWraper {
        int val;
        public IntWraper(int val) {
            this.val = val;
        }
    }

    // inorder traversal
    public boolean isValidBST(TreeNode root, IntWraper prev) {
        if(root == null) {
            return true;
        }
        if(isValidBST(root.left, prev)) {
            if(root.val > prev.val) {
                prev.val = root.val;
                return isValidBST(root.right, prev);
            }
        }
        return false;
    }

    public boolean isValidBST(TreeNode root) {
        IntWraper prev = new IntWraper(Integer.MIN_VALUE);
        return isValidBST(root, prev);
    }
}
/*
    Second Round
*/
class Solution3 {
    private boolean isValidBST(TreeNode root, int min, int max) {
        if(root == null) return true;
        if(min >= root.val || root.val >= max) return false;
        if(!isValidBST(root.left, min, root.val)) return false;
        if(!isValidBST(root.right, root.val, max)) return false;
        return true;
    }
    
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}

class Main {
    public static void main(String[] args) {
        TreeNode root = Input.buildExampleBadBST();
        Solution solution = new Solution();
        System.out.println(solution.isValidBST(root));
    }
}
