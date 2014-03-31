/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together 
the nodes of the first two lists.
*/

import LCLibrary.*;

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
    /*
        Second Round
    */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode head = null, prev = null;
        while(l1 != null && l2 != null) {
            ListNode next;
            if(l1.val < l2.val) {
                next = l1;
                l1 = l1.next;
            }
            else {
                next = l2;
                l2 = l2.next;
            }
            if(head == null) {
                head = next;
                prev = next;
            }
            else {
                prev.next = next;
                prev = next;
            }
            next.next = null;
        }
        if(l1 != null) {
            prev.next = l1;
        }
        if(l2 != null) {
            prev.next = l2;
        }
        return head;
    }
}

class Main {
    public static void main(String[] args) {
    	ListNode l1 = Input.buildExampleList3();
    	ListNode l2 = Input.buildExampleList4();
        Solution solution = new Solution();
        Output.printList(solution.mergeTwoLists2(l1, l2));
    }
}