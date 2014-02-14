import java.util.*; 

class MyNode {
	public char ch;
	public MyNode next;

	public MyNode(char ch) {
		this.ch = ch;
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
}

class Q2_1App {

	public static void initialList(String str, LinkedList<Character> list) {
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			list.add(ch);
		}
	}

	public static void initialList(String str, MyLinkedList list) {
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			list.add(ch);
		}
	}

	public static void displayList(LinkedList<Character> list) {
		for(int i = 0; i < list.size(); i++)
			System.out.print(list.get(i));
		System.out.println();
	}

	public static void displayList(MyLinkedList list) {
		MyNode current = list.head;
		if(current == null)
			return;
		while(current != null) {
			System.out.print(current.ch);
			current = current.next;
		}
		System.out.println();
	}

	public static void removeDuplicate(LinkedList<Character> list) {
		for(int i = 0; i < list.size(); i++)
			for(int j = i + 1; j < list.size(); j++)
				if(list.get(i) == list.get(j))
					list.remove(j--);
	}

	public static void main(String[] args) {
		String testStr = "ABSNDAASXK";
		System.out.println("Use LinkedList:");
		LinkedList<Character> list = new LinkedList<Character>();
		initialList(testStr, list);
		displayList(list);
		removeDuplicate(list);
		displayList(list);
		System.out.println("Use My LinkedList:");
		MyLinkedList myList = new MyLinkedList();
		initialList(testStr, myList);
		displayList(myList);
		myList.removeDuplicate();
		displayList(myList);
	}
}