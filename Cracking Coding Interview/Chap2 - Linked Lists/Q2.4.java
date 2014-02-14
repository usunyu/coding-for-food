import java.util.*;

class MyNode {
	public int i;
	public MyNode next;

	public MyNode(int i) {
		this.i = i;
	}

	public void display() {
		System.out.print(i + " ");
	}
}

class MyLinkedList {
	public MyNode head;

	public void add(int i) {
		MyNode node = new MyNode(i);
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
			if(current == null)
				break;
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

	public void partition(int value) {
		if(head == null)
			return;
		MyNode current = head;
		while(current.next != null) {
			if(current.next.i < value) {
				MyNode temp = current.next;
				current.next = current.next.next;
				temp.next = head;
				head = temp;
			}
			else
				current = current.next;
		}
	}
}

class Q2_4App {

	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add(48);
		myList.add(32);
		myList.add(14);
		myList.add(87);
		myList.add(73);
		myList.add(23);
		myList.add(42);
		myList.add(54);
		myList.add(43);
		myList.add(76);
		myList.add(55);
		myList.add(34);
		myList.display();
		myList.partition(50);
		myList.display();
	}
}


