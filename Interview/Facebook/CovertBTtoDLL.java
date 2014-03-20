/*
        8
    4       1
9          2  3
        
        
<- 9 = 4 = 8 = 2 = 1 = 3 ->
*/

class Node {
	int data;
	Node left, right;

	public Node(int data) {
		this.data = data;
	}
}

// this class is help to store first node and last node of a list
// in the recursion solution, with this aid class, I can get the 
// first and last node of list in O(1)
class NodePair {
	Node first;
	Node last;
}

class Solution {
	// recursion solution
	public static NodePair convertRec(Node root) {
		NodePair currentPart = new NodePair();
		// initial current part
		currentPart.first = root; currentPart.last = root;
		if(root.left != null) {
			NodePair leftPart = convertRec(root.left);
			// merge current part and left part
			currentPart.first = leftPart.first;
			// connect to the last node of left part
			leftPart.last.right = root;
			root.left = leftPart.last;
		}
		if(root.right != null) {
			NodePair rightPart = convertRec(root.right);
			// merge current part and right part
			currentPart.last = rightPart.last;
			// connect to the first node of right part
			rightPart.first.left = root;
			root.right = rightPart.first;
		}
		return currentPart;
	}

	public static Node convert(Node root) {
		if(root == null) return null;
		NodePair list = convertRec(root);
		// connect the first and last
		list.first.left = list.last;
		list.last.right = list.first;
		return list.first;
	}

	public static void main(String[] args) {
		Node root = buildExampleBT();
		Node head = convert(root);
		printDLL(head);		// print
		printDLLR(head);	// print reverse
	}

	public static void printDLL(Node head) {
		if(head == null) return;
		Node current = head;
		System.out.print("<-");
		while(current.right != head) {
			System.out.print(current.data + "<=>");
			current = current.right;
		}
		System.out.println(current.data + "->");
	}

	public static void printDLLR(Node head) {
		if(head == null) return;
		Node current = head;
		System.out.print("<-");
		while(current.left != head) {
			System.out.print(current.data + "<=>");
			current = current.left;
		}
		System.out.println(current.data + "->");
	}

	public static Node buildExampleBT() {
		Node node1 = new Node(8);
        Node node2 = new Node(4); node1.left = node2;
        Node node3 = new Node(1); node1.right = node3;
        Node node4 = new Node(9); node2.left = node4;
        Node node5 = new Node(2); node3.left = node5;
        Node node6 = new Node(3); node3.right = node6;
        return node1;
	}
}