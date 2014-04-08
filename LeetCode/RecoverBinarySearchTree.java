/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
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

class WrapInt {
    int index;
}

class Solution {
    private void inorderSearch(TreeNode root, ArrayList<Integer> array) {
        if(root.left != null) inorderSearch(root.left, array);
        array.add(root.val);
        if(root.right != null) inorderSearch(root.right, array);
    }

    private void inorderFix(TreeNode root, ArrayList<Integer> array,  WrapInt wi) {
        if(root.left != null) inorderFix(root.left, array, wi);
        root.val = array.get(wi.index++);
        if(root.right != null) inorderFix(root.right, array, wi);
    }
    
    // space complexity: O(n)
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        ArrayList<Integer> array = new ArrayList<Integer>();
        inorderSearch(root, array);
        Collections.sort(array);
        inorderFix(root, array, new WrapInt());
    }
}

class Solution2 {
    // morris traversal: http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
    // space complexity: O(1)
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root, prev = null, n1 = null, n2 = null;
        while(cur != null) {
            if(cur.left == null) {   // 1. if cur.left = null, output cur and set cur.right to cur
                // visit node
                if(prev != null && cur.val < prev.val) {
                    n2 = cur;
                    if(n1 == null) n1 = prev;
                }
                prev = cur;
                cur = cur.right;
            }
            else {
                // find predecessor
                TreeNode predecessor = cur.left;
                while(predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null) {    // 2. a) if predecessor.right = null, set predecessor.right = cur and set cur = cur.left
                    predecessor.right = cur;
                    cur = cur.left;
                }
                else {  // 2. b) if predecessor.right = cur, set predecessor.right = null(recover tree), output cur and set cur = cur.right
                    predecessor.right = null;
                    // visit node
                    if(prev != null && cur.val < prev.val) {
                        n2 = cur;
                        if(n1 == null) n1 = prev;
                    }
                    prev = cur;
                    cur = cur.right;
                }
            }
        }
        swap(n1, n2);
    }

    private void swap(TreeNode a, TreeNode b) {
        if (a == null || b == null)  return;
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
}
/*
    Second Round
*/
// using O(n) space
class Solution3 {
    private void inorder(TreeNode root, ArrayList<TreeNode> list) {
        if(root == null) return;
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }
    
    public void recoverTree(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        inorder(root, list);
        // find concave, raised
        int c = -1, r = -1;
        // from back
        for(c = list.size() - 1; c >= 0; c--) {
            int left = list.get(c - 1).val, mid = list.get(c).val, right = (c == list.size() - 1 ? Integer.MAX_VALUE : list.get(c + 1).val);
            if(mid < left && mid < right) break;
        }
        // from start
        for(r = 0; r < list.size(); r++) {
            int left = (r == 0 ? Integer.MIN_VALUE : list.get(r - 1).val), mid = list.get(r).val, right = list.get(r + 1).val;
            if(mid > left && mid > right) break;
        }
        if(c == -1 || r == -1) return;
        // swap
        int tmp = list.get(c).val;
        list.get(c).val = list.get(r).val;
        list.get(r).val = tmp;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        TreeNode root = Input.buildExampleBadBST();
        Output.levelOrderTraversalTree(root);
        solution.recoverTree(root);
        Output.levelOrderTraversalTree(root);
    }
}
