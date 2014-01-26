import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // iteratively solution
    public boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> leftStack = new Stack<TreeNode>();
        Stack<TreeNode> rightStack = new Stack<TreeNode>();
        leftStack.push(root);
        rightStack.push(root);
        boolean symmetric = true;
        while(!leftStack.isEmpty() && !rightStack.isEmpty()) {
            TreeNode t1 = leftStack.pop();
            TreeNode t2 = rightStack.pop();
            if((t1 == null && t2 != null) || (t2 == null && t1 != null)) {
                symmetric = false;
                break;
            }
            if(t1 != null && t2 != null) {
                if(t1.val != t2.val) {
                    symmetric = false;
                    break;
                }
                else {
                    leftStack.push(t1.left);
                    leftStack.push(t1.right);
                    rightStack.push(t2.right);
                    rightStack.push(t2.left);
                }
            }
        }
        if(!leftStack.isEmpty() || !rightStack.isEmpty()) symmetric = false;
        return symmetric;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        root.left = t1;
        TreeNode t2 = new TreeNode(2);
        root.right = t2;
        TreeNode t3 = new TreeNode(3);
        t1.left = t3;
        TreeNode t4 = new TreeNode(4);
        t1.right = t4;
        TreeNode t5 = new TreeNode(4);
        t2.left = t5;
        TreeNode t6 = new TreeNode(3);
        t2.right = t6;
        System.out.println(solution.isSymmetric(root));
    }
}
