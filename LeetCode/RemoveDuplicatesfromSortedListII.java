/*
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
*/
import LCLibrary.*;

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(head == null) return head;
        ListNode current = head;
        ListNode prev = null;
        while(current != null) {
            ListNode next = current.next;
            if(next != null) {
                if(current.val == next.val) {   // duplicate
                    while(current != null) {    // filter duplicate
                        next = current.next;
                        if(next != null && current.val != next.val) {
                            break;
                        }
                        current = next;
                    }
                    if(prev != null) {
                        prev.next = next;
                    }
                    else {
                        head = next;
                    }
                }
                else {  // not duplicate
                    prev = current;
                }
            }
            current = next;
        }
        return head;
    }
}

/*
    Second Round
*/
class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode head2 = null, prev = null, cur = head;
        while(cur != null) {
            if(cur.next == null || cur.next.val != cur.val) {   // insert
                ListNode tmp = cur.next;
                cur.next = null;    // clean
                if(head2 == null) {
                    head2 = cur;
                    prev = cur;
                }
                else {
                    prev.next = cur;
                    prev = cur;
                }
                cur = tmp;
            }
            else {
                int val = cur.val;
                while(cur != null && cur.val == val) {
                    cur = cur.next;
                }
            }
        }
        return head2;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        ListNode head = Input.buildExampleListWithDup();
        Output.printList(solution.deleteDuplicates(head));
    }
}
