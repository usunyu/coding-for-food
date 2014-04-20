/*
You are given a binary tree in which each node contains a value. Design an algorithm to print all paths which 
sum to a given value. The path does not need to start or end at the root or a leaf.
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

	public String toString() {
		return "[" + value + "]";
	}
}

class MyBST {
	MyNode root;
	int pathValue;

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

	public void printPath(MyNode node) {
		int value = pathValue;
		Stack<MyNode> stack = new Stack<MyNode>();
		// find path
		while(value > 0) {
			value -= node.value;
			stack.push(node);
			node = node.parent;
		}
		// print path
		System.out.print("Find path: ");
		while(!stack.isEmpty()) {
			MyNode current = stack.pop();
			current.display();
		}
		System.out.println();
	}

	public void path(int value) {
		pathValue = value;
		pathRec(root, value);
	}

	public void pathRec(MyNode root, int value) {
		if(root == null)
			return;
		if(root.value == value) {
			printPath(root);
			// return;
		}
		// do not stop
		// if(root.value > value)
			// return;
		pathRec(root.leftChild, value - root.value);
		pathRec(root.rightChild, value - root.value);
		pathRec(root.leftChild, value);
		pathRec(root.rightChild, value);
	}
}

class Solution {
	public static void main(String[] args) {
		MyBST bst = new MyBST();
		bst.insert(20);
		bst.insert(10);
		bst.insert(23);
		bst.insert(5);
		bst.insert(15);
		bst.insert(22);
		bst.insert(18);

		bst.path(45);
	}
}
/*
	Second Round
*/
class Solution2 {
	private static void findPath(MyNode root, ArrayList<MyNode> path, ArrayList<ArrayList<MyNode>> paths, int current, int target) {
		if(root == null) return;
		ArrayList<MyNode> list = new ArrayList<MyNode>(path);
		list.add(root);
		int next = current - root.value;
		if(next == 0) paths.add(list);
		// else {
		findPath(root.leftChild, list, paths, next, target);
		findPath(root.rightChild, list, paths, next, target);
		// }
		if(!path.isEmpty()) {
			findPath(root, new ArrayList<MyNode>(), paths, target, target);
		}
	}

	public static ArrayList<ArrayList<MyNode>> findPath(MyNode root, int target) {
		ArrayList<ArrayList<MyNode>> paths = new ArrayList<ArrayList<MyNode>>();
		findPath(root, new ArrayList<MyNode>(), paths, target, target);
		return paths;
	}

	public static void main(String[] args) {
		MyBST bst = new MyBST();
		bst.insert(20);
		bst.insert(10);
		bst.insert(23);
		bst.insert(5);
		bst.insert(15);
		bst.insert(22);
		bst.insert(18);

		System.out.println(findPath(bst.root, 45));
	}
}



