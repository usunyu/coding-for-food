import java.util.*;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

class NodeInQ {
	int val;
	int index;

	NodeInQ(int x, int i) {
		val = x;
		index = i;
	}
}

class Solution {
	private ListNode encapsulate(ListNode node, int i, PriorityQueue<NodeInQ> priorityQ) {
		NodeInQ nodeInQ = null;
		if (node != null) {
			nodeInQ = new NodeInQ(node.val, i);
			priorityQ.add(nodeInQ);
			node = node.next;
		}
		return node;
	}

	// time complexity : NlogSize
	// space complexity : Size
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		// Note: The Solution object is instantiated only once and is reused by each test case.
		if(lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();
		PriorityQueue<NodeInQ> priorityQ = new PriorityQueue<NodeInQ>( size, new Comparator<NodeInQ>() {
					public int compare(NodeInQ n1, NodeInQ n2) {
						return n1.val - n2.val;
					}
				});
		ListNode head = null;
		ListNode[] pointer = new ListNode[size];
		// initial
		for (int i = 0; i < size; i++) {
			pointer[i] = lists.get(i);
			pointer[i] = encapsulate(pointer[i], i, priorityQ);
		}

		while (!priorityQ.isEmpty()) {
			NodeInQ nodeInQ = priorityQ.poll();
			int i = nodeInQ.index;
			int x = nodeInQ.val;
			ListNode temp = new ListNode(x);
			if (head == null) {
				head = temp;
			} else {
				ListNode p = head;
				while (p.next != null) {
					p = p.next;
				}
				p.next = temp;
			}
			pointer[i] = encapsulate(pointer[i], i, priorityQ);
		}
		return head;
	}

	// time complexity : NlogSize
	// space complexity : Size
	public ListNode mergeKLists2(ArrayList<ListNode> lists) {
		// Note: The Solution object is instantiated only once and is reused by each test case.
		if(lists == null || lists.size() == 0) {
			return null;
		}
		int size = lists.size();
		Comparator<ListNode> comparator = new Comparator<ListNode>() {
	        @Override
	        public int compare(ListNode n1, ListNode n2) {
	            if (n1.val < n2.val)
	                return -1;
	            else if (n1.val > n2.val)
	                return 1;
	            else
	                return 0;
	        }
	    };
	    PriorityQueue<ListNode> priorityQ = new PriorityQueue<ListNode>(size, comparator);

		ListNode head = null;
		ListNode current = null;
		for(int i = 0; i < size; i++) {
			if(lists.get(i) != null) {
				priorityQ.add(lists.get(i));
			}
		}
		while(!priorityQ.isEmpty()) {
			ListNode temp = priorityQ.poll();
			if(head == null) {
				head = temp;
				current = temp;
			}
			else {
				current.next = temp;
				current = temp;
			}
			if(current.next != null) {
				priorityQ.add(current.next);
			}
		}
		return head;
	}
}

class Main {
	public static void print(ListNode head) {
		while(head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 5;
		int length = 10;
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		for(int i =0; i < n; i++) {
			ListNode head = null ;
			for(int j = length; j > 0; j--) {
				ListNode temp = new ListNode(j);
				if(head == null) {
					head = temp;
				}
				else {
					temp.next = head;
					head = temp;
				}
			}
			lists.add(head);
		}
		ListNode merge = solution.mergeKLists2(lists);
		print(merge);
	}
}