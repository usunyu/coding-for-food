/*
	For non-leaf nodes, three arrangements are possible:
	• A node with one data item always has two children
	• A node with two data items always has three children
	• A node with three data items always has four children

	For convenience we number the data items in a link from 0 to 2, and the child links from 0 to 3:
	• All children in the subtree rooted at child 0 have key values less than key 0.
	• All children in the subtree rooted at child 1 have key values greater than key 0 but less than key 1.
	• All children in the subtree rooted at child 2 have key values greater than key 1 but less than key 2.
	• All children in the subtree rooted at child 3 have key values greater than key 2.

	Node Splits
	Insertion becomes more complicated if a full node is encountered on the path down to the insertion point. 
	When this happens, the node must be split. It's this splitting process that keeps the tree balanced. The 
	kind of 2-3-4 tree we're discussing here is often called a top-down 2-3-4 tree because nodes are split on 
	the way down to the insertion point.
	Let's name the data items in the node that's about to be split A, B, and C. Here's what happens in a split.
	• A new, empty node is created. It's a sibling of the node being split, and is placed to its right.
	• Data item C is moved into the new node.
	• Data item B is moved into the parent of the node being split.
	• Data item A remains where it is.
	• The rightmost two children are disconnected from the node being split and connected to the new node.

	Splitting the Root
	When a full root is encountered at the beginning of the search for the insertion point, the resulting split 
	is slightly more complicated:
	• A new node is created that becomes the new root and the parent of the node being split.
	• A second new node is created that becomes a sibling of the node being split.
	• Data item C is moved into the new sibling.
	• Data item B is moved into the new root.
	• Data item A remains where it is.
	• The two rightmost children of the node being split are disconnected from it and connected to the new 
	right-hand node.
*/

// tree234.java
// demonstrates 234 tree
// to run this program: C>java Tree234App

import java.io.*;	// for I/O
import java.lang.Integer;	// for parseInt()

class DataItem {
	public double dData;	// one data item

	public DataItem(double dd) {	// constructor
		dData = dd;
	}

	public void displayItem() {	// display item, format "/27"
		System.out.print("/" + dData);
	}

}	// end class DataItem

// used for inorder
class IndexItem {
	public int iData;

	public IndexItem(int id) {	// constructor
		iData = id;
	}
}

class Node {
	private static final int ORDER = 4;
	private int numItems;
	private Node parent;
	private Node childArray[] = new Node[ORDER];
	private DataItem itemArray[] = new DataItem[ORDER-1];

	// connect child to this node
	public void connectChild(int childNum, Node child) {
		childArray[childNum] = child;

		if(child != null)
			child.parent = this;
	}

	// disconnect child from this node, return it
	public Node disconnectChild(int childNum) {
		Node tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	public Node getChild(int childNum) {
		return childArray[childNum];
	}

	public Node getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return (childArray[0] == null) ? true : false;
	}

	public int getNumItems() {
		return numItems;
	}

	public DataItem getItem(int index) {	// get DataItem at index
		return itemArray[index];
	}

	public boolean isFull() {
		return (numItems == ORDER - 1) ? true : false;
	}

	public int findItem(double key) {	// return index of item (within node)
		for(int j = 0; j < ORDER - 1; j++) {
			if(itemArray[j] == null)
				break;
			else if(itemArray[j].dData == key)	// if found, 
				return j;
		}
		return -1;	// otherwise
	}	// end findItem

	public int insertItem(DataItem newItem) {
		// assumes node is not full
		numItems++;	// will add new item
		double newKey = newItem.dData;	// key of new item

		for(int j = ORDER - 2; j >= 0; j--) {	// start on right,
			if(itemArray[j] == null)	// if item null,
				continue;	// go left one cell
			else {	// not null, get its key
				double itsKey = itemArray[j].dData;
				if(newKey < itsKey)	// if it's bigger
         			itemArray[j + 1] = itemArray[j];	// shift it right
         		else {
         			itemArray[j + 1] = newItem;	// insert new item
         			return j + 1;	// return index to new item
         		}
			}	// end else (not null)
		}	// end for, shifted all items,
		itemArray[0] = newItem;	// insert new item
		return 0;
	}	// end insertItem()

	public DataItem removeItem() {	// remove largest item
		// assumes node not empty
		DataItem temp = itemArray[numItems-1];	// save item
		itemArray[numItems-1] = null;	// disconnect it
		numItems--;	// one less item
		return temp;	// return item
	}

	public void displayNode() {	// format "/24/56/74/"
		for(int j=0; j<numItems; j++)
			itemArray[j].displayItem();	// "/56"
		System.out.println("/");	// final "/"
	}
}

class Tree234 {
	private static final int ORDER = 4;
	private Node root = new Node();            // make root node

	public int find(double key) {
		Node curNode = root;
		int childNumber;
		while(true) {
			if(( childNumber = curNode.findItem(key) ) != -1)
				return childNumber;	// found it
			else if( curNode.isLeaf() )
				return -1;	// can't find it
			else	// search deeper
				curNode = getNextChild(curNode, key);
		}	// end while
	}

	// insert a DataItem
	public void insert(double dValue) {
		Node curNode = root;
		DataItem tempItem = new DataItem(dValue);

		while(true) {
			if( curNode.isFull() ) {	// if node full,
				split(curNode);		// split it
				curNode = curNode.getParent();	// back up search once
				curNode = getNextChild(curNode, dValue);
			}	// end if(node is full)
			else if( curNode.isLeaf() )	// if node is leaf
				break;	// go insert
			else	// node is not full, not a leaf; so go to lower level
				curNode = getNextChild(curNode, dValue);
		}	// end while
		curNode.insertItem(tempItem);	// insert new DataItem
	}	// end insert()

