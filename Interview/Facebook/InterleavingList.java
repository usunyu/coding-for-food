/*
将1->2->3->4->5->6->7 变成 1->7->2->6->3->5->4，不能用额外空间循环语句
http://e.v-get.com/tech/20131026/154939950.html
*/

class ListNode {
	int value;
	ListNode next;

	public ListNode(int val) {
		value = val;
	}
}

class Main {
	public static ListNode buildList() {
		ListNode head = null, prev = null;
		for(int i = 0; i < 8; i++) {
			ListNode node = new ListNode(i + 1);
			if(head == null) {
				head = node;
				prev = node;
			}
			else {
				prev.next = node;
				prev = node;
			}
		}
		return head;
	}

	public static void printList(ListNode head) {
		while(head != null) {
			if(head.next != null)
				System.out.print(head.value + " -> ");
			else
				System.out.println(head.value);
			head = head.next;
		}
	}

	// 1->2->3->4->5->6->7 ==> 1->7->2->6->3->5->4
	public static ListNode interleavingList(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode left = head, right, prev;
		while(left != null) {
			right = left; prev = null;
			// find last node
			while(right.next != null) {
				prev = right;
				right = right.next;
			}
			// insert
			if(prev == null)
				break;
			prev.next = null;
			right.next = left.next;
			left.next = right;
			left = right.next;
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode head = buildList();
		printList(head);
		head = interleavingList(head);
		printList(head);
	}
}



