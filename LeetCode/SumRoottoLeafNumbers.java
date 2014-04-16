/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
*/
import java.util.*;
import LCLibrary.*;

class Solution {
    public int dfs(TreeNode root, int num, int sum) {
        num = num * 10 + root.val;
        if(root.left == null && root.right == null) {
            sum = sum + num;
        }
        if(root.left != null) {
            sum = dfs(root.left, num, sum);
        }
        if(root.right != null) {
            sum = dfs(root.right, num, sum);
        }
        return sum;
    }
    
    public int sumNumbers(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(root == null) {
            return 0;
        }
        int sum = dfs(root, 0, 0);
        return sum;
    }
}

/*
    Second Round
*/
class Solution2 {
    private int sumNumbers(TreeNode root, int num, int sum) {
        if(root == null) return sum;
        num = num * 10 + root.val;
        if(root.left == null && root.right == null) return num + sum;
        sum = sumNumbers(root.left, num, sum);
        sum = sumNumbers(root.right, num, sum);
        return sum;
    }
    
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root, 0, 0);
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        TreeNode root = Input.buildExampleTree2();
        System.out.println(solution.sumNumbers(root));
    }
}