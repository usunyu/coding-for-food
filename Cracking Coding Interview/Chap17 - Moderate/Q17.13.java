/*
Consider a simple node-like data structure called BiNode, which has pointers to two other nodes. 
The data structure BiNode could be used to represent both a binary tree (where node1 is the left node and node2 
is the right node) or a doubly linked list (where node1 is the previous node and node2 is the next node). 

Implement a method to convert a binary search tree (implemented with BiNode) into a doubly linked list. 
The values should be kept in order and the operation should be performed in place (that is,on the original 
data structure).
*/

class BiNode {
	int data;
	BiNode node1;
	BiNode node2;

	public BiNode(int data) {
		this.data = data;
	}

	public String toString() {
		return "[" + data + "]";
	}
}

class Solution {
	public static BiNode convert(BiNode root) {
		BiNode left = null, right = null;
		if(root.node1 != null) {
			left = convert(root.node1);
		}
		BiNode head = root;
		if(left != null) {
			head = left;
			// go to tail
			while(left.node2 != null) {
				left = left.node2;
			}
		}
		if(root.node2 != null) {
			right = convert(root.node2);
		}
		if(left != null) {
			left.node2 = root;
		}
		root.node1 = left;
		if(right != null) {
			right.node1 = root;
		}
		root.node2 = right;
		return head;
	}

	public static BiNode convertToCircular(BiNode root) {
		if(root.node1 == null && root.node2 == null) {
			root.node1 = root;
			root.node2 = null;
			return root;
		}
		BiNode left = null, right = null;
		if(root.node1 != null) {
			left = convertToCircular(root.node1);
		}
		BiNode head = (left == null ? root : left);
		if(root.node2 != null) {
			right = convertToCircular(root.node2);
		}
		BiNode leftTail = null, rightTail = null;
		if(left != null) {
			leftTail = left.node1;
			left.node1 = null;
			leftTail.node2 = root;
			root.node1 = leftTail;
		}
		if(right != null) {
			rightTail = right.node1;
			right.node1 = root;
			root.node2 = right;
		}
		if(rightTail != null) {
			head.node1 = rightTail;
		}
		else {
			head.node1 = root;
		}
		return head;
	}

	public static void printAsTree(BiNode root, String spaces) {
		if (root == null) {
			System.out.println(spaces + "- null");
			return;
		}
		System.out.println(spaces + "- " + root.data);
		printAsTree(root.node1, spaces + "   ");
		printAsTree(root.node2, spaces + "   ");
	}

	public static BiNode createTree() {
		BiNode[] nodes = new BiNode[7];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new BiNode(i);
		}
		nodes[4].node1 = nodes[2];
		nodes[4].node2 = nodes[5];
		nodes[2].node1 = nodes[1];
		nodes[2].node2 = nodes[3];
		nodes[5].node2 = nodes[6];
		nodes[1].node1 = nodes[0];
		return nodes[4];
	}

	public static void printLinkedListTree(BiNode root) {
		for (BiNode node = root; node != null; node = node.node2) {
			if (node.node2 != null && node.node2.node1 != node) {
				System.out.print("inconsistent node: " + node);
			}
			System.out.print(node.data + "->");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		BiNode root = createTree();
		printAsTree(root, "");
		BiNode r = convert(root);
		printLinkedListTree(r);
	}
}
/*
	Second Round
*/
class BiNodePair {
	BiNode first, last;

	public String toString() {
		return "[" + first + ", " + last + "]";
	}
}

class Solution2 {
	public static void printAsTree(BiNode root, String spaces) {
		if (root == null) {
			System.out.println(spaces + "- null");
			return;
		}
		System.out.println(spaces + "- " + root.data);
		printAsTree(root.node1, spaces + "   ");
		printAsTree(root.node2, spaces + "   ");
	}

	public static BiNode createTree() {
		BiNode[] nodes = new BiNode[7];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new BiNode(i);
		}
		nodes[4].node1 = nodes[2];
		nodes[4].node2 = nodes[5];
		nodes[2].node1 = nodes[1];
		nodes[2].node2 = nodes[3];
		nodes[5].node2 = nodes[6];
		nodes[1].node1 = nodes[0];
		return nodes[4];
	}

	public static void printLinkedListTree(BiNode root) {
		for (BiNode node = root; node != null; node = node.node2) {
			if (node.node2 != null && node.node2.node1 != node) {
				System.out.print("inconsistent node: " + node);
			}
			System.out.print(node.data + "->");
		}
		System.out.println();
	}

	private static BiNodePair convertRec(BiNode root) {
		BiNodePair current = new BiNodePair();
		current.first = root;
		current.last = root;
		// merge
		if(root.node1 != null) {	// left
			BiNodePair leftPart = convertRec(root.node1);
			leftPart.last.node2 = current.first;
			current.first.node1 = leftPart.last;
			current.first = leftPart.first;
		}
		if(root.node2 != null) {	// right
			BiNodePair rightPart = convertRec(root.node2);
			rightPart.first.node1 = current.last;
			current.last.node2 = rightPart.first;
			current.last = rightPart.last;
		}
		// connect first and last
		// current.first.node1 = current.last;
		// current.last.node2 = current.first;
		return current;
	}

	// recursion
	public static BiNode convert(BiNode root) {
		if(root == null) return null;
		BiNodePair pair = convertRec(root);
		return pair.first;
	}

	// iteration, morris
	// runtime O(n), space O(1)
	public static BiNode convert2(BiNode root) {
		// inorder morris
		BiNode cur = root, head = null, prev = null;
		while(cur != null) {
			if(cur.node1 == null) {
				BiNode tmp = cur.node2;
				cur.node2 = null;	// clean
				// add node to list
				if(head == null) {
					head = cur;
					prev = cur;
				}
				else {
					prev.node2 = cur;
					cur.node1 = prev;
					prev = cur;
				}

				cur = tmp;
			}
			else {
				BiNode pred = cur.node1;
				while(pred.node2 != null) pred = pred.node2;
				// add trail
				pred.node2 = cur;
				BiNode tmp = cur.node1;
				cur.node1 = null;	// we have already add trail, remove the left part of tree
				cur = tmp;
			}
		}
		return head;
	}

	public static void main(String[] args) {
		BiNode root = createTree();
		printAsTree(root, "");
		// BiNode r = convert(root);
		// printLinkedListTree(r);
		BiNode m = convert2(root);
		printLinkedListTree(m);
	}
}


