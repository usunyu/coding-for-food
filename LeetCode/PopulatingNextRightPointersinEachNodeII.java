/*
Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    // my solution for Populating Next Right Pointers in Each Node
    // is still suitable for Populating Next Right Pointers in Each Node II :)
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        LinkedList<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        // initial
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeLinkNode prev = null;
            LinkedList<TreeLinkNode> tempQueue = new LinkedList<TreeLinkNode>();
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
    public void connect(TreeLinkNode root) {
        TreeLinkNode ptr = root, next, prev;
        while(ptr != null) {
            next = null; prev = null;    // reset
            while(ptr != null) {
                if(ptr.left != null) {
                    if(prev != null)
                        prev.next = ptr.left;
                    if(next == null)
                        next = ptr.left;
                    prev = ptr.left;
                }
                if(ptr.right != null) {
                    if(prev != null)
                        prev.next = ptr.right;
                    if(next == null)
                        next = ptr.right;
                    prev = ptr.right;
                }
                ptr = ptr.next;
            }
            ptr = next;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        TreeLinkNode root = Input.buildExampleTreeLink3();
        solution.connect(root);
    }
}
