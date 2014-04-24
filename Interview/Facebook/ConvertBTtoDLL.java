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
	
	public String toString() {
		return "[" + data + "]";
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
}

class Solution2 {
	// morris solution
	public static Node convert(Node root) {
		Node cur = root, head = null, prev = null, tmp, prec;
		while(cur != null) {
			if(cur.left == null) {
				tmp = cur.right;
				/* BUG: we cannot use this way to remove node from tree
				if(cur.right != null && cur.right.left == cur)
					cur.right.left = null;	// cut
				*/
				cur.right = null;	// clean
				if(head == null) {
					head = cur; prev = cur;
				}
				else {
					prev.right = cur;
					cur.left = prev;
					prev = cur;
				}
				cur = tmp;
			}
			else {
				prec = cur.left;
				while(prec.right != null) prec = prec.right;
				prec.right = cur;
				/*	BUG Fix: */
				tmp = cur.left;
				cur.left = null;	// need cut here
				cur = tmp;
			}
		}
		if(head != null) {
			head.left = prev;
			prev.right = head;
		}
		return head;
	}
}

class Main {
	public static void main(String[] args) {
		Node root = buildExampleBT();
		Node head = Solution2.convert(root);
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
		// Node node1 = new Node(8);
        // Node node2 = new Node(4); node1.left = node2;
        // Node node3 = new Node(1); node1.right = node3;
        // Node node4 = new Node(9); node2.left = node4;
        // Node node5 = new Node(2); node3.left = node5;
        // Node node6 = new Node(3); node3.right = node6;

        Node node4 = new Node(4);
        Node node2 = new Node(2); node4.left = node2;
        Node node1 = new Node(1); node2.left = node1;
        Node node3 = new Node(3); node2.right = node3;
        Node node0 = new Node(0); node1.left = node0;
        Node node5 = new Node(5); node4.right = node5;
        Node node6 = new Node(6); node5.right = node6;
        return node4;
	}
}

