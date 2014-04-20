/*
Implement a function to check if a binary tree is balanced. 
For the purposes of this question, a balanced tree is defined to be a tree such that the heights of 
the two subtrees of any node never differ by more than one.
*/

import java.util.*;

class MyNode {
	int value;
	MyNode leftChild;
	MyNode rightChild;

	public MyNode(int value) {
		this.value = value;
	}
}

class MyBST {
	MyNode root;

	public void insert(int value) {
		MyNode node = new MyNode(value);
		if(root == null)
			root = node;
		else {
			MyNode current = root;
			while(true) {
				if(value > current.value) {
					if(current.rightChild != null)
						current = current.rightChild;
					else {
						current.rightChild = node;
						break;
					}
				}
				else {
					if(current.leftChild != null)
						current = current.leftChild;
					else {
						current.leftChild = node;
						break;
					}
				}
			}
		}
	}

	public boolean isBalance() {
		// return isBalanceRec(root);
		if(checkHeight(root) == -1)
			return false;
		else
			return true;
	}

	private int checkHeight(MyNode node) {
		if(node == null)
			return 0;
		int leftHeight = checkHeight(node.leftChild);
		int rightHeight = checkHeight(node.rightChild);
		if(leftHeight == -1 || rightHeight == -1)
			return -1;
		if(abs(leftHeight - rightHeight) > 1)
			return -1;
		return max(leftHeight, rightHeight) + 1;
	}

	private boolean isBalanceRec(MyNode node) {
		// check current node's is balance
		int leftHeight, rightHeight;
		if(node.leftChild != null)
			leftHeight = height(node.leftChild);
		else
			leftHeight = 0;
		if(node.rightChild != null)
			rightHeight = height(node.rightChild);
		else
			rightHeight = 0;

		if(abs(leftHeight - rightHeight) > 1)
			return false;

		boolean leftBalance = false, rightBalance = false;
		// check whether left child is balance
		if(node.leftChild != null)
			leftBalance = isBalanceRec(node.leftChild);
		else
			leftBalance = true;
		if(node.rightChild != null)
			rightBalance = isBalanceRec(node.rightChild);
		else
			rightBalance = true;

		if(leftBalance && rightBalance)
			return true;
		else
			return false;
	}

	private int height(MyNode node) {
		if(node.leftChild == null && node.rightChild == null)
			return 1;
		else if(node.leftChild != null && node.rightChild != null)
			return max(height(node.leftChild), height(node.rightChild)) + 1;
		else if(node.leftChild != null)
			return height(node.leftChild) + 1;
		else
			return height(node.rightChild) + 1;
	}

	private int max(int i1, int i2) {
		return i1 > i2 ? i1 : i2;
	}

	private int abs(int i) {
		return i >= 0 ? i : 0 - i;
	}
}

class Solution {
	public static void main(String[] args) {
		MyBST bst = new MyBST();
		bst.insert(10);
		bst.insert(5);
		bst.insert(15);
		bst.insert(3);
		bst.insert(7);
		bst.insert(1);
		System.out.println("The tree is " + (bst.isBalance() ? "balance" : "not balance"));
	}
}
