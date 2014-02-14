import java.io.*;
import java.util.*;
import java.lang.Integer;

class Node {
	int iData;
	Node lChild;
	Node rChild;

	public Node(int key) {
		iData = key;
	}

	public void displayNode() {
		System.out.print(iData + " ");
	}
}

class Tree {
	Node root;

	public Node find(int key) {
		Node current = root;
		while(current != null) {
			if(key == current.iData)
				return current;
			else if(key > current.iData)
				current = current.rChild;
			else
				current = current.lChild;
		}
		return null;
	}

	public void insert(Node newNode) {
		Node current = root;
		if(root == null)
			root = newNode;
		else {
			Node previous;
			while(true) {
				previous = current;
				if(newNode.iData == current.iData) {
					// cannot insert repeat data
					System.err.println("Sorry, cannot insert repeat data");
					break;
				}
				else if(newNode.iData > current.iData) {
					current = current.rChild;
					if(current == null) {
						previous.rChild = newNode;
						break;
					}
				}
				else {
					current = current.lChild;
					if(current == null) {
						previous.lChild = newNode;
						break;
					}
				}
			}
		}
	}

	public void inOrderTraversal() {
		if(root != null)
			inOrderRec(root);
		System.out.println();
	}

	private void inOrderRec(Node node) {
		if(node.lChild != null)
			inOrderRec(node.lChild);
		node.displayNode();
		if(node.rChild != null)
			inOrderRec(node.rChild);
	}
}


class HashTable {
	private Tree[] hashArray;
	private int arraySize;

	public HashTable(int size) {
		arraySize = size;
		hashArray = new Tree[arraySize];
		for(int j=0; j<arraySize; j++) {
			hashArray[j] = new Tree();
		}
	}

	public void displayTable() {
		for(int j=0; j<arraySize; j++) {
			System.out.print(j + ". ");
			hashArray[j].inOrderTraversal();
		}
	}

	public int hashFunc(int key) {
		return key % arraySize;
	}

	public void insert(Node theNode) {
		int key = theNode.iData;
		int hashVal = hashFunc(key);
		hashArray[hashVal].insert(theNode);
	}

	public Node find(int key) {
		int hashVal = hashFunc(key);
		Node theNode = hashArray[hashVal].find(key);
		return theNode;
	}
}

class HashTreeApp {
	public static void main(String[] args) throws IOException {
		int aKey;
		Node aDataItem;
		int size, n, keysPerCell = 100;

		putText("Enter size of hash table: ");
		size = getInt();
		putText("Enter initial number of items: ");
		n = getInt();

		HashTable theHashTable = new HashTable(size);
		for(int j=0; j<n; j++) {
			aKey = (int)(java.lang.Math.random() * keysPerCell * size);
			aDataItem = new Node(aKey);
			theHashTable.insert(aDataItem);
		}

		while(true) {
			putText("Enter first letter of ");
			putText("show, insert, or find: ");
			char choice = getChar();
			switch(choice) {
				case 's':
					theHashTable.displayTable();
					break;
				case 'i':
					putText("Enter key value to insert: ");
					aKey = getInt();
					aDataItem = new Node(aKey);
					theHashTable.insert(aDataItem);
					break;
				case 'f':
					putText("Enter key value to find: ");
					aKey = getInt();
					aDataItem = theHashTable.find(aKey);
					if(aDataItem != null)
						System.out.println("Found " + aKey);
					else
						System.out.println("Could not find " + aKey);
					break;
				default:
					putText("Invalid entry\n");
			}
		}
	}

	public static void putText(String s) {
		System.out.print(s);
		System.out.flush();
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}



