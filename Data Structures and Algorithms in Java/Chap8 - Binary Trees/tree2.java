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

	public void insert(Tree tree) {
		if(root == null)
			root = tree.getRoot();
		else {
			Node pNode = new Node(0, "+");
			pNode.leftChild = root;
			pNode.rightChild = tree.getRoot();
			root = pNode;
		}
	}

	public void insert(Tree t1, Tree t2) {
		Node pNode = new Node(0, "+");
		pNode.leftChild = t1.getRoot();
		pNode.rightChild = t2.getRoot();
		root = pNode;
	}

	public Node getRoot() {
		return root;
	}

	public void insert(int iData, String sData) {
		Node newNode = new Node(iData, sData);
		if(root == null)
			root = newNode;
		else {

		}
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
	public static void main(String[] args) throws IOException {
		String input;
		Queue<Tree> treeQueue = new LinkedList<Tree>();
		int num = 0;
		while(true) {
			System.out.print("Enter a char:");
			System.out.flush();
			input = getString();

			if(input.equals(""))
				break;

			Tree tree = new Tree();
			tree.insert(++num, input);
			treeQueue.offer(tree);
		}

		Tree bigTree = new Tree();
		Queue<Tree> interTreeQueue = new LinkedList<Tree>();
		// construct tree
		while(!treeQueue.isEmpty()) {

			Tree tree1 = treeQueue.poll();
			Tree tree2;
			Tree tree3 = new Tree();
			if(!treeQueue.isEmpty()) {
				tree2 = treeQueue.poll();
				tree3.insert(tree1, tree2);
			}
			else
				tree3.insert(tree1);

			interTreeQueue.offer(tree3);

			if(treeQueue.isEmpty()) {
				treeQueue = interTreeQueue;
				interTreeQueue = new LinkedList<Tree>();

				if(treeQueue.size() == 1) {
					bigTree = treeQueue.poll();
				}
			}
		}

		bigTree.displayTree();
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
