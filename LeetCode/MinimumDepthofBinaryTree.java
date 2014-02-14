
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(left != 0 && right != 0) return Math.min(left, right) + 1;
        else if(left == 0) return right + 1;
        else return left + 1;
    }
}

class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        root.right = node1;
        TreeNode node2 = new TreeNode(3);
        node1.left = node2;
        Solution solution = new Solution();
        System.out.println(solution.minDepth(root));
    }
}
