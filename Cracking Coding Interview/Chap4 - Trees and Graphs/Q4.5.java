import java.util.*;

class MyNode {
	int value;
	MyNode leftChild;
	MyNode rightChild;
	int level;

	public MyNode(int value) {
		this.value = value;
	}

	public void display() {
		System.out.print(value + " ");
	}
}

class MyBinaryTree {
	MyNode root;

	public void construct() {
		root = new MyNode(10);
		MyNode l1 = new MyNode(5);
		MyNode l2 = new MyNode(15);
		root.leftChild = l1;
		root.rightChild = l2;
		MyNode l3 = new MyNode(7);
		MyNode l4 = new MyNode(3);
		l1.leftChild = l3;
		l1.rightChild = l4;
		MyNode l5 = new MyNode(1);
		l3.leftChild = l5;
	}

	public void constructBST() {
		root = new MyNode(10);
		MyNode l1 = new MyNode(5);
		MyNode l2 = new MyNode(15);
		root.leftChild = l1;
		root.rightChild = l2;
		MyNode l3 = new MyNode(3);
		MyNode l4 = new MyNode(7);
		l1.leftChild = l3;
		l1.rightChild = l4;
		MyNode l5 = new MyNode(1);
		l3.leftChild = l5;
	}

	public boolean isBST() {	// not correct
		if(root == null)
			return false;
		boolean isBST = true;
		LinkedList<MyNode> queue = new LinkedList<MyNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			MyNode current = queue.poll();
			if(current.leftChild != null) {
				if(current.leftChild.value > current.value) {
					isBST = false;
					break;
				}
				queue.add(current.leftChild);
			}
			if(current.rightChild != null) {
				if(current.rightChild.value < current.value) {
					isBST = false;
					break;
				}
				queue.add(current.rightChild);
			}
		}
		return isBST;
	}

	public boolean isBST2() {
		return isBST2Rec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST2Rec(MyNode node, int min, int max) {
		if(node == null)
			return true;
		if(node.value <= min || node.value > max)
			return false;
		return isBST2Rec(node.leftChild, min, node.value) && isBST2Rec(node.rightChild, node.value, max);
	}
}

class Q4_5App {
	public static void main(String[] args) {
		MyBinaryTree tree1 = new MyBinaryTree();
		MyBinaryTree tree2 = new MyBinaryTree();
		tree1.construct();
		tree2.constructBST();
		System.out.println("T1 is" + (tree1.isBST2() ? "" : " not") + " Binary Search Tree.");
		System.out.println("T2 is" + (tree2.isBST2() ? "" : " not") + " Binary Search Tree.");
	}
}

