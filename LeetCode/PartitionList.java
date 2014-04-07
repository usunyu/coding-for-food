/*
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
*/

import LCLibrary.*;

class Solution {
    public ListNode partition(ListNode head, int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(head == null) return head;
        ListNode ptr = head, prev = null;
        ListNode ghead = null, gptr = null;
        while(ptr != null) {
            if(ptr.val >= x) {
                if(ghead == null) {
                    ghead = ptr;
                }
                else {
                    gptr.next = ptr;
                }
                gptr = ptr;
            }
            else {
                if(prev == null) {
                    head = ptr;
                }
                else {
                    prev.next = ptr;
                }
                prev = ptr;
            }
            ptr = ptr.next;
        }
        if(prev != null) prev.next = ghead;
        if(gptr != null) gptr.next = null;
        return head;
    }
}

/*
    Second Round
*/
class Solution2 {
    public ListNode partition(ListNode head, int x) {
        ListNode lessHead = null, lessPrev = null, greaterHead = null, greaterPrev = null, ptr = head;
        while(ptr != null) {
            ListNode next = ptr.next;
            ptr.next = null;    // clean
            if(ptr.val < x) {
                if(lessHead == null) {
                    lessHead = ptr; lessPrev = ptr;
                }
                else {
                    lessPrev.next = ptr; lessPrev = ptr;
                }
            }
            else {
                if(greaterHead == null) {
                    greaterHead = ptr; greaterPrev = ptr;
                }
                else {
                    greaterPrev.next = ptr; greaterPrev = ptr;
                }
            }
            ptr = next;
        }
        // connect
        if(lessPrev == null) return greaterHead;
        lessPrev.next = greaterHead;
        return lessHead;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        ListNode head = Input.buildExampleList5();
        Output.printList(solution.partition(head, 3));
    }
}
