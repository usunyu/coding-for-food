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

class MyResult {
	boolean isPalin;
	MyNode nextNode;

	public MyResult(MyNode node) {
		isPalin = true;
		nextNode = node;
	}
}

class MyLinkedList {
	public MyNode head;
	int length;

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
		length++;
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

	public boolean isPalindrome() {
		Stack<Character> stack = new Stack<Character>();
		MyNode current = head;
		while(current != null) {
			stack.push(current.ch);
			current = current.next;
		}
		current = head;
		boolean isPalin = true;
		while(current != null) {
			if(current.ch != stack.pop()) {
				isPalin = false;
				break;
			}
			current = current.next;
		}
		return isPalin;
	}

	public boolean isPalindrome2() {
		return isPalindrome2Rec(head, length).isPalin;
	}

	private MyResult isPalindrome2Rec(MyNode node, int l) {
		if(l == 1)
			return new MyResult(node.next);
		if(l == 0)
			return new MyResult(node);
		MyResult result = isPalindrome2Rec(node.next, l - 2);
		if(result.nextNode.ch != node.ch)
			result.isPalin = false;
		result.nextNode = result.nextNode.next;
		return result;
	}
}

class Q2_7App {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add('a');
		myList.add('b');
		myList.add('c');
		myList.add('b');
		myList.add('a');
		// myList.add('a');
		myList.display();
		
		if(myList.isPalindrome2())
			System.out.println("This string is palindrome.");
		else
			System.out.println("This string is not palindrome.");
	}
}
