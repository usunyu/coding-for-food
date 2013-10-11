class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

class Solution {
	public ListNode swapPairs(ListNode head) {
		// Note: The Solution object is instantiated only once and is reused by each test case.
		if(head == null) {
			return null;
		}
		ListNode ptr = head;
		ListNode prev = null;
		while (ptr != null) {
			ListNode next = ptr.next;
			ListNode nnext = null;
			if (next != null) {
				nnext = next.next;
				
				next.next = ptr;
				ptr.next = nnext;
			}
			else {
				next = ptr;
			}
			
			if (ptr == head) {
				head = next;
			} else {
				prev.next = next;
			}
			
			prev = ptr;
			ptr = nnext;
		}
		return head;
	}
}

class Main {
	public static void print(ListNode ptr) {
		while (ptr != null) {
			System.out.print(ptr.val + " ");
			ptr = ptr.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		ListNode head = null;
		for (int i = 1; i <= 1; i++) {
			if (head == null) {
				head = new ListNode(i);
			} else {
				ListNode p = head;
				while (p.next != null) {
					p = p.next;
				}
				p.next = new ListNode(i);
			}
		}
		print(head);
		head = solution.swapPairs(head);
		print(head);
	}
}