/*
Given a binary tree, to print all paths which sum to a given value.
The path does not need to start or end at the root or a leaf
*/
import java.util.ArrayList;
import java.util.HashSet;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public String toString() {
    	return "[" + val + "]";
    }
}

class NodePair {
	TreeNode node1, node2;
	
	public NodePair(TreeNode n1, TreeNode n2) {
		node1 = n1;
		node2 = n2;
	}
}

class Solution {
	public static ArrayList<NodePair> solve(TreeNode root, int target) {
		ArrayList<NodePair> pairs = new ArrayList<NodePair>();
		// get height of tree
		int height = height(root);
		// store in heap
		int size = (int)Math.pow(2, height) - 1;
		TreeNode[] heap = new TreeNode[size];
		store(root, heap, -1, false);
		// for every pair
		for(int i = 0; i < size; i++) {
			for(int j = i + 1; j < size; j++) {
				if(heap[i] == null || heap[j] == null) continue;
				if(distance(heap, i, j) == target) {
					pairs.add(new NodePair(heap[i], heap[j]));
				}
			}
		}
		return pairs;
	}
	
	// n1 < n2
	public static int distance(TreeNode[] heap, int n1, int n2) {
		HashSet<Integer> set = new HashSet<Integer>();
		// store all n1's ancestor
		int p1 = n1;
		while(p1 != 0) {
			int parent = (p1 - 1) / 2;
			set.add(parent);
			p1 = parent;
		}
		// find common ancestor
		int p2 = n2;
		int common = -1;
		while(p2 != 0) {
			int parent = (p2 - 1) / 2;
			if(parent == n1) {	// n1 is common ancestor
				common = n1;
				break;
			}
			else if(set.contains(parent)) {
				common = parent;
				break;
			}
			p2 = parent;
		}
		// cal distance
		int distance = 0;
		p1 = n1;
		while(p1 != common) {
			distance++;
			p1 = (p1 - 1) / 2;
		}
		if(common != n1) {
			p2 = n2;
			while(p2 != common) {
				distance++;
				p2 = (p2 - 1) / 2;
			}
		}
		distance++;
		return distance;
	}
	
	public static void store(TreeNode root, TreeNode[] heap, int parent, boolean leftChild) {
		int current;
		if(parent == -1) {	// root
			current = 0;
		}
		else {
			if(leftChild) {	// left child of parent
				current = 2 * parent + 1;
			}
			else {	// right child of parent
				current = 2 * parent + 2;
			}
		}
		heap[current] = root;
		if(root.left != null) {
			store(root.left, heap, current, true);
		}
		if(root.right != null) {
			store(root.right, heap, current, false);
		}
	}
	
	public static int height(TreeNode root) {
		if(root == null) return 0;
		else return Math.max(height(root.left), height(root.right)) + 1;
	}
	
	public static void main(String[] args) {
		TreeNode root = buildExampleTree();
		ArrayList<NodePair> pairs = solve(root, 5);
		printPairs(pairs);
	}
	
	public static void printPairs(ArrayList<NodePair> pairs) {
		for(NodePair pair : pairs) {
			System.out.println(pair.node1.val + " - " + pair.node2.val);
		}
	}
	
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
}