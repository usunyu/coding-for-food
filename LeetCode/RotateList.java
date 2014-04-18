/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/

import LCLibrary.*;

class Solution {
    public ListNode rotateRight(ListNode head, int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(n == 0 || head == null || head.next == null) return head;
        ListNode fast = head, slow = head;
        while(fast.next != head || n > 0) {
            if(n > 0) {
                n--;
            }
            else {
                slow = slow.next;
            }
            fast = fast.next;
            if(fast.next == null) fast.next = head;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        return head2;
    }
}
/*
    Second Round
*/
class Solution2 {
    public ListNode rotateRight(ListNode head, int n) {
        if(head == null || n == 0) return head;
        // locate
        ListNode cur = head, head2 = head, prev = null;
        int length = 0;
        for(int i = 0; i < n - 1 && cur != null; i++) {
            cur = cur.next;
            length++;
        }
        if(cur != null) {   // we have enough node
            while(cur.next != null) {
                prev = head2;
                cur = cur.next;
                head2 = head2.next;
            }
        }
        else {  // reduce
            return rotateRight(head, n % length);
        }
        // rotate
        if(prev != null) {
            prev.next = null;
            cur.next = head;
        }
        return head2;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        ListNode head = Input.buildExampleList();
        Output.printList(head);
        Output.printList(solution.rotateRight(head, 2));
    }
}
