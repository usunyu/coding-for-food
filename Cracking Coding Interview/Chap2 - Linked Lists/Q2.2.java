import java.util.*; 

class MyNode {
	public int i;
	public MyNode next;

	public MyNode(int i) {
		this.i = i;
	}

	public void display() {
		System.out.println(i);
	}
}

class IntWrap {
	public int value = 0;
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

	public void display() {
		MyNode current = head;
		if(current == null)
			return;
		while(current != null) {
			System.out.print(current.i + " ");
			current = current.next;
		}
		System.out.println();
	}

	public MyNode findKthLast(int k) {
		if(k <= 0)
			return null;
		MyNode current = head;
		while(true) {
			MyNode runner = current;
			int countLarger = 0;
			while(runner.next != null) {
				if(runner.next.i < current.i) {	// move to head
					MyNode temp = runner.next;	// tempory store
					runner.next = runner.next.next;
					temp.next = head;
					head = temp;
				}
				else {
					countLarger++;
					runner = runner.next;
				}
			}
			// display();
			if(countLarger == k - 1)	// find it
				return current;
			else if(countLarger > (k - 1)) {	// in the right side
				current = current.next;
				head = current;
			}
			else {	// in the left side
				if(head == current)	// no more on left
					return null;
				current.next = null;
				current = head;
				k = k - countLarger;
				if(k < 0)
					return null;
			}
		}
	}

	public MyNode nthToLastR(int k) {
		IntWrap intWrap = new IntWrap();
		return nthToLastRec(head, k, intWrap);
	}

	private MyNode nthToLastRec(MyNode node, int k, IntWrap intWrap) {
		if(node == null)
			return null;
		MyNode result = nthToLastRec(node.next, k, intWrap);
		intWrap.value++;
		if(k == intWrap.value)
			return node;
		return result;
	}

	public MyNode nthToLast(int k) {
		if(head == null)
			return null;
		MyNode n1 = head;
		MyNode n2 = head;
		// move n2 to k
		for(int i = 0; i < k; i++) {
			if(n2 == null) return null;
			n2 = n2.next;
		}
		if(n2 == null) return null;
		// move n1 and n2 simultaneous
		while(n2 != null) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}
}

class Q2_2App {
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
		System.out.print("nthToLast:\t");
		MyNode node = myList.nthToLast(6);
		if(node != null)
			node.display();
		else
			System.err.println("Not find!");
		System.out.print("nthToLastR:\t");
		node = myList.nthToLastR(6);
		if(node != null)
			node.display();
		else
			System.err.println("Not find!");
		System.out.print("findKthLast:\t");
		node = myList.findKthLast(6);
		if(node != null)
			node.display();
		else
			System.err.println("Not find!");
	}
}



