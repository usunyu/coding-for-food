/*
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.

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
    // iteratively solution
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> leftStack = new Stack<TreeNode>();
        Stack<TreeNode> rightStack = new Stack<TreeNode>();
        leftStack.push(root.left);
        rightStack.push(root.right);
        boolean symmetric = true;
        while(!leftStack.isEmpty() && !rightStack.isEmpty()) {
            TreeNode t1 = leftStack.pop();
            TreeNode t2 = rightStack.pop();
            if((t1 == null && t2 != null) || (t2 == null && t1 != null)) {
                symmetric = false;
                break;
            }
            if(t1 != null && t2 != null) {
                if(t1.val != t2.val) {
                    symmetric = false;
                    break;
                }
                else {
                    leftStack.push(t1.left);
                    leftStack.push(t1.right);
                    rightStack.push(t2.right);
                    rightStack.push(t2.left);
                }
            }
        }
        if(!leftStack.isEmpty() || !rightStack.isEmpty()) symmetric = false;
        return symmetric;
    }
}

class Solution2 {
    private boolean isSymmetricRec(TreeNode lnode, TreeNode rnode) {
        if(lnode == null && rnode == null) return true;
        if(lnode == null || rnode == null || lnode.val != rnode.val) return false;
        return isSymmetricRec(lnode.left, rnode.right) && isSymmetricRec(lnode.right, rnode.left);
    }

    // recursively solution
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricRec(root.left, root.right);
    }
}
/*
    Second Round
*/
class Solution3 {
    private boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) return true;
        if(node1 == null || node2 == null || node1.val != node2.val) return false;
        return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
    }
    
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        TreeNode root = Input.buildExampleTree3();
        System.out.println(solution.isSymmetric(root));
    }
}
