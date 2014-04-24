/*
Print all the paths from root to every leaf in a binary tree.
*/
import java.util.ArrayList;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public String toString() {
    	return "[" + val + "]";
    }
}

class Main {
	/*
		   1
		  / \
		2    3
	   / \  / \
	  4  5 6   7
	*/
	public static TreeNode buildExampleTree() {
		TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2); node1.left = node2;
        TreeNode node3 = new TreeNode(3); node1.right = node3;
        TreeNode node4 = new TreeNode(4); node2.left = node4;
        TreeNode node5 = new TreeNode(5); node2.right = node5;
        TreeNode node6 = new TreeNode(6); node3.left = node6;
        TreeNode node7 = new TreeNode(7); node3.right = node7;
        return node1;
	}

	private static void printPath(TreeNode root, ArrayList<TreeNode> path) {
		if(root == null) return;
		path.add(root);
		if(root.left == null && root.right == null) {	// leaf
			System.out.println(path);
		}
		else {
			printPath(root.left, path);
			printPath(root.right, path);
		}
		path.remove(path.size() - 1);	// backtracking
	}

	public static void printAllPaths(TreeNode root) {
		printPath(root, new ArrayList<TreeNode>());
	}

	public static void main(String[] args) {
		TreeNode root = buildExampleTree();
		printAllPaths(root);
	}
}

