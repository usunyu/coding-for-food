import java.util.*;

class Node {
	int iData;			// data used as key value
	double dData;		// other data
	Node leftChild;		// this node's left child
	Node rightChild;	// this node's right child
	boolean visited;	// usded in traversal
	public void displayNode() {
		System.out.print('{');
		System.out.print(iData);
		System.out.print(",");
		System.out.print(dData);
		System.out.print("} ");
	}
}

class Tree {
	private Node root;

	public Node find(int key) {	// the only data field in Tree
		Node current = root;

		while(current.iData != key) {
			if(current.iData > key)
				current = current.leftChild;
			else
				current = current.rightChild;
			if(current == null)
				return null;
		}
		return current;
	}

	public void insert(int id, double dd) {
		Node newNode = new Node();
		newNode.iData = id;
		newNode.dData = dd;
		newNode.visited = false;
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

	public boolean delete(int key) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;

		while(current.iData != key) {
			parent = current;
			if(key < current.iData) {
				isLeftChild = true;
				current = current.leftChild;
			}
			else {
				isLeftChild = false;
				current = current.rightChild;
			}
			if(current == null)
				return false;
		}
		// Delete a Node With No Children
		if(current.leftChild==null && current.rightChild==null) {	// if no children, simply delete it
			if(current == root)
				root = null;
			else if(isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}	// Delete a Node With One Child
		else if(current.rightChild==null) {	// if no right child, replace with left subtree
			if(current == root)
				root = current.leftChild;
			else if(isLeftChild)
				parent.leftChild = current.leftChild;
			else
				parent.rightChild = current.leftChild;
		}
		else if(current.leftChild==null) {	// if no left child, replace with right subtree
			if(current == root)
				root = current.rightChild;
			else if(isLeftChild)
				parent.leftChild = current.rightChild;
			else
				parent.rightChild = current.rightChild;
		}
		else {	// The Node to be Deleted Has Two Children
			// get successor of node to delete (current)
			Node successor = getSuccessor(current);
			// connect parent of current to successor instead
			if(current == root)
				root = successor;
			else if(isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
		}
		return true;
	}

	// returns node with next-highest value after delNode
	// goes to right child, then right child's left descendants
	private Node getSuccessor(Node delNode) {
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild;
		while(current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChild;
		}
		if(successor != delNode.rightChild) {
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		successor.leftChild = delNode.leftChild;
		return successor;
	}

	private void inOrderNonRecursion() {
		Node current = root;
		LinkedList<Node> list = new LinkedList<Node>();
		list.add(current);
		while(list.size() != 0) {
			current = list.peekLast();
			if(current.leftChild != null && current.leftChild.visited == false) {
				list.add(current.leftChild);
			}
			else {
				current.displayNode();
				current.visited = true;
				list.removeLast();
				if(current.rightChild != null)
					list.add(current.rightChild);
			}
		}
	}

	private void inOrderNonRecursion2() {
		Node current = root;
		Stack<Node> stack = new Stack<Node>();
		while(current != null || !stack.isEmpty()) {
			while(current != null) {
				stack.push(current);
				current = current.leftChild;
			}
			current = stack.pop();
			current.displayNode();
			current = current.rightChild;
		}
	}

	private void inOrder(Node localRoot) {
		if(localRoot != null) {
			inOrder(localRoot.leftChild);
			localRoot.displayNode();
			inOrder(localRoot.rightChild);
		}
	}

	private void preOrderNonRecursion() {
		Node current = root;
		LinkedList<Node> stack = new LinkedList<Node>();
		stack.add(root);
		while(stack.size() != 0) {
			current = stack.removeLast();
			current.displayNode();
			if(current.rightChild != null)
				stack.add(current.rightChild);
			if(current.leftChild != null)
				stack.add(current.leftChild);
		}
	}

	private void preOrderNonRecursion2() {
		Node current = root;
		Stack<Node> stack = new Stack<Node>();
		stack.push(current);
		while(!stack.isEmpty()) {
			current = stack.pop();
			current.displayNode();
			if(current.rightChild != null)
				stack.push(current.rightChild);
			if(current.leftChild != null)
				stack.push(current.leftChild);
		}
	}

	private void preOrder(Node localRoot) {
		if(localRoot != null) {
			localRoot.displayNode();
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	private void postOrderNonRecursion() {
		Node current = root;
		LinkedList<Node> list = new LinkedList<Node>();
		list.add(current);
		while(list.size() != 0) {
			current = list.peekLast();
			if(current.leftChild != null && current.leftChild.visited == false) {
				list.add(current.leftChild);
			}
			else if(current.rightChild != null && current.rightChild.visited == false) {
				list.add(current.rightChild);
			}
			else {
				current.displayNode();
				current.visited = true;
				list.removeLast();
			}
		}
	}

	private void postOrderNonRecursion2() {
		Node current = null;
		Node previous = null;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()) {
			current = stack.peek();
			if( (current.leftChild == null && current.rightChild == null) ||		// no childs, can visit directly
				(current.leftChild == previous && current.rightChild == null) ||	// only have left child, and visited
				(current.rightChild == previous) ) {								// has two childs or only has right child, and visited
				current.displayNode();
				stack.pop();
				previous = current;
			}
			else {
				if(current.rightChild != null)
					stack.push(current.rightChild);
				if(current.leftChild != null)
					stack.push(current.leftChild);
			}
		}
	}

	private void postOrder(Node localRoot) {
		if(localRoot != null) {
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			localRoot.displayNode();
		}
	}

	private void levelOrder() {
		Node current = root;
		LinkedList<Node> list = new LinkedList<Node>();
		list.add(current);
		while(list.size() != 0) {
			current = list.remove();
			current.visited = false;
			current.displayNode();
			if(current.leftChild != null)
				list.add(current.leftChild);
			if(current.rightChild != null)
				list.add(current.rightChild);
		}
	}

	public void inOrderTraversal() {
		System.out.println("In-Order Traversal in Recursion:");
		inOrder(root);
		System.out.println();
		System.out.println("In-Order Traversal in Non-Recursion:");
		inOrderNonRecursion2();
		System.out.println();
		System.out.println();
	}

	public void preOrderTraversal() {
		System.out.println("Pre-Order Traversal in Recursion:");
		preOrder(root);
		System.out.println();
		System.out.println("Pre-Order Traversal in Non-Recursion:");
		preOrderNonRecursion2();
		System.out.println();
		System.out.println();
	}

	public void postOrderTraversal() {
		System.out.println("Post-Order Traversal in Recursion");
		postOrder(root);
		System.out.println();
		System.out.println("Post-Order Traversal in Non-Recursion");
		postOrderNonRecursion2();
		System.out.println();
		System.out.println();
	}

	public void levelOrderTraversal() {
		System.out.println("Level-Order Traversal");
		levelOrder();
		System.out.println();
		System.out.println();
	}

	public Node minimum() {
		Node current = root, last = null;
		while(current != null) {
			last = current;
			current = current.leftChild;
		}
		return last;
	}

	public Node maximum() {
		Node current = root, last = null;
		while(current != null) {
			last = current;
			current = current.rightChild;
		}
		return last;
	}

	public void displayTree2() {
		System.out.println();
		Queue<Node> innerQueue = new LinkedList<Node>();
		Queue<Node> outerQueue = new LinkedList<Node>();
		Queue<Node> showQueue = new LinkedList<Node>();
		outerQueue.add(root);
		boolean isFinished = false;
		int count = 0;
		while(!isFinished) {
			isFinished = true;
			while(!outerQueue.isEmpty()) {
				Node current = outerQueue.poll();
				showQueue.add(current);
				if(current != null) {
					innerQueue.add(current.leftChild);
					innerQueue.add(current.rightChild);
					if(current.leftChild != null || current.rightChild != null)
						isFinished = false;
				}
				else {
					innerQueue.add(null);
					innerQueue.add(null);
				}
			}
			if(!isFinished) {
				count = 0;
				while(!innerQueue.isEmpty()) {
					count++;
					outerQueue.add(innerQueue.poll());
				}
			}
		}
		int num = 1;
		while(!showQueue.isEmpty()) {
			count /= 2;
			for(int i = 0; i < num; i++) {
				for(int j = 0; j < count; j++)
					System.out.print("  ");
				Node node = showQueue.poll();
				if(node != null)
					System.out.print(node.iData);
				else
					System.out.print("-*");
			}
			num *= 2;
			System.out.println();
		}
		System.out.println();
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
					System.out.print(temp.iData);
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
}  // end class Tree

class TreeApp {
	public static void main(String[] args) {
		Tree theTree = new Tree();        // make a tree
		theTree.insert(67, 6.7);        // insert 3 nodes
		theTree.insert(41, 4.1);
		theTree.insert(95, 9.5);
		theTree.insert(1, 0.1);
		theTree.insert(45, 4.5);
		theTree.insert(74, 7.4);
		theTree.insert(96, 9.6);
		theTree.insert(0, 0.0);
		theTree.insert(4, 0.4);
		theTree.insert(43, 4.3);
		theTree.insert(61, 6.1);
		theTree.insert(73, 7.3);
		theTree.insert(76, 7.6);
		theTree.insert(2, 0.2);
		theTree.insert(25, 2.5);
		theTree.insert(45, 4.5);
		theTree.insert(65, 6.5);
		theTree.insert(69, 6.9);
		theTree.insert(80, 8.0);

		theTree.displayTree();
		// theTree.displayTree2();

		// Node found = theTree.find(25);  // find node with key 25
		// if(found != null)
		// 	System.out.println("Found the node with key 25");
		// else
		// 	System.out.println("Could not find node with key 25");

		theTree.inOrderTraversal();

		theTree.preOrderTraversal();

		theTree.levelOrderTraversal();

		theTree.postOrderTraversal();

		// System.out.println("Delete the node with key 45");
		// theTree.delete(45);
	}  // end main()
} // end class TreeApp