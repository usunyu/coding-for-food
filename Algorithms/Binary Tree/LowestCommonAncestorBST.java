/*
Given a binary search tree (BST), find the lowest common ancestor of two given nodes in the BST.

http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-search-tree.html
*/
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public String toString() {
    	return "[" + val + "]";
    }
}

class LCA {
	// what if node1 or node2 is not in the bst, bug?
	public static TreeNode lowestCommonAcestor(TreeNode root, TreeNode node1, TreeNode node2) {
		if(root == null || node1 == null || node2 == null) return null;
		if(node1.val < root.val && node2.val < root.val)
			return lowestCommonAcestor(root.left, node1, node2);
		else if(node1.val > root.val && node2.val > root.val)
			return lowestCommonAcestor(root.right, node1, node2);
		else
			return root;
	}
}

class Main {
	/*
		   5
		  / \
		3    8
	   / \  / \
	  1  4 6   9
	*/
	public static TreeNode buildExampleBST() {
		TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3); node1.left = node2;
        TreeNode node3 = new TreeNode(8); node1.right = node3;
        TreeNode node4 = new TreeNode(1); node2.left = node4;
        TreeNode node5 = new TreeNode(4); node2.right = node5;
        TreeNode node6 = new TreeNode(6); node3.left = node6;
        TreeNode node7 = new TreeNode(9); node3.right = node7;
        return node1;
	}

	public static void main(String[] args) {
		TreeNode root = buildExampleBST();
		TreeNode node1 = root.left.right, node2 = root.left.left;
		TreeNode common = LCA.lowestCommonAcestor(root, node1, node2);
		System.out.println(common);
	}
}