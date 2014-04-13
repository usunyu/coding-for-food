/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
*/

import LCLibrary.*;

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
/*
	Second Round
*/
class Solution2 {
	private void swap(ListNode node1, ListNode node2) {
        ListNode tmp = node2.next;
        node2.next = node1;
        node1.next = tmp;
    }
    
    public ListNode swapPairs(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while(cur != null) {
            ListNode n1 = cur;
            cur = cur.next;
            if(cur != null) {
                ListNode n2 = cur, tmp = cur.next;
                swap(n1, n2);
                if(prev == null) head = n2;
                else prev.next = n2;
                prev = n1;
                cur = tmp;
            }
        }
        return head;
    }
}

class Solution3 {
    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next==null)    return head;
        ListNode dum = new ListNode(0);
        ListNode prev = dum, p = head;
        while (p!=null && p.next!=null){
            ListNode pp = p.next, next = pp.next;
            pp.next = p;
            p.next = next;
            prev.next = pp;
            prev = p;
            p = next;
        }
        return dum.next;
    }
}

class Main {
	public static void main(String[] args) {
		Solution2 solution = new Solution2();
		ListNode head = Input.buildExampleList5();
		Output.printList(head);
		head = solution.swapPairs(head);
		Output.printList(head);
	}
}