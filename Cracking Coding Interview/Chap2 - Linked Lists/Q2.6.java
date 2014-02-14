import java.util.*; 

class MyNode {
	public char ch;
	public MyNode next;

	public MyNode(char ch) {
		this.ch = ch;
	}

	public void display() {
		System.out.print(ch + " ");
	}
}

class MyLinkedList {
	public MyNode head;

	public void add(char ch) {
		MyNode node = new MyNode(ch);
		if(head == null)
			head = node;
		else {
			MyNode current = head;
			while(current.next != null)
				current = current.next;
			current.next = node;
		}
	}

	public MyNode get(int k) {
		MyNode current = head;
		for(int i = 0; i < k; i++) {
			current = current.next;
		}
		return current;
	}

	public void display() {
		MyNode current = head;
		if(current == null)
			return;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}

	public void corrupt(int i) {
		if(head == null)
			return;
		MyNode current = head;
		MyNode temp = null;
		int k = 0;
		while(current.next != null) {
			if(k == i)
				temp = current;
			current = current.next;
			k++;
		}
		current.next = temp;
	}

	private boolean checkCorrupt(ArrayList<MyNode> arrayList, MyNode node) {
		boolean result = false;
		for(int i = 0; i < arrayList.size(); i++) {
			if(arrayList.get(i).next == node.next) {
				result = true;
				break;
			}
		}
		return result;
	}

	public MyNode findCorrupt() {
		ArrayList<MyNode> arrayList = new ArrayList<MyNode>();
		MyNode current = head;
		while(current.next != null) {
			if(checkCorrupt(arrayList, current)) {
				return current.next;
			}
			else {
				arrayList.add(current);
				current = current.next;
			}
		}
		return null;
	}

	public MyNode findCorrupt2() {
		if(head == null)
			return null;
		MyNode fast = head;
		MyNode slow = head;
		MyNode collide = null;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(fast == slow) {
				collide = fast;
				break;
			}
		}
		if(collide == null)
			return null;
		slow = head;
		while(true) {
			if(collide == slow)
				return collide;
			collide = collide.next;
			slow = slow.next;
		}
	}
}

class Q2_6App {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add('a');
		myList.add('b');
		myList.add('c');
		myList.add('d');
		myList.add('e');
		myList.display();
		myList.corrupt(2);
		// myList.display();
		MyNode corrupt = myList.findCorrupt2();
		System.out.print("Corrupt: ");
		corrupt.display();
		System.out.println();
	}
}


