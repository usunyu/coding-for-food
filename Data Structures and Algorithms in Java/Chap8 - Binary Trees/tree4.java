import java.io.*;
import java.util.*;

class Node {
	char ch;
	Node leftChild;
	Node rightChild;

	public Node(char ch) {
		this.ch = ch;
		leftChild = null;
		rightChild = null;
	}

	public void displayNode() {
		System.out.print(ch);
	}
}

class Tree {
	private Node root;

	public Tree(Node node) {
		root = node;
	}

	public void merge(Tree t1, Tree t2) {
		root.leftChild = t1.getRoot();
		root.rightChild = t2.getRoot();
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

class PostTreeCons {
	private String input;
	Stack<Tree> treeStack;

	public PostTreeCons(String input) {
		this.input = input;
		treeStack = new Stack<Tree>();
	}

	public Tree doConstruct() {
		for(int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			Node node = new Node(ch);
			if(ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') {
				Tree tree = new Tree(node);
				treeStack.push(tree);
			}
			else {
				if(ch == '+' || ch == '-' || ch == '*' || ch == '/') {
					Tree t1 = treeStack.pop();
					Tree t2 = treeStack.pop();
					Node op = new Node(ch);
					Tree t3 = new Tree(op);
					t3.merge(t2, t1);
					treeStack.push(t3);
				}
			}
		}
		return treeStack.pop();
	}
}

class TreeApp {
	static ArrayList<Node> nodeList = new ArrayList<Node>();

	public static void main(String[] args) throws IOException {
		String input;
		
		while(true) {
			System.out.print("Enter a postfix expression:");
			System.out.flush();
			input = getString();

			if(input.equals(""))
				break;

			PostTreeCons pot = new PostTreeCons(input);
			Tree exTree = pot.doConstruct();

			exTree.displayTree();
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}
