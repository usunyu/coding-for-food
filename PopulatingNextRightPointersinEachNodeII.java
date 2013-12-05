import java.util.*;

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
}

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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeLinkNode root = new TreeLinkNode(1);
        TreeLinkNode lc1 = new TreeLinkNode(2);
        root.left = lc1;
        TreeLinkNode rc1 = new TreeLinkNode(3);
        root.right = rc1;
        solution.connect(root);
    }
}
