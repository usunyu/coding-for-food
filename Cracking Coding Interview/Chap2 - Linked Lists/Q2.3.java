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

	public void remove(MyNode node) {
		node.next = node.next.next;
	}

	public void removeMiddle() {
		if(head == null)
			return;
		MyNode n1 = head;
		MyNode n2 = head;
		while(true) {
			if(n2.next == null) {
				remove(n1);
				return;
			}
			n2 = n2.next;
			if(n2.next == null) {
				remove(n1);
				return;
			}
			n2 = n2.next;
			if(n2.next != null)
				n1 = n1.next;
		}
	}

	public void removeNode(MyNode node) {
		if(node == null && node.next != null)
			return;
		node.ch = node.next.ch;
		node.next = node.next.next;
	}
}

class Q2_3App {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add('a');
		myList.add('b');
		myList.add('c');
		myList.add('d');
		myList.add('e');
		myList.add('f');
		myList.add('g');
		myList.add('h');
		myList.add('i');
		myList.display();
		// myList.removeMiddle();
		// myList.display();
		MyNode node = myList.get(3);
		myList.removeNode(node);
		myList.display();
	}
}
