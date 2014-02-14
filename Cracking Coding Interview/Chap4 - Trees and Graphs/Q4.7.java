import java.util.*;

class MyNode {
	int value;
	MyNode leftChild;
	MyNode rightChild;
	MyNode parent;

	public MyNode(int value) {
		this.value = value;
	}

	public void display() {
		System.out.print(value + " ");
	}
}

class MyBST {
	MyNode root;

	public void insert(MyNode node) {
		// MyNode node = new MyNode(value);
		if(root == null)
			root = node;
		else {
			MyNode current = root;
			while(true) {
				if(node.value > current.value) {
					if(current.rightChild != null)
						current = current.rightChild;
					else {
						current.rightChild = node;
						node.parent = current;
						break;
					}
				}
				else {
					if(current.leftChild != null)
						current = current.leftChild;
					else {
						current.leftChild = node;
						node.parent = current;
						break;
					}
				}
			}
		}
	}

	private int getHeight(MyNode node) {
		int height = 0;
		while(node != null) {
			node = node.parent;
			height++;
		}
		return height;
	}

	public MyNode common(MyNode node1, MyNode node2) {
		int height1 = getHeight(node1);
		int height2 = getHeight(node2);
		MyNode p1 = node1, p2 = node2;
		if(height1 > height2) {
			for(int i = 0; i < height1 - height2; i++)
				p1 = p1.parent;
		}
		else {
			for(int i = 0; i < height2 - height1; i++)
				p2 = p2.parent;
		}
		while(p1 != p2) {
			p1 = p1.parent;
			p2 = p2.parent;
		}

		return p1;
	}

	public MyNode common2(MyNode node1, MyNode node2) {
		return common2Rec(root, node1, node2);
	}

	// 1. if node under this root return node
	// 2. if no node node under this root return null
	// 3. if left return and right return not same ane not null, find common
	// 4. always return the non-null node
	public MyNode common2Rec(MyNode root, MyNode p, MyNode q) {
		if(root == null)
			return null;

		if(root == p && root == q)
			return root;

		MyNode left = common2Rec(root.leftChild, p, q);
		MyNode right = common2Rec(root.rightChild, p, q);

		if(left != null && right != null && p != q)
			return root;

		if(left == null && right == null) {
			if(root == p || root == q)
				return root;
			else
				return null;
		}

		if(left == null) {
			if(root == p || root == q)
				return root;
			return right;
		}
		else {
			if(root == p || root == q)
				return root;
			return left;
		}
	}
}

class Q4_7App {
	public static void main(String[] args) {
		MyBST bst = new MyBST();
		MyNode node1 = new MyNode(10);
		bst.insert(node1);
		MyNode node2 = new MyNode(5);
		bst.insert(node2);
		MyNode node3 = new MyNode(15);
		bst.insert(node3);
		MyNode node4 = new MyNode(3);
		bst.insert(node4);
		MyNode node5 = new MyNode(7);
		bst.insert(node5);
		MyNode node6 = new MyNode(1);
		bst.insert(node6);
		MyNode node7 = new MyNode(6);
		bst.insert(node7);
		MyNode node8 = new MyNode(8);
		bst.insert(node8);
		MyNode node9 = new MyNode(13);
		bst.insert(node9);
		MyNode node10 = new MyNode(17);
		bst.insert(node10);

		System.out.println("common of (" + node1.value + ") and (" + node10.value + ") is (" + bst.common(node1, node10).value + ")");
		System.out.println("common of (" + node4.value + ") and (" + node5.value + ") is (" + bst.common(node4, node5).value + ")");
		System.out.println("common of (" + node5.value + ") and (" + node9.value + ") is (" + bst.common(node5, node9).value + ")");
		System.out.println();
		System.out.println("common of (" + node1.value + ") and (" + node10.value + ") is (" + bst.common2(node1, node10).value + ")");
		System.out.println("common of (" + node4.value + ") and (" + node5.value + ") is (" + bst.common2(node4, node5).value + ")");
		System.out.println("common of (" + node5.value + ") and (" + node9.value + ") is (" + bst.common2(node5, node9).value + ")");
	}
}




