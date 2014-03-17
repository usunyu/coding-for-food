/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
*/
import LCLibrary.*;

class Solution {
    public void flatten(TreeNode root) {
        if(root != null) {
            TreeNode temp = root.right;
            if(root.left != null) {
                temp = root.right;
                root.right = root.left;
                root.left = null;
                TreeNode travel = root;
                while(travel.right != null) {
                    travel = travel.right;
                }
                travel.right = temp;
                flatten(root.right);
            }
            if(temp != null) {
                flatten(temp);
            }
        }
    }
    /*
        Second Round
    */
    public void flatten2(TreeNode root) {
        if(root == null) return;
        if(root.left != null) {
            TreeNode tmp = root.right;
            TreeNode cur = root.left;
            while(cur.right != null) {
                cur = cur.right;
            }
            cur.right = tmp;
            root.right = root.left;
            root.left = null;
            flatten2(root.right);
        }
        if(root.right != null) {
            flatten2(root.right);
        }
    }
    // non recursive
    public void flatten3(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root;
        while(cur != null) {
            TreeNode tmp = cur.right;
            if(cur.left != null) {
                cur.right = cur.left;
                cur.left = null;
                TreeNode trav = cur;
                while(trav.right != null) 
                    trav = trav.right;
                trav.right = tmp;
            }
            cur = cur.right;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = Input.buildExampleTree2();
        solution.flatten3(root);
        Output.printFlattenTree(root);
    }
}
