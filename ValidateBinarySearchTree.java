import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public boolean isValidBST(TreeNode root, int min, int max) {
        if(root == null) {
            return true;
        }
        boolean isValidBST = true;
        // check this node
        if(root.val <= min || root.val >= max) {
            isValidBST = false;
        }
        else {
            // check its childs
            if(root.left != null) {
                isValidBST = (isValidBST(root.left, min, root.val) && isValidBST);
            }
            if(root.right != null) {
                isValidBST = (isValidBST(root.right, root.val, max) && isValidBST);
            }
        }
        return isValidBST;
    }

    // time complexity : O(N)
    // space complexity : O(1)
    public boolean isValidBST(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(root == null) {
            return true;
        }
        boolean isValidBST = true;
        if(root.left != null) {
            isValidBST = (isValidBST(root.left, Integer.MIN_VALUE, root.val) && isValidBST);
        }
        if(root.right != null) {
            isValidBST = (isValidBST(root.right, root.val, Integer.MAX_VALUE) && isValidBST);
        }
        return isValidBST;
    }

    class IntWraper {
        int val;
        public IntWraper(int val) {
            this.val = val;
        }
    }

    // inorder traversal
    public boolean isValidBST2(TreeNode root, IntWraper prev) {
        if(root == null) {
            return true;
        }
        if(isValidBST2(root.left, prev)) {
            if(root.val > prev.val) {
                prev.val = root.val;
                return isValidBST2(root.right, prev);
            }
        }
        return false;
    }

    public boolean isValidBST2(TreeNode root) {
        IntWraper prev = new IntWraper(Integer.MIN_VALUE);
        return isValidBST2(root, prev);
    }
}

class Main {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(0);
        TreeNode t2 = new TreeNode(-1);
        t1.left = t2;
        Solution solution = new Solution();
        System.out.println(solution.isValidBST2(t1));
    }
}
