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

	public void display() {
		if(root == null)
			return;
		int width = 63;
		int currentLevel = -1;
		LinkedList<MyNode> queue = new LinkedList<MyNode>();
		boolean hasNode = true;
		root.level = 0;
		queue.add(root);
		while(!queue.isEmpty()) {
			MyNode node = queue.poll();
			if(node.level > currentLevel) {
				if(!hasNode)
					break;
				System.out.println();
				hasNode = false;
				currentLevel++;
				width /= 2;
				for(int i = 0; i < width/2; i++)
					System.out.print("  ");
			}
			else {
				for(int i = 0; i < width; i++)
					System.out.print("  ");
			}
			
			System.out.print(node.value);

			if(node.leftChild != null) {
				node.leftChild.level = currentLevel + 1;
				queue.add(node.leftChild);
				hasNode = true;
			}
			else {
				MyNode nullNode = new MyNode(-1);
				nullNode.level = currentLevel + 1;
				queue.add(nullNode);
			}

			if(node.rightChild != null) {
				node.rightChild.level = currentLevel + 1;
				queue.add(node.rightChild);
				hasNode = true;
			}
			else {
				MyNode nullNode = new MyNode(-1);
				nullNode.level = currentLevel + 1;
				queue.add(nullNode);
			}
		}
		System.out.println();
	}
}

class Q4_3App {
	static int MAX = 10;
	static int[] sortedArray = new int[MAX];
	static MyBST bst = new MyBST();

	public static void balanceInsert() {
		balanceInsertRec(0, MAX - 1);
	}

	public static void balanceInsertRec(int start, int end) {
		if(start > end)
			return;
		int index = (start + end) / 2;
		int value = sortedArray[index];
		bst.insert(value);

		balanceInsertRec(start, index - 1);
		balanceInsertRec(index + 1, end);
	}

	public static void main(String[] args) {
		for(int i = 0; i < MAX; i++)
			sortedArray[i] = i;

		balanceInsert();
		bst.display();
	}
}






