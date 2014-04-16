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

	public void initialList(String str) {
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			add(ch);
		}
	}

	public void displayList() {
		MyNode current = head;
		if(current == null)
			return;
		while(current != null) {
			System.out.print(current.ch);
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
	//--------------------------------------------------------------------//
}