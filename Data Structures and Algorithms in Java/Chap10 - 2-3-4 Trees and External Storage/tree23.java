import java.io.*;

class DataItem {
	public double dData;	// one data item

	public DataItem(double dd) {	// constructor
		dData = dd;
	}

	public void displayItem() {	// display item, format "/27"
		System.out.print("/" + dData);
	}

}	// end class DataItem

class Node {
	private static final int ORDER = 3;
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

class Tree23 {
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
			if(curNode.isLeaf()) {
				if(!curNode.isFull()) {
					curNode.insertItem(tempItem);
					break;
				}
				else {
					split(curNode, tempItem);
					break;
				}
			}
			else
				curNode = getNextChild(curNode, dValue);
		}	// end while
	}	// end insert()

	public Node split(Node thisNode, DataItem newItem) {	// split the node
		DataItem itemA, itemB, itemC;
		Node parent, childNew, child0, child1, child2;
		Node result = null;

		if(newItem.dData <= thisNode.getItem(0).dData) {
			itemA = newItem;
			itemB = thisNode.getItem(0);
			itemC = thisNode.getItem(1);
		}
		else if(newItem.dData >= thisNode.getItem(1).dData) {
			itemA = thisNode.getItem(0);
			itemB = thisNode.getItem(1);
			itemC = newItem;
		}
		else {
			itemA = thisNode.getItem(0);
			itemB = newItem;
			itemC = thisNode.getItem(1);
		}

		if(thisNode == root) {
			parent = new Node();
			parent.insertItem(itemB);
			
			thisNode.removeItem();
			thisNode.removeItem();
			thisNode.insertItem(itemA);
			
			child1 = new Node();
			child1.insertItem(itemC);
			
			parent.connectChild(0, thisNode);
			parent.connectChild(1, child1);
			
			root = parent;

			result = child1;
		}
		else {
			parent = thisNode.getParent();
			if(!parent.isFull()) {
				if(parent.getChild(0) == thisNode) {
					child1 = parent.disconnectChild(1);
					parent.connectChild(2, child1);
					
					childNew = new Node();
					childNew.insertItem(itemC);
					parent.connectChild(1, childNew);

					parent.insertItem(itemB);

					thisNode.removeItem();
					thisNode.removeItem();
					thisNode.insertItem(itemA);

					result = childNew;
				}
				else if(parent.getChild(1) == thisNode) {
					thisNode.removeItem();
					thisNode.removeItem();
					thisNode.insertItem(itemA);

					parent.insertItem(itemB);

					childNew = new Node();
					childNew.insertItem(itemC);
					parent.connectChild(2, childNew);

					result = childNew;
				}
				else
					System.err.println("error 1!");
			}
			else {
				// to do
				Node rightParent = split(parent, itemB);
				if(parent.getChild(0) == thisNode) {
					thisNode.removeItem();
					thisNode.removeItem();
					thisNode.insertItem(itemA);
					
					parent.connectChild(0, thisNode);
					
					child1 = parent.disconnectChild(1);
					child2 = parent.disconnectChild(2);

					childNew = new Node();
					childNew.insertItem(itemC);
					parent.connectChild(1, childNew);

					rightParent.connectChild(0, child1);
					rightParent.connectChild(1, child2);
				}
				else if(parent.getChild(1) == thisNode) {
					thisNode.removeItem();
					thisNode.removeItem();
					thisNode.insertItem(itemA);
					parent.connectChild(1, thisNode);
					
					child2 = parent.disconnectChild(2);
					rightParent.connectChild(1, child2);
					
					childNew = new Node();
					childNew.insertItem(itemC);
					rightParent.connectChild(0, childNew);
				}
				else if(parent.getChild(2) == thisNode) {
					thisNode.removeItem();
					thisNode.removeItem();
					thisNode.insertItem(itemA);
					parent.disconnectChild(2);
					rightParent.connectChild(0, thisNode);
					
					childNew = new Node();
					childNew.insertItem(itemC);
					rightParent.connectChild(1, childNew);
				}
			}
		}

		return result;
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
}	// end class Tree23

class Tree23App {
	public static void main(String[] args) throws IOException {
		double value;
		Tree23 theTree = new Tree23();
		theTree.insert(60);
		theTree.insert(80);
		theTree.insert(40);
		theTree.insert(70);
		theTree.insert(90);
		theTree.insert(95);
		theTree.insert(93);
		theTree.displayTree();
	}  // end main()
}	// end class Tree23App


