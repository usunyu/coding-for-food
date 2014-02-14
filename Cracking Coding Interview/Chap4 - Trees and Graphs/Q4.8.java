import java.util.*;

class MyNode {
	int value;
	MyNode leftChild;
	MyNode rightChild;
	int level;

	public MyNode(int value) {
		this.value = value;
	}
}

class MyBST {
	MyNode root;

	public void insert(int value) {
		MyNode node = new MyNode(value);
		if(root == null)
			root = node;
		else {
			MyNode current = root;
			while(true) {
				if(value > current.value) {
					if(current.rightChild != null)
						current = current.rightChild;
					else {
						current.rightChild = node;
						break;
					}
				}
				else {
					if(current.leftChild != null)
						current = current.leftChild;
					else {
						current.leftChild = node;
						break;
					}
				}
			}
		}
	}

	public boolean match(MyNode node1, MyNode node2) {
		if(node1 == null && node2 == null)
			return true;
		if(node1 == null || node2 == null)
			return false;
		if(node1.value != node2.value)
			return false;
		boolean leftMatch = match(node1.leftChild, node2.leftChild);
		boolean rightMatch = match(node1.rightChild, node2.rightChild);
		return leftMatch && rightMatch;
	}

	public boolean isSubtree(MyBST tree) {
		if(tree == null)
			return true;
		MyNode subRoot = tree.root;
		// traversal the local tree
		LinkedList<MyNode> queue = new LinkedList<MyNode>();
		queue.add(root);
		while(!queue.isEmpty()) {
			MyNode current = queue.poll();
			if(current.leftChild != null)
				queue.add(current.leftChild);
			if(current.rightChild != null)
				queue.add(current.rightChild);
			// match sub tree
			if(match(current, subRoot))
				return true;
		}
		return false;
	}
}

class Q4_8App {
	public static void main(String[] args) {
		MyBST T1 = new MyBST();
		T1.insert(10);
		T1.insert(5);
		T1.insert(15);
		T1.insert(3);
		T1.insert(7);
		T1.insert(13);
		T1.insert(17);
		T1.insert(1);
		T1.insert(6);
		T1.insert(8);

		MyBST T2 = new MyBST();
		T2.insert(7);
		T2.insert(6);
		T2.insert(8);
		// T2.insert(9);

		System.out.println("T2 is" + (T1.isSubtree(T2) ? "" : " not") + " sub-tree of T1");
	}
}


