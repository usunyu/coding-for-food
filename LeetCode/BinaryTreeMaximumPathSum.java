/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
*/

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // list[0] store max total sum, list[1] store max height sum
    // WTF!!
    private void maxPathSum(TreeNode root, ArrayList<Integer> list) {
        if(root == null) {
            list.add(null);
            list.add(null);
            return;
        }
        ArrayList<Integer> leftList = new ArrayList<Integer>();
        maxPathSum(root.left, leftList);
        ArrayList<Integer> rightList = new ArrayList<Integer>();
        maxPathSum(root.right, rightList);
        Integer leftTotal = leftList.get(0), leftHeight = leftList.get(1);
        Integer rightTotal = rightList.get(0), rightHeight = rightList.get(1);
        Integer curHeight, curTotal;
        if(leftTotal == null && rightTotal == null) {
            curHeight = root.val;
            curTotal = root.val;
        }
        else if(leftTotal == null || rightTotal == null) {
            Integer height = (leftHeight == null ? rightHeight : leftHeight);
            Integer total = (leftTotal == null) ? rightTotal : leftTotal;
            curHeight = Math.max(root.val, height + root.val);
            curTotal = Math.max(Math.max(root.val, height + root.val), total);
        }
        else {
            curHeight = Math.max(Math.max(leftHeight, rightHeight) + root.val, root.val);
            curTotal = Math.max(Math.max(Math.max(leftTotal, rightTotal), leftHeight + rightHeight + root.val), root.val);
            curTotal = Math.max(curTotal, Math.max(leftHeight + root.val, rightHeight + root.val));
        }
        list.add(curTotal);
        list.add(curHeight);
    }
    
    public int maxPathSum(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        maxPathSum(root, list);
        return list.get(0);
    }

    public int maxPathSum2(TreeNode root) {  
        // pass in an Object that will be filled in the two values
        Data data = maxSubPath(root);
        return data.sum;
    }

    private class Data {
        int path = 0;
        int sum = Integer.MIN_VALUE;
    }

    private Data maxSubPath(TreeNode root) {
        Data data = new Data();
        if (root == null) return data;

        Data l = maxSubPath(root.left);
        Data r = maxSubPath(root.right);

        data.path = Math.max(0, Math.max(l.path, r.path) + root.val);
        data.sum = Math.max(Math.max(l.sum, r.sum), l.path+root.val+r.path);
        return data;
    }

    /*
        Second Round
    */

    static int max = Integer.MIN_VALUE;
    
    private int pathSum(TreeNode root) {
        if(root == null) return 0;
        int left = pathSum(root.left);
        int right = pathSum(root.right);
        int current = left + right + root.val;
        max = Math.max(max, current);
        return Math.max(0, current + Math.max(left, right));
    }
    
    // don't know why cannot pass {-3} in leetcode, WTF!!
    public int maxPathSum3(TreeNode root) {  
        if(root == null) return 0;
        pathSum(root);
        return max;
    }

    class Pair {
        int sum;
        int max = Integer.MIN_VALUE;
    }
    
    private Pair pathSum2(TreeNode root) {
        Pair pair = new Pair();
        if(root == null) return pair;
        Pair left = pathSum2(root.left);
        Pair right = pathSum2(root.right);
        pair.max = Math.max(Math.max(left.max, right.max), root.val + left.sum + right.sum);
        pair.sum = Math.max(0, Math.max(left.sum, right.sum) + root.val);
        return pair;
    }
    
    public int maxPathSum4(TreeNode root) {  
        if(root == null) return 0;
        Pair pair = pathSum2(root);
        return pair.max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        root.left = t1;
        TreeNode t2 = new TreeNode(3);
        root.right = t2;
        System.out.println(solution.maxPathSum4(root));
    }
}
