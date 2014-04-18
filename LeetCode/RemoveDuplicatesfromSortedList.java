/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
*/

import LCLibrary.*;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(head == null) return head;
        ListNode pivot = head;
        ListNode current = pivot.next;
        while(current != null) {
            if(current.val != pivot.val) {
                pivot.next = current;
                pivot = current;
            }
            current = current.next;
            pivot.next = null;
        }
        return head;
    }
}
/*
    Second Round
*/
class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode prev = null, cur = head;
        while(cur != null) {
            ListNode tmp = cur.next;
            if(prev == null || prev.val != cur.val) {
                cur.next = null;
                if(prev != null) prev.next = cur;
                prev = cur;
            }
            cur = tmp;
        }
        return head;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        ListNode head = Input.buildExampleListWithDup();
        Output.printList(solution.deleteDuplicates(head));
    }
}
