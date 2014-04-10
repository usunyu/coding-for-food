/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path 
equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

import LCLibrary.*;

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        boolean res = false;
        sum -= root.val;
        if(sum == 0 && root.left == null && root.right == null) return true;
        else {
            if(root.left != null) res = res || hasPathSum(root.left, sum);
            if(root.right != null) res = res || hasPathSum(root.right, sum);
        }
        return res;
    }
}
/*
    Second Round
*/
class Solution2 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.val == sum && root.left == null && root.right == null) return true;
        if(hasPathSum(root.left, sum - root.val)) return true;
        if(hasPathSum(root.right, sum - root.val)) return true;
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = Input.buildExampleTree2();
        System.out.println(solution.hasPathSum(root, 7));
    }
}
