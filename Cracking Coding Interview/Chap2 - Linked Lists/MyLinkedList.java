import java.util.Stack;
import java.util.ArrayList;

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

    public void addFirst(int i) {
		MyNode node = new MyNode(i);
		if(head == null)
			head = node;
		else {
			node.next = head;
			head = node;
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

	public void displayValReverseList() {
		MyNode current = head;
		Stack<Integer> stack = new Stack<Integer>();
		if(current == null)
			return;
		while(current != null) {
			stack.push(current.val);
			current = current.next;
		}
		while(!stack.isEmpty()) {
			int t = stack.pop();
			System.out.print(t + " ");
		}
		System.out.println();
	}

	public MyNode get(int k) {
		MyNode current = head;
		for(int i = 0; i < k; i++) {
			current = current.next;
		}
		return current;
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

	//------------------------------- Q2.3 -------------------------------//
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
	/*
		Second Round
	*/
	public boolean removeNode2(MyNode node) {
		// can not delete if the node is last, mark it as dumy
		if(node == null || node.next == null) return false;
		node.ch = node.next.ch;
		node.next = node.next.next;
		return true;
	}

	//------------------------------- Q2.4 -------------------------------//
	public void partition(int value) {
		if(head == null)
			return;
		MyNode current = head;
		while(current.next != null) {
			if(current.next.val < value) {
				MyNode temp = current.next;
				current.next = current.next.next;
				temp.next = head;
				head = temp;
			}
			else
				current = current.next;
		}
	}
	/*
		Second Round
	*/
	public void partition2(int value) {
		if(head == null) return;
		MyNode less = null, larger = null, head1 = null, head2 = null;
		MyNode current = head;
		while(current != null) {
			if(current.val < value) {
				if(head1 == null) {
					head1 = current;
					less = current;
				}
				else {
					less.next = current;
					less = current;
				}
			}
			else {
				if(head2 == null) {
					head2 = current;
					larger = current;
				}
				else {
					larger.next = current;
					larger = current;
				}
			}
			current = current.next;
		}
		less.next = head2;
		larger.next = null;
		head = head1;
	}

	public void partition3(int value) {
		if(head == null) return;
		MyNode current = head, prev = null;
		while(current != null) {
			MyNode tmp = current.next;
			if(current.val < value) {	// move to head
				if(prev != null) {
					prev.next = current.next;
					current.next = head;
					head = current;
				}
			}
			else prev = current;
			current = tmp;
		}
	}

	//------------------------------- Q2.5 -------------------------------//
	public MyLinkedList addList(MyLinkedList list) {
		MyNode n1 = head;
		MyNode n2 = list.head;
		MyLinkedList result = new MyLinkedList();
		int carry = 0;
		while (n1 != null && n2 != null) {
			int i1 = n1.val;
			int i2 = n2.val;
			int i3 = i1 + i2 + carry;

			carry = i3 / 10;
			i3 = i3 - carry * 10;

			result.add(i3);

			n1 = n1.next;
			n2 = n2.next;
		}
		while(n1 != null) {
			int i1 = n1.val;
			int i3 = i1 + carry;

			carry = i3 / 10;
			i3 = i3 - carry * 10;

			result.add(i3);

			n1 = n1.next;
		}
		while(n2 != null) {
			int i2 = n2.val;
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
			reverseList.addFirst(current.val);
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

	class TempSum {
		public MyLinkedList resultList;
		public int carry;

		public TempSum() {
			resultList = new MyLinkedList();
			carry = 0;
		}
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
		int value1 = node1.val;
		int value2 = node2.val;
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
	/*
		Second Round
	*/	
	public MyLinkedList addList2(MyLinkedList other) {
		MyNode pt1 = head, pt2 = other.head, head3 = null, cur = null;
		int carry = 0;
		while(pt1 != null || pt2 != null) {
			int add = (pt1 == null ? 0 : pt1.val) + 
				(pt2 == null ? 0 : pt2.val) + carry;
			MyNode node = new MyNode(add % 10);
			carry = add / 10;
			if(head3 == null) {
				head3 = node;
				cur = node;
			}
			else {
				cur.next = node;
				cur = node;
			}
			if(pt1 != null) pt1 = pt1.next;
			if(pt2 != null) pt2 = pt2.next;
		}
		MyLinkedList list = new MyLinkedList();
		list.head = head3;
		return list;
	}

	private int addListForward3Rec(MyNode head1, MyNode head2, MyNode head3) {
		if(head1 == null || head2 == null) return 0;
		int val = head1.val + head2.val;
		MyNode node = new MyNode(val % 10);
		head3.next = node;
		int carry = addListForward3Rec(head1.next, head2.next, node);
		// update with carry
		val = head1.val + head2.val + carry;
		node.val = val % 10;
		return val / 10;
	}

	public MyLinkedList addListForward3(MyLinkedList list) {
		int len1 = length(), len2 = list.length();
		int diff = Math.abs(len2 - len1);
		if(len1 > len2) list.padList(diff);
		else padList(diff);

		MyNode head1 = head, head2 = list.head, head3 = new MyNode(0);
		int carry = addListForward3Rec(head1, head2, head3);
		if(carry == 0) head3 = head3.next;
		else head3.val = carry;
		MyLinkedList ret = new MyLinkedList();
		ret.head = head3;
		return ret;
	}

	//------------------------------- Q2.6 -------------------------------//
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

	//------------------------------- Q2.7 -------------------------------//
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

	class MyResult {
		boolean isPalin;
		MyNode nextNode;

		public MyResult(MyNode node) {
			isPalin = true;
			nextNode = node;
		}
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
	/*
		Second Round
	*/
	class MyNodeWraper {
		MyNode node;
		
		public String toString() {
			return node.toString();
		}
	}
	
	private boolean isPalindrome3Rec(MyNode node, MyNodeWraper compare) {
		if(node == null) return true;
		boolean ret = isPalindrome3Rec(node.next, compare);
		if(!ret) return false;
		if(compare.node.ch != node.ch) return false;
		compare.node = compare.node.next;
		return true;
	}

	public boolean isPalindrome3() {
		MyNodeWraper wraper = new MyNodeWraper();
		wraper.node = head;
		return isPalindrome3Rec(head, wraper);
	}
}


