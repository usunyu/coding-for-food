/*
How to find inorder successor and predecessor in a Binary Search Tree?

http://www.quora.com/Data-Structures/How-to-find-inorder-successor-and-predecessor-in-a-Binary-Search-Tree

Without parent?
http://algorithmsandme.blogspot.com/2013/08/binary-search-tree-inorder-successor.html
*/
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public TreeNode(int x) { val = x; }

    public String toString() {
    	return "[" + val + "]";
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
        TreeNode node2 = new TreeNode(3); node1.left = node2; node2.parent = node1;
        TreeNode node3 = new TreeNode(8); node1.right = node3; node3.parent = node1;
        TreeNode node4 = new TreeNode(1); node2.left = node4; node4.parent = node2;
        TreeNode node5 = new TreeNode(4); node2.right = node5; node5.parent = node2;
        TreeNode node6 = new TreeNode(6); node3.left = node6; node6.parent = node3;
        TreeNode node7 = new TreeNode(9); node3.right = node7; node7.parent = node3;
        return node1;
	}

	/*
	1. The node has a right subtree.
	If the given node has a right subtree then by the BST property the next larger key must be in the 
	right subtree. Since all keys in a right subtree are larger than the key of the given node, 
	the successor must be the smallest of all those keys in the right subtree.

	2. The node does not have a right subtree.
	In this case we will have to look up the tree since that's the only place we might find the next larger key. 
	There is no point looking at the left subtree as all keys in the left subtree are guaranteed to be smaller 
	than the key in the given tree.

	When we look up from the given node, there can be two cases: first, the current node is the left child of 
	its parent. In this case the parent is the successor node. This is because the parent always comes next 
	in inorder traversal if you are done with left subtree (rooted at the current node).
	Second, the current node is the right child of the parent. This is an interesting case. In this case, 
	as you keep going up the ancestor chain you encounter smaller values if you are going up but larger values 
	if you are going right. The successor node will be the first node up the ancestor chain that you encounter 
	on the right chain.
	*/
	public static TreeNode findSuccessor(TreeNode node) {
		if(node == null) 
			return null;
		TreeNode successor;
		if(node.right != null) {	// The node has a right subtree
			successor = node.right;
			while(successor.left != null)
				successor = successor.left;
		}
		else {	// The node does not have a right subtree
			TreeNode prev = node, parent = node.parent;
			while(parent != null && parent.left != prev) {
				prev = parent;
				parent = parent.parent;
			}
			successor = parent;
		}
		return successor;
	}

	/*
	Finding the predecessor node follows a symmetric logic.
	*/
	public static TreeNode findPredecessor(TreeNode node) {
		if(node == null) 
			return null;
		TreeNode predecessor;
		if(node.left != null) {
			predecessor = node.left;
			while(predecessor.right != null)
				predecessor = predecessor.right;
		}
		else {
			TreeNode prev = node, parent = node.parent;
			while(parent != null && parent.right != prev) {
				prev = parent;
				parent = parent.parent;
			}
			predecessor = parent;
		}
		return predecessor;
	}

	// Without Parent pointer
	/*
	1. Start with root.
	2. If the node is given has less than root, then search on left side and update successor.
	3. If the node is greater than root, then search in right part, don't update successor.
	4. If we find the node and if the node has right sub tree, then the minimum node on the right sub tree of 
		node is the in-order successor.
	5. Return successor
	*/
	public static TreeNode findSuccessorWithoutParent(TreeNode root, TreeNode node) {
		TreeNode successor = null, current = root;
		if(root == null)
			return null;
		while(current != node) {
			if(current.val > node.val) {
				successor = current;
				current = current.left;
			}
			else {
				current = current.right;
			}
		}
		if(current != null && current.right != null) {
			current = current.right;
			while(current.left != null)
				current = current.left;
			successor = current;
		}
		return successor;
	}

	public static TreeNode findPredecessorWithoutParent(TreeNode root, TreeNode node) {
		TreeNode predecessor = null, current = root;
		if(root == null)
			return null;
		while(current != node) {
			if(current.val > node.val) {
				current = current.left;
			}
			else {
				predecessor = current;
				current = current.right;
			}
		}
		if(current != null && current.left != null) {
			current = current.left;
			while(current.right != null)
				current = current.right;
			predecessor = current;
		}
		return predecessor;
	}	

	public static void main(String[] args) {
		TreeNode root = buildExampleBST();

		System.out.println(findSuccessor(root.left.right));
		System.out.println(findSuccessorWithoutParent(root, root.left.right));

		System.out.println(findPredecessor(root.left.right));
		System.out.println(findPredecessorWithoutParent(root, root.left.right));
	}
}
