package LCLibrary;

public class Input {
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
}