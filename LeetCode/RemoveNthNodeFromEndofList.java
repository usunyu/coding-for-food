/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    // time complexity : O(N)
    // space complexity : O(N)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<ListNode> buffer = new ArrayList<ListNode>();
        // buffer the list
        ListNode current = head;
        int size = 0;
        while(current != null) {
            buffer.add(current);
            current = current.next;
            size++;
        }
        int index = size - n - 1;
        if(index < 0) { // remove head
            head = head.next;
        }
        else {  // remove middle
            current = buffer.get(index);
            current.next = current.next.next;
        }
        return head;
    }
}
class Solution2 {
    // time complexity : O(N)
    // space complexity : O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ListNode fast = head, slow = head;
        for(int i = 0 ; i < n; i++) {
            fast = fast.next;
        }
        if(fast == null) {
            return head.next;
        }
        else {
            while(fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
        }
        return head;
    }
}
/*
    Second Round
*/
class Solution3 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return null;
        ListNode fast = head, slow = head, prev = null;
        for(int i = 0; i < n && fast != null; i++) {
            fast = fast.next;
        }
        while(fast != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        if(prev == null)
            head = slow.next;
        else
            prev.next = slow.next;
        return head;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        ListNode head = Input.buildExampleList5();
        Output.printList(solution.removeNthFromEnd(head, 2));
    }
}