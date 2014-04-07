/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public void pathSum(TreeNode root, int sum, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths) {
        if(root != null) {
            sum -= root.val;
            path.add(root.val);
            if(sum == 0 && root.left == null && root.right == null) {
                paths.add(path);
            }
            else {
                if(root.left != null)  pathSum(root.left, sum, new ArrayList<Integer>(path), paths);
                if(root.right != null)  pathSum(root.right, sum, new ArrayList<Integer>(path), paths);
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        if(root == null) return paths;
        pathSum(root, sum, new ArrayList<Integer>(), paths);
        return paths;
    }
}

class Solution2 {
    public void pathSum(TreeNode root, int sum, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths) {
        if(root == null) return;
        path.add(root.val);
        if(root.val == sum && root.left == null && root.right == null) {
            paths.add(path);
        }
        else {
            pathSum(root.left, sum - root.val, new ArrayList<Integer>(path), paths);
            pathSum(root.right, sum - root.val, new ArrayList<Integer>(path), paths);
        }
    }
    
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        pathSum(root, sum, new ArrayList<Integer>(), paths);
        return paths;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = Input.buildExampleTree2();
        Output.printLevelLists(solution.pathSum(root, 7));
    }
}
