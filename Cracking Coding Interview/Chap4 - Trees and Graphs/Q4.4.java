/*
Given a binary tree, design an algorithm which creates a linked list of all the nodes at
each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
*/

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
}

class Solution {
	static ArrayList<LinkedList<MyNode>> depthList = new ArrayList<LinkedList<MyNode>>();

	public static void displayList() {
		for(int i = 0; i < depthList.size(); i++) {
			System.out.print("Depth " + i + ": ");
			LinkedList<MyNode> linkList = depthList.get(i);
			for(int j = 0; j < linkList.size(); j++)
				linkList.get(j).display();
			System.out.println();
		}
	}

	public static void getDepthNode(MyBST bst) {
		getDepthNodeRec(bst.root, 0);
	}

	public static void getDepthNodeRec(MyNode node, int level) {
		LinkedList<MyNode> linkList;
		if(level >= depthList.size()) {
			linkList = new LinkedList<MyNode>();
			depthList.add(linkList);
		}
		else
			linkList = depthList.get(level);
		linkList.add(node);
		if(node.leftChild != null)
			getDepthNodeRec(node.leftChild, level + 1);
		if(node.rightChild != null)
			getDepthNodeRec(node.rightChild, level + 1);
	}

	public static void main(String[] args) {
		MyBST bst = new MyBST();
		bst.insert(10);
		bst.insert(5);
		bst.insert(15);
		bst.insert(3);
		bst.insert(7);
		bst.insert(1);
		getDepthNode(bst);
		displayList();
	}
}




