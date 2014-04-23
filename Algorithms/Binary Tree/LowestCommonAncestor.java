/*
Given a binary tree, find the lowest common ancestor of two given nodes in the tree.

http://leetcode.com/2011/07/lowest-common-ancestor-of-a-binary-tree-part-i.html
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

class IntWrap {
	int count;
	
	public String toString() {
		return "[" + count + "]";
	}
}

class LCA {
	public static TreeNode lowestCommonAcestor(TreeNode root, TreeNode node1, TreeNode node2) {
		IntWrap wrap = new IntWrap();
		TreeNode ret = lowestCommonAcestor(root, node1, node2, wrap);
		return wrap.count == 2 ? ret : null;
	}

	// use count flag if we already find one node
	public static TreeNode lowestCommonAcestor(TreeNode root, TreeNode node1, TreeNode node2, IntWrap wrap) {
		if(root == null || wrap.count == 2) return null;	// when count==2, already found, pruning
		if(root == node1 || root == node2) {
			wrap.count++;
			// do not stop, fix node not in tree bug
			lowestCommonAcestor(root.left, node1, node2, wrap);
			lowestCommonAcestor(root.right, node1, node2, wrap);
			return root;
		}
		TreeNode left = lowestCommonAcestor(root.left, node1, node2, wrap);
		TreeNode right = lowestCommonAcestor(root.right, node1, node2, wrap);
		if(left != null && right != null)
			return root;
		else if(left != null)
			return left;
		else
			return right;
	}
}

class Main {
	/*
		   1
		  / \
		 2   3
		    /
		   4
		    \
		     5
	*/
	public static TreeNode buildExampleTree() {
		TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2); node1.left = node2;
        TreeNode node3 = new TreeNode(3); node1.right = node3;
        TreeNode node4 = new TreeNode(4); node3.left = node4;
        TreeNode node5 = new TreeNode(5); node4.right = node5;
        return node1;
	}

	public static void main(String[] args) {
		TreeNode root = buildExampleTree();
		// TreeNode node1 = root.right, node2 = new TreeNode(10);
		TreeNode node1 = root.right, node2 = root.right.left;
		TreeNode common = LCA.lowestCommonAcestor(root, node1, node2);
		System.out.println(common);
	}
}