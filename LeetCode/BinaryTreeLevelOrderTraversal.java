/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:
[
  [3],
  [9,20],
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
import LCLibrary.*;

class Solution {
    // time complexity : O(N)
    // space complexity : O(N)
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
        if(root == null) {
            return levels;
        }
        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            LinkedList<TreeNode> tempQueue = new LinkedList<TreeNode>();
            ArrayList<Integer> level = new ArrayList<Integer>();
            while(!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.removeFirst();
                if(node.left != null) {
                    tempQueue.add(node.left);
                }
                if(node.right != null) {
                    tempQueue.add(node.right);
                }
                level.add(node.val);
            }
            // finish one level
            levels.add(level);
            nodeQueue = tempQueue;
        }
        return levels;
    }

    public ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Queue<TreeNode> tmp = new LinkedList<TreeNode>();
            ArrayList<Integer> sub = new ArrayList<Integer>();
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                sub.add(node.val);
                if(node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            result.add(sub);
            queue = tmp;
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = Input.buildExampleTree();
        System.out.println(solution.levelOrder2(root));
    }
}

