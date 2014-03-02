/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7]
  [9,20],
  [3],
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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null) return result;
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.add(root);
        while(!list.isEmpty()) {
            LinkedList<TreeNode> temp = new LinkedList<TreeNode>();
            ArrayList<Integer> sub = new ArrayList<Integer>();
            while(!list.isEmpty()) {
                TreeNode node = list.remove();
                sub.add(node.val);
                if(node.left != null) temp.add(node.left);
                if(node.right != null) temp.add(node.right);
            }
            list = temp;
            result.add(sub);
        }
        // just reverse the normal level order traversal
        Collections.reverse(result);
        return result;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> result) {
        for(ArrayList<Integer> list : result) {
            System.out.println(list.toString());
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        root.right = node1;
        TreeNode node2 = new TreeNode(3);
        node1.left = node2;
        TreeNode node3 = new TreeNode(4);
        root.left = node3;
        Solution solution = new Solution();
        print(solution.levelOrderBottom(root));
    }
}