	public void split(Node thisNode) {	// split the node
		// assumes node is full
		DataItem itemB, itemC;
		Node parent, child2, child3;
		int itemIndex;

		itemC = thisNode.removeItem();	// remove items from
		itemB = thisNode.removeItem();	// this node
		child2 = thisNode.disconnectChild(2);	// remove children
		child3 = thisNode.disconnectChild(3);	// from this node

		Node newRight = new Node();	// make new node

		if(thisNode == root) {	// if this is the root,
			root = new Node();	// make new root
			parent = root;	// root is our parent

			root.connectChild(0, thisNode);	// connect to parent
		}
		else	// this node not the root
			parent = thisNode.getParent();    // get parent
		// deal with parent
		itemIndex = parent.insertItem(itemB);	// item B to parent
		int n = parent.getNumItems();	// total items?
		for(int j = n - 1; j > itemIndex; j--) {	// move parent's connections
			Node temp = parent.disconnectChild(j);	// one child
			parent.connectChild(j + 1, temp);	// to the right
		}

		// connect newRight to parent
		parent.connectChild(itemIndex + 1, newRight);

		// deal with newRight
		newRight.insertItem(itemC);       // item C to newRight
		newRight.connectChild(0, child2); // connect to 0 and 1
		newRight.connectChild(1, child3); // on newRight
	}	// end split()

	// gets appropriate child of node during search for value
	public Node getNextChild(Node theNode, double theValue) {
		int j;
		// assumes node is not empty, not full, not a leaf
		int numItems = theNode.getNumItems();
		for(j = 0; j < numItems; j++) {        // for each item in node
			if( theValue < theNode.getItem(j).dData )
				return theNode.getChild(j);  // return left child
		}	// end for
		return theNode.getChild(j);	// we're greater, so return right child
	}

	public void displayTree() {
		recDisplayTree(root, 0, 0);
	}

	private void recDisplayTree(Node thisNode, int level, int childNumber) {
		System.out.print("level=" + level + " child=" + childNumber + "");
		thisNode.displayNode();               // display this node
		// call ourselves for each child of this node
		int numItems = thisNode.getNumItems();
		for(int j = 0; j < numItems + 1; j++) {
			Node nextNode = thisNode.getChild(j);
			if(nextNode != null)
				recDisplayTree(nextNode, level+1, j);
			else
				return;
		}
	}	// end recDisplayTree()

	public DataItem minimum() {
		Node current = root;
		while(current.getChild(0) != null)
			current = current.getChild(0);
		return current.getItem(0);
	}

	public void inOrderTraversal() {
		recInOrderTraversal(root);
		System.out.println();
	}

	public void recInOrderTraversal(Node node) {
		for(int i = 0; i < ORDER; i++) {

			if(node.getChild(i) != null)
				recInOrderTraversal(node.getChild(i));

			if(i < ORDER - 1 && node.getItem(i) != null)
				node.getItem(i).displayItem();
		}
	}

	public void sort(double[] dArray) {
		IndexItem index = new IndexItem(0);
		recSort(dArray, root, index);
	}

	public void recSort(double[] dArray, Node node, IndexItem index) {
		for(int i = 0; i < ORDER; i++) {

			if(node.getChild(i) != null)
				recSort(dArray, node.getChild(i), index);

			if(i < ORDER - 1 && node.getItem(i) != null)
				dArray[index.iData++] = node.getItem(i).dData;
		}
	}
}	// end class Tree234

class Tree234App {
	public static void sort(double[] dArray) {
		Tree234 theTree = new Tree234();
		for(int i = 0; i < dArray.length; i++)
			theTree.insert(dArray[i]);
		theTree.sort(dArray);
	}

	public static void main(String[] args) throws IOException {
		double value;
		Tree234 theTree = new Tree234();
		theTree.insert(50);
		theTree.insert(40);
		theTree.insert(60);
		theTree.insert(30);
		theTree.insert(70);
		while(true) {
			putText("Enter first letter of ");
			putText("show(s), insert(i), find(f), inorder(n), sort(o) or min(m): ");
			char choice = getChar();
			switch(choice) {
				case 's':
					theTree.displayTree();
					break;
				case 'i':
					putText("Enter value to insert: ");
					value = getInt();
					theTree.insert(value);
					break;
				case 'f':
					putText("Enter value to find: ");
					value = getInt();
					int found = theTree.find(value);
					if(found != -1)
						System.out.println("Found "+value);
					else
						System.out.println("Could not find "+value);
					break;
				case 'm':
					System.out.print("Min data is ");
					theTree.minimum().displayItem();
					System.out.println();
					break;
				case 'n':
					theTree.inOrderTraversal();
					break;
				case 'o':
					double[] dArray = new double[10];
					dArray[0] = 90;
					dArray[1] = 80;
					dArray[2] = 70;
					dArray[3] = 60;
					dArray[4] = 50;
					dArray[5] = 40;
					dArray[6] = 30;
					dArray[7] = 20;
					dArray[8] = 10;
					dArray[9] = 0;
					sort(dArray);
					for(int i = 0; i < dArray.length; i++)
						System.out.print(dArray[i] + " ");
					System.out.println();
					break;
				default:
					putText("Invalid entry\n");
            }  // end switch
		}  // end while
	}  // end main()

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
}	// end class Tree234App







