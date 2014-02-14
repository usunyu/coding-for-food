import java.io.*;
import java.util.*;

class Node {
	int iData;
	String sData;
	Node leftChild;
	Node rightChild;

	public Node(int iData, String sData) {
		this.iData = iData;
		this.sData = sData;
		leftChild = null;
		rightChild = null;
	}

	public void displayNode() {
		System.out.print(sData);
	}
}

class Tree {
	private Node root;

	public void insert(Node node) {
		if(root == null)
			root = node;
		else {

		}
	}

	public Node getRoot() {
		return root;
	}

	public void displayTree() {
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;
		System.out.println("......................................................");
		while(isRowEmpty==false) {
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;

			for(int j=0; j<nBlanks; j++)
				System.out.print(' ');

			while(globalStack.isEmpty()==false) {
				Node temp = (Node)globalStack.pop();
				if(temp != null) {
					temp.displayNode();
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if(temp.leftChild != null || temp.rightChild != null)
						isRowEmpty = false;
				}
				else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for(int j=0; j<nBlanks*2-2; j++)
					System.out.print(' ');
			}  // end while globalStack not empty

			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty()==false)
				globalStack.push( localStack.pop() );
		}  // end while isRowEmpty is false
		System.out.println("......................................................");
	}
}

class TreeApp {
	static ArrayList<Node> nodeList = new ArrayList<Node>();

	public static void main(String[] args) throws IOException {
		String input;
		
		int num = 0;
		while(true) {
			System.out.print("Enter a char:");
			System.out.flush();
			input = getString();

			if(input.equals(""))
				break;

			Node node = new Node(++num, input);
			nodeList.add(node);
		}

		Tree bigTree = new Tree();
		if(!nodeList.isEmpty()) {
			bigTree.insert(nodeList.get(0));
			insertRec(bigTree.getRoot(), 0);
		}
		bigTree.displayTree();
	}

	public static void insertRec(Node node, int level) {
		if(nodeList.size() > (2 * level + 1)) {
			node.leftChild = nodeList.get(2 * level + 1);
			insertRec(node.leftChild, 2 * level + 1);
		}
		if(nodeList.size() > (2 * level + 2)) {
			node.rightChild = nodeList.get(2 * level + 2);
			insertRec(node.rightChild, 2 * level + 2);
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
