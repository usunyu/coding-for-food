/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null) return result;
        // initial
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack2.push(root);
        boolean left = false;
        
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            if(stack1.isEmpty()) {
                // flip flag
                left = left ? false : true;
                ArrayList<Integer> level = new ArrayList<Integer>();
                while(!stack2.isEmpty()) {
                    TreeNode node = stack2.pop();
                    level.add(node.val);
                    if(left) {  // from left to right
                        if(node.left != null) stack1.push(node.left);
                        if(node.right != null) stack1.push(node.right);
                    }
                    else {  // from right to left
                        if(node.right != null) stack1.push(node.right);
                        if(node.left != null) stack1.push(node.left);
                    }
                }
                result.add(level);
            }
            else {
                // flip flag
                left = left ? false : true;
                ArrayList<Integer> level = new ArrayList<Integer>();
                while(!stack1.isEmpty()) {
                    TreeNode node = stack1.pop();
                    level.add(node.val);
                    if(left) {  // from left to right
                        if(node.left != null) stack2.push(node.left);
                        if(node.right != null) stack2.push(node.right);
                    }
                    else {  // from right to left
                        if(node.right != null) stack2.push(node.right);
                        if(node.left != null) stack2.push(node.left);
                    }
                }
                result.add(level);
            }
        }
        return result;
    }

    /*
        Second Round
    */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null) return result;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        boolean fromLeft = true;
        while(!queue.isEmpty()) {
            LinkedList<TreeNode> tmp = new LinkedList<TreeNode>();
            ArrayList<Integer> sub = new ArrayList<Integer>();
            while(!queue.isEmpty()) {
                TreeNode node = queue.pollLast();
                if(fromLeft) {
                    if(node.left != null) tmp.add(node.left);
                    if(node.right != null) tmp.add(node.right);
                }
                else {
                    if(node.right != null) tmp.add(node.right);
                    if(node.left != null) tmp.add(node.left);
                }
                sub.add(node.val);
            }
            result.add(sub);
            fromLeft = fromLeft ? false : true;
            queue = tmp;
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        root.left = node1;
        TreeNode node2 = new TreeNode(3);
        root.right = node2;
        TreeNode node3 = new TreeNode(4);
        node1.left = node3;
        TreeNode node4 = new TreeNode(5);
        node2.right = node4;
        Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> result = solution.zigzagLevelOrder2(root);
        for(ArrayList<Integer> level : result) {
            for(int i : level) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
