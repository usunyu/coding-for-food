/*
Write an algorithm to find the 'next'node (i.e., in-order successor) of a given node in
a binary search tree. You may assume that each node has a link to its parent.
*/

import java.util.*;

class MyNode {
	int value;
	MyNode leftChild;
	MyNode rightChild;
	MyNode parent;

	public MyNode(int value) {
		this.value = value;
	}

	public void display() {
		System.out.print(value + " ");
	}
}

class MyBST {
	MyNode root;

	public void insert(MyNode node) {
		// MyNode node = new MyNode(value);
		if(root == null)
			root = node;
		else {
			MyNode current = root;
			while(true) {
				if(node.value > current.value) {
					if(current.rightChild != null)
						current = current.rightChild;
					else {
						current.rightChild = node;
						node.parent = current;
						break;
					}
				}
				else {
					if(current.leftChild != null)
						current = current.leftChild;
					else {
						current.leftChild = node;
						node.parent = current;
						break;
					}
				}
			}
		}
	}

	// 1. if the node is root
	// next: 1) right child's left most child if it has right child
	//       2) null if it has no right child
	// 2. if the node is left child of parent
	// next: 1) parent if it has no right child
	//       2) right child's left most child if it has right child
	// 3. if the node is right child of parent
	// next: 1) parent's parent until parent is left child of parent's parent
	//       2) right child's left most child if it has right child
	//       3) null above 2 conditions do not meet
	public MyNode next(MyNode node) {
		if(node == null)
			return null;
		MyNode nextNode = null;
		// 1. if the node is root
		if(node.parent == null) {
			if(node.rightChild != null) {	// 1) right child's left most child if it has right child
				nextNode = node.rightChild;
				while(nextNode.leftChild != null)
					nextNode = nextNode.leftChild;
				return nextNode;
			}
			else	// 2) null if it has no right child
				return null;
		}
		else {
			MyNode parent = node.parent;
			if(node == parent.leftChild) {	// 2. if the node is left child of parent
				if(node.rightChild != null) {
					nextNode = node.rightChild;
					while(nextNode.leftChild != null)
						nextNode = nextNode.leftChild;
					return nextNode;
				}
				else	// 1) parent if it has no right child
					return parent;
			}
			else {	// 3. if the node is right child of parent
				if(node.rightChild != null) {	// 2) right child's left most child if it has right child
					nextNode = node.rightChild;
					while(nextNode.leftChild != null)
						nextNode = nextNode.leftChild;
					return nextNode;
				}
				else {
					MyNode gradParent = parent.parent;
					while(gradParent != null) {
						if(gradParent.leftChild == parent)
							return gradParent;
						parent = gradParent;
						gradParent = gradParent.parent;
					}
					return null;
				}
			}
		}
	}
}

class Solution {
	public static void main(String[] args) {
		MyBST bst = new MyBST();
		MyNode node1 = new MyNode(10);
		bst.insert(node1);
		MyNode node2 = new MyNode(5);
		bst.insert(node2);
		MyNode node3 = new MyNode(15);
		bst.insert(node3);
		MyNode node4 = new MyNode(3);
		bst.insert(node4);
		MyNode node5 = new MyNode(7);
		bst.insert(node5);
		MyNode node6 = new MyNode(1);
		bst.insert(node6);
		MyNode node7 = new MyNode(6);
		bst.insert(node7);
		MyNode node8 = new MyNode(8);
		bst.insert(node8);
		MyNode node9 = new MyNode(13);
		bst.insert(node9);
		MyNode node10 = new MyNode(17);
		bst.insert(node10);
		System.out.println("node1's (" + node1.value + ") next is (" + (bst.next(node1) == null ? "null" : bst.next(node1).value) + ")");
		System.out.println("node2's (" + node2.value + ") next is (" + (bst.next(node2) == null ? "null" : bst.next(node2).value) + ")");
		System.out.println("node3's (" + node3.value + ") next is (" + (bst.next(node3) == null ? "null" : bst.next(node3).value) + ")");
		System.out.println("node4's (" + node4.value + ") next is (" + (bst.next(node4) == null ? "null" : bst.next(node4).value) + ")");
		System.out.println("node5's (" + node5.value + ") next is (" + (bst.next(node5) == null ? "null" : bst.next(node5).value) + ")");
		System.out.println("node6's (" + node6.value + ") next is (" + (bst.next(node6) == null ? "null" : bst.next(node6).value) + ")");
		System.out.println("node7's (" + node7.value + ") next is (" + (bst.next(node7) == null ? "null" : bst.next(node7).value) + ")");
		System.out.println("node8's (" + node8.value + ") next is (" + (bst.next(node8) == null ? "null" : bst.next(node8).value) + ")");
		System.out.println("node9's (" + node9.value + ") next is (" + (bst.next(node9) == null ? "null" : bst.next(node9).value) + ")");
		System.out.println("node10's (" + node10.value + ") next is (" + (bst.next(node10) == null ? "null" : bst.next(node10).value) + ")");
	}
}



