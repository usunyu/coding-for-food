/*
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        LinkedList<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        // initial
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeLinkNode prev = null;
            LinkedList<TreeLinkNode> tempQueue = new LinkedList<TreeLinkNode>();
            // connect one level
            while(!queue.isEmpty()) {
                TreeLinkNode current = queue.remove();
                if(current.left != null) tempQueue.add(current.left);
                if(current.right != null) tempQueue.add(current.right);
                if(prev != null) {
                    prev.next = current;
                }
                prev = current;
            }
            queue = tempQueue;
        }
    }
}

class Solution2 {
    private void connect(TreeLinkNode parent, TreeLinkNode root) {
        if(root == null) return;
        // connect its two own childrens
        if(root.left != null)
            root.left.next = root.right;
        // connect with brother
        if(parent != null && parent.next != null && parent.right == root)
            root.next = parent.next.left;
        // recursion
        connect(root, root.left);
        connect(root, root.right);
    }
    
    public void connect(TreeLinkNode root) {
        connect(null, root);
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        TreeLinkNode root = Input.buildExampleTreeLink();
        solution.connect(root);
    }
}
