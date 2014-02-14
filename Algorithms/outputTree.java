import java.io.*;
import java.util.*;

class TreePair {
	String parent;
	String child;

	public void setPair(String p, String c) {
		parent = p;
		child = c;
	}

	public String getParent() {
		return parent;
	}

	public String getChild() {
		return child;
	}
}

class MyTreeNode {
	String node;
	MyTreeNode firstChild;
	MyTreeNode nextSibling;
	int level;
	boolean hasParent;

	public void setNode(String s) {
		node = s;
	}

	public String getNode() {
		return node;
	}

	public void setChild(MyTreeNode child) {
		if(child != null)
			child.nextSibling = firstChild;
		firstChild = child;
	}

	public MyTreeNode getChild() {
		return firstChild;
	}

	public MyTreeNode getSibling() {
		return nextSibling;
	}

	public void giveParent() {
		hasParent = true;
	}

	public boolean hasParent() {
		return hasParent;
	}

	public void setLevel(int l) {
		level = l;
	}

	public int getLevel() {
		return level;
	}
}

class OutputTreeApp {
	static int n = 5;
	static Hashtable <String, MyTreeNode> myHashTable;
	static TreePair[] treePair;

	public static void myConstruct() {
		for(int i = 0; i < treePair.length; i++) {
			String currentParent = treePair[i].getParent();
			String currentChild = treePair[i].getChild();
			MyTreeNode myTreeNode;
			MyTreeNode myTreeChildNode;
			// if the parent node already in the hashtable
			if(myHashTable.containsKey(currentParent)) {
				myTreeNode = myHashTable.get(currentParent);
			}
			else { //if the parent node not in the hashtable
				myTreeNode = new MyTreeNode();
				myTreeNode.setNode(currentParent);
				myHashTable.put(currentParent, myTreeNode);
			}
			// if the child node already in the hashtable
			if(myHashTable.containsKey(currentChild)) {
				myTreeChildNode = myHashTable.get(currentChild);
			}
			else { //if the child node not in the hashtable
				myTreeChildNode = new MyTreeNode();
				myTreeChildNode.setNode(currentChild);
				myHashTable.put(currentChild, myTreeChildNode);
			}
			// sign it has parent
			myTreeChildNode.giveParent();
			// put the child in the parent's first child and set it as other childs' sibling
			myTreeNode.setChild(myTreeChildNode);
		}
	}

	public static MyTreeNode getRoot() {
		MyTreeNode myTreeNode = null;
		for(int i = 0; i < treePair.length; i++) {
			myTreeNode = myHashTable.get(treePair[i].getParent());
			if(!myTreeNode.hasParent()) {
				break;
			}
		}
		return myTreeNode;
	}

	public static void outputNode(MyTreeNode node) {
		int level = node.getLevel();
		String str = node.getNode();
		for(int i = 0; i < level; i++) {
			System.out.print("\t");
		}
		System.out.println(str);
	}
	// iteration
	public static void outputTree() {
		Stack <MyTreeNode> stack = new Stack <MyTreeNode> ();
		MyTreeNode current = getRoot();
		stack.push(current);
		while(!stack.empty()) {
			current = stack.pop();
			// keep track of the level
			int level = current.getLevel();
			outputNode(current);
			MyTreeNode firstChild = current.getChild();
			// if it has child
			if(firstChild != null) {
				firstChild.setLevel(level + 1);
				stack.push(firstChild);
				MyTreeNode nextSibling = firstChild.getSibling();
				// if it has more than one child
				while(nextSibling != null) {
					nextSibling.setLevel(level + 1);
					stack.push(nextSibling);
					nextSibling = nextSibling.getSibling();
				}
			}
		}
	}

	public static void outputNode(MyTreeNode node, int level) {
		String str = node.getNode();
		for(int i = 0; i < level; i++) {
			System.out.print("\t");
		}
		System.out.println(str);
	}

	public static void outputTreeRecursion(MyTreeNode local, int level) {
		outputNode(local, level);

		MyTreeNode firstChild = local.getChild();
		if(firstChild != null) {
			outputTreeRecursion(firstChild, level + 1);
			MyTreeNode nextSibling = firstChild.getSibling();
			while(nextSibling != null) {
				outputTreeRecursion(nextSibling, level + 1);
				nextSibling = nextSibling.getSibling();
			}
		}
	}
	// recursion
	public static void outputTree2() {
		outputTreeRecursion(getRoot(), 0);
	}

	public static void main(String[] args) {
		treePair = new TreePair[n];
		for(int i = 0; i < n; i++) {
			treePair[i] = new TreePair();
		}
		treePair[0].setPair("A", "B");
		treePair[1].setPair("A", "C");
		treePair[2].setPair("B", "D");
		treePair[3].setPair("B", "E");
		treePair[4].setPair("E", "F");

		myHashTable = new Hashtable <String, MyTreeNode> ();

		myConstruct();

		outputTree();

		outputTree2();
	}
}