class BiNode {
	int val;
	BiNode node1;
	BiNode node2;

	public BiNode(int val) {
		this.val = val;
	}
}

class Main {
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

	public static void print(BiNode node) {
		while(node != null) {
			System.out.print(node.val + " ");
			node = node.node2;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		BiNode root = new BiNode(10);
		BiNode node1 = new BiNode(5);
		BiNode node2 = new BiNode(15);
		root.node1 = node1;
		root.node2 = node2;
		BiNode node3 = new BiNode(3);
		BiNode node4 = new BiNode(7);
		node1.node1 = node3;
		node1.node2 = node4;
		root = convertToCircular(root);
		root.node1 = null;
		print(root);
	}
}