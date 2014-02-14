/*
	The diameter of a tree is the number of nodes on the longest path between two leaves in the tree.
	The diagram below shows a tree with diameter nine, the leaves that form the ends of a longest path are shaded (note that there 
	is more than one path in each tree of length nine, but no path longer than nine nodes).

	In particular, note that the diameter of a tree T is the largest of the following quantities:
		the diameter of T's left subtree
		the diameter of T's right subtree
		the longest path between leaves that goes through the root of T

	Given the root node of the tree, return the diameter of the tree
*/

class Node {
	Node left, right;
	int data;
	Node(int newData) {
		left = right = null;
		data = newData;
	}
}

class Solution {
	static int getHeight(Node root) {
		if(root == null) return 0;
    	return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}

	/* Write your custom functions here */
	static int diameterOfTree(Node root) {
		/* For your reference
	   	class Node {
	   		Node left, right;
			int data;
			Node(int newData) {
				left = right = null;
				data = newData;
			}
		}
		*/
		if(root == null) return 0;
		return Math.max(Math.max(diameterOfTree(root.left), diameterOfTree(root.right)), getHeight(root.left) + getHeight(root.right) + 1);
	}

    public static void main(String args[] ) throws Exception {
    	Node root = new Node(40);
    	Node node1 = new Node(30);
    	root.left = node1;
    	Node node2 = new Node(65);
    	root.right = node2;
    	Node node3 = new Node(22);
    	node1.left = node3;
    	Node node4 = new Node(38);
    	node1.right = node4;
    	Node node5 = new Node(78);
    	node2.left = node5;

    	System.out.println(diameterOfTree(root));
    }
}