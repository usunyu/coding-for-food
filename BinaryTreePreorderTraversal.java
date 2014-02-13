import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    private void preorderTraversal(TreeNode root, ArrayList<Integer> traversal) {
        if(root == null) return;
        traversal.add(root.val);
        preorderTraversal(root.left, traversal);
        preorderTraversal(root.right, traversal);
    }
    
    // recursive
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> traversal = new ArrayList<Integer>();
        preorderTraversal(root, traversal);
        return traversal;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        System.out.println(solution.preorderTraversal(root));
    }
}