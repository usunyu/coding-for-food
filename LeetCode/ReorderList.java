/*
Given a singly linked list L: L0->L1->...->Ln-1->Ln,
reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->...

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
*/

import java.util.Stack;
import LCLibrary.*;

class Solution {
    public void reorderList(ListNode head) {
        if(head == null) return;
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode current = head;
        while(current != null) {
            stack.push(current);
            current = current.next;
        }
        current = head;
        ListNode last = null;
        int half = stack.size() / 2;
        while(stack.size() > half) {
            last = stack.pop();
            ListNode tmp = current.next;
            current.next = last;
            last.next = tmp;
            current = tmp;
        }
        last.next = null;
    }
}

class Solution2 {
    public void reorderList(ListNode head) {
        if(head == null) return;
        // partition the list into 2 sublists of equal length
        ListNode fast = head, slow = head, prev = null;
        while(fast != null) {
            fast = fast.next;
            prev = slow;
            if(fast == null) {
                slow = slow.next;
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }
        if(prev != null) prev.next = null;
        // reverse the second sublist
        ListNode head2 = null;
        while(slow != null) {
            ListNode tmp = slow.next;
            slow.next = head2;
            head2 = slow;
            slow = tmp;
        }
        // merge the 2 sublists as required
        while(head2 != null) {
            ListNode tmp = head.next;
            ListNode tmp2 = head2.next;
            head.next = head2;
            head2.next = tmp;
            head = tmp;
            head2 = tmp2;
        }
    }
}

/*
    Second Round
*/
class Solution3 {
    public void reorderList(ListNode head) {
        if(head == null) return;
        // partition list
        ListNode slow = head, fast = head;
        while(fast != null) {
            if(fast.next == null) break;
            fast = fast.next;
            if(fast.next == null) break;
            fast = fast.next;
            slow = slow.next;
        }
        ListNode head1 = head, head2 = slow.next;
        slow.next = null;   // break
        // reverse second part
        ListNode cur = head2;
        while(cur != null) {
            ListNode tmp = cur.next;
            cur.next = null;    // clean
            if(head2 != cur) {
                cur.next = head2;
                head2 = cur;
            }
            cur = tmp;
        }
        // reorder list
        while(head1 != null && head2 != null) {
            ListNode tmp1 = head1.next, tmp2 = head2.next;
            head1.next = head2;
            head2.next = tmp1;
            head1 = tmp1; head2 = tmp2;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        ListNode head = Input.buildExampleList5();
        Output.printList(head);
        solution.reorderList(head);
        Output.printList(head);
    }
}
