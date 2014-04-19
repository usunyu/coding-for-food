public class MyLinkedList {
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

	public void initialList(String str) {
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			add(ch);
		}
	}

	public void initialList(int[] arr) {
		for(int i : arr) {
			add(i);
		}
	}

	public void displayChList() {
		MyNode current = head;
		if(current == null)
			return;
		while(current != null) {
			System.out.print(current.ch);
			current = current.next;
		}
		System.out.println();
	}

	public void displayValList() {
		MyNode current = head;
		if(current == null)
			return;
		while(current != null) {
			System.out.print(current.val + " ");
			current = current.next;
		}
		System.out.println();
	}

	//------------------------------- Q2.1 -------------------------------//
	public void removeDuplicate() {
		MyNode current = head;
		while(current != null) {
			MyNode runner = current;
			while(runner.next != null) {
				if(runner.next.ch == current.ch) {
					runner.next = runner.next.next;
				}
				else
					runner = runner.next;
			}
			current = current.next;
		}
	}
	/*
		Second Round
	*/
	public void removeDuplicate2() {
		MyNode current = head;
		while(current != null) {
			MyNode next = current.next, prev = current;
			while(next != null) {
				if(next.ch == current.ch) {	// need remove
					prev.next = next.next;
				}
				else {
					prev = next;
				}
				next = next.next;
			}
			current = current.next;
		}
	}

	//------------------------------- Q2.2 -------------------------------//
	public MyNode findKthLast(int k) {
		if(k <= 0)
			return null;
		MyNode current = head;
		while(true) {
			MyNode runner = current;
			int countLarger = 0;
			while(runner.next != null) {
				if(runner.next.val < current.val) {	// move to head
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
	/*
		Second Round
	*/
	public MyNode nthToLast2(int k) {
		MyNode fast = head;
		int i = 0;
		while(fast != null && i < k) {
			fast = fast.next;
			i++;
		}
		if(fast == null) return null;
		MyNode slow = head;
		while(fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow;
	}
	// recursion
	static int length = 0;

	private MyNode nthToLast2Rec(int k, MyNode node) {
		if(node == null) return null;
		MyNode n = nthToLast2Rec(k, node.next);
		length++;
		if(length == k) return node;
		else if(length > k) return n;
		else return null;
	}

	public MyNode nthToLast2Rec(int k) {
		return nthToLast2Rec(k, head);
	}
}