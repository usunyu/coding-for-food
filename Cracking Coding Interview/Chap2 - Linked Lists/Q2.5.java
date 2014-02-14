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

class TempSum {
	public MyLinkedList resultList;
	public int carry;

	public TempSum() {
		resultList = new MyLinkedList();
		carry = 0;
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

	public void addFirst(int i) {
		MyNode node = new MyNode(i);
		if(head == null)
			head = node;
		else {
			node.next = head;
			head = node;
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
		// System.out.println();
	}

	public void displayReverse() {
		MyNode current = head;
		Stack<Integer> stack = new Stack<Integer>();
		if(current == null)
			return;
		while(current != null) {
			stack.push(current.i);
			current = current.next;
		}
		while(!stack.isEmpty()) {
			int t = stack.pop();
			System.out.print(t + " ");
		}
		// System.out.println();
	}

	public MyLinkedList addList(MyLinkedList list) {
		MyNode n1 = head;
		MyNode n2 = list.head;
		MyLinkedList result = new MyLinkedList();
		int carry = 0;
		while (n1 != null && n2 != null) {
			int i1 = n1.i;
			int i2 = n2.i;
			int i3 = i1 + i2 + carry;

			carry = i3 / 10;
			i3 = i3 - carry * 10;

			result.add(i3);

			n1 = n1.next;
			n2 = n2.next;
		}
		while(n1 != null) {
			int i1 = n1.i;
			int i3 = i1 + carry;

			carry = i3 / 10;
			i3 = i3 - carry * 10;

			result.add(i3);

			n1 = n1.next;
		}
		while(n2 != null) {
			int i2 = n2.i;
			int i3 = i2 + carry;

			carry = i3 / 10;
			i3 = i3 - carry * 10;

			result.add(i3);

			n2 = n2.next;
		}
		if(carry != 0)
			result.add(carry);
		return result;
	}

	// not in place
	public MyLinkedList reverse() {
		if(head == null)
			return null;
		MyLinkedList reverseList = new MyLinkedList();
		MyNode current = head;
		while(current != null) {
			reverseList.addFirst(current.i);
			current = current.next;
		}
		return reverseList;
	}

	public MyLinkedList addListForward(MyLinkedList list) {
		MyLinkedList number1 = this.reverse();
		MyLinkedList number2 = list.reverse();
		MyLinkedList result = number1.addList(number2);
		result = result.reverse();
		return result;
	}

	public MyLinkedList addListForward2(MyLinkedList list) {
		int length1 = length();
		int length2 = list.length();

		// make length of two list equal
		if(length1 < length2)
			padList(length2 - length1);
		else
			list.padList(length1 - length2);

		MyNode node1 = head;
		MyNode node2 = list.head;

		TempSum sum = addListForward2Rec(node1, node2);

		MyLinkedList resultList = sum.resultList;
		if(sum.carry != 0)
			resultList.addFirst(sum.carry);

		return resultList;
	}

	public TempSum addListForward2Rec(MyNode node1, MyNode node2) {
		if(node1 == null && node2 == null) {
			TempSum sum = new TempSum();
			return sum;
		}
		TempSum sum = addListForward2Rec(node1.next, node2.next);
		int value1 = node1.i;
		int value2 = node2.i;
		int value3 = value1 + value2 + sum.carry;
		int carry = value3 / 10;
		value3 %= 10;
		sum.resultList.addFirst(value3);
		sum.carry = carry;
		return sum;
	}

	public void padList(int n) {
		for(int i = 0; i < n; i++)
			addFirst(0);
	}

	public int length() {
		int l = 0;
		MyNode current = head;
		while(current != null) {
			current = current.next;
			l++;
		}
		return l;
	}
}

class Q2_5App {
	public static void main(String[] args) {
		MyLinkedList number1 = new MyLinkedList();
		MyLinkedList number2 = new MyLinkedList();
		number1.add(9);
		number1.add(9);
		number1.add(1);
		number1.add(3);
		number2.add(8);
		number2.add(5);
		number2.add(8);
		number1.displayReverse();
		System.out.print(" + ");
		number2.displayReverse();
		System.out.print(" = ");
		MyLinkedList number3 = number1.addList(number2);
		number3.displayReverse();
		System.out.println();
		number1.display();
		System.out.print(" + ");
		number2.display();
		System.out.print(" = ");
		number3 = number1.addListForward2(number2);
		number3.display();
		System.out.println();
	}
}

