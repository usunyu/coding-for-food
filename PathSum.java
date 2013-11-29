
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(-2);
        TreeNode lc1 = new TreeNode(4);
        root.left = lc1;
        TreeNode rc1 = new TreeNode(-3);
        root.right = rc1;
        System.out.println(solution.hasPathSum(root, -5));
    }
}
