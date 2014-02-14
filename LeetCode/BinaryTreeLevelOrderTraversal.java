import java.util.*;

// Definition for binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

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
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> levels) {
        for(ArrayList<Integer> level : levels) {
            for(int val : level) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.left = left;
        root.right = right;
        print(solution.levelOrder(root));
    }
}

