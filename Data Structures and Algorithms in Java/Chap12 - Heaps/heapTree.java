import java.io.*;
import java.util.*;

class Node {
	int iData;			// data used as key value
	Node leftChild;		// this node's left child
	Node rightChild;	// this node's right child
	Node parent;

	public Node(int key) {
		iData = key;
	}

	public void displayNode() {
		System.out.print('{' + iData + '}');
	}
}

class HeapTree {
	Node root;
	int currentSize;

	public HeapTree() {
		currentSize = 0;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public ArrayList<Integer> getPath(int pos) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		while(pos > 1) {
			path.add(pos % 2);
			pos /= 2;
		}
		Collections.reverse(path);
		return path;
	}

	public void insert(int key) {
		Node newNode = new Node(key);
		if(currentSize == 0) {
			root = newNode;
			currentSize++;
			return;
		}
		Node current = root;
		// find the pos and path
		int pos = currentSize + 1;
		ArrayList<Integer> path = getPath(pos);

		Node parent = null;
		for(int i = 0; i < path.size(); i++) {
			int step = path.get(i);
			parent = current;
			if(step == 0) {	// move left
				current = current.leftChild;
				if(current == null) {
					parent.leftChild = newNode;
					newNode.parent = parent;
				}
			}
			else {	// move right
				current = current.rightChild;
				if(current == null) {
					parent.rightChild = newNode;
					newNode.parent = parent;
				}
			}
			//newNode.parent = parent;
		}
		trickleUp(newNode);
		currentSize++;
	}

	public void trickleUp(Node node) {
		while(node.parent != null && node.parent.iData < node.iData) {
			// exchange
			int temp = node.parent.iData;
			node.parent.iData = node.iData;
			node.iData = temp;
			node = node.parent;
		}
	}

	public int remove() {
		if(currentSize == 0)
			return -1;
		int max = root.iData;
		// find the pos and path
		ArrayList<Integer> path = getPath(currentSize);
		Node current = root;
		Node parent = null;
		for(int i = 0; i < path.size(); i++) {
			int step = path.get(i);
			parent = current;
			if(step == 0) {	// move left
				current = current.leftChild;
				if(i == (path.size() - 1))
					parent.leftChild = null;
			}
			else {	// move right
				current = current.rightChild;
				if(i == (path.size() - 1))
					parent.rightChild = null;
			}
		}
		root.iData = current.iData;
		currentSize--;
		if(currentSize == 0)
			root = null;
		else
			trickleDown(root);
		return max;
	}

	public void trickleDown(Node node) {
		Node largerChild;
		while(true) {
			if(node.rightChild != null && node.rightChild.iData > node.leftChild.iData)
				largerChild = node.rightChild;
			else if(node.leftChild != null)
				largerChild = node.leftChild;
			else
				break;

			if(node.iData < largerChild.iData) {
				int temp = largerChild.iData;
				largerChild.iData = node.iData;
				node.iData = temp;
				node = largerChild;
			}
			else
				break;
		}
	}
}

class HeapTreeApp {
	public static void main(String[] args) {
		HeapTree theHeap = new HeapTree();
		theHeap.insert(30);
		theHeap.insert(50);
		theHeap.insert(10);
		theHeap.insert(40);
		theHeap.insert(20);
		while( !theHeap.isEmpty() ) {
			int item = theHeap.remove();
			System.out.print(item + " ");
		}  // end while
		System.out.println();
	}  // end main()
}



