import java.io.*;
import java.util.*;

class Node {
	int iData;			// data used as key value
	Node leftChild;		// this node's left child
	Node rightChild;	// this node's right child
	public void displayNode() {
		System.out.print('{' + iData + '}');
	}
}

class PriorityTree {
	private Node root;

	public void insert(int id) {
		Node newNode = new Node();
		newNode.iData = id;
		if(root == null) {
			root = newNode;
		}
		else {
			Node current = root;
			Node parent;
			while(true) {
				parent = current;
				if(id < current.iData) {
					current = current.leftChild;
					if(current == null) {
						parent.leftChild = newNode;
						break;
					}
				}
				else {
					current = current.rightChild;
					if(current == null) {
						parent.rightChild = newNode;
						break;
					}
				}
			}
		}
	}

	public int removeMax() {
		Node current = root;
		Node parent = null;
		while(current.rightChild != null) {
			parent = current;
			current = current.rightChild;
		}
		if(parent != null)
			parent.rightChild = current.leftChild;
		else
			root = current.leftChild;
		return current.iData;
	}

	public boolean isEmpty() {
		return root == null;
	}
}  // end class PriorityTree

class PriorityTreeApp {
	public static void main(String[] args) {
		PriorityTree thePQ = new PriorityTree();
		thePQ.insert(30);
		thePQ.insert(50);
		thePQ.insert(10);
		thePQ.insert(40);
		thePQ.insert(20);
		while( !thePQ.isEmpty() ) {
			int item = thePQ.removeMax();
			System.out.print(item + " ");
		}  // end while
		System.out.println();
	}  // end main()
}
