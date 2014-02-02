
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
    	if(p == null && q == null) return true;
        if(p != null && q != null && p.val == q.val) {
        	return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
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
        System.out.println(solution.isSameTree(root, node1));
    }
}
