class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ListNode head = null, current = null;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                if(head == null) {
                    head = l1;
                }
                else {
                    current.next = l1;
                }
                current = l1;
                l1 = l1.next;
            }
            else {
                if(head == null) {
                    head = l2;
                }
                else {
                    current.next = l2;
                }
                current = l2;
                l2 = l2.next;
            }
        }
        
        while(l1 != null) {
        	if(head == null) {
        		head = l1;
        	}
        	else {
        		current.next = l1;
        	}
            current = l1;
            l1 = l1.next;
        }
        
        while(l2 != null) {
            if(head == null) {
        		head = l2;
        	}
        	else {
        		current.next = l2;
        	}
            current = l2;
            l2 = l2.next;
        }
        if(current != null) {
        	current.next = null;
        }
        return head;
    }
}

class Main {

	public static void print(ListNode list) {
		while(list != null) {
			System.out.print(list.val + " ");
			list = list.next;
		}
		System.out.println();
	}

    public static void main(String[] args) {
    	ListNode l1 = new ListNode(2);
    	ListNode l2 = new ListNode(1);
        Solution solution = new Solution();
        ListNode merge = solution.mergeTwoLists(l1, l2);
        print(merge);
    }
}