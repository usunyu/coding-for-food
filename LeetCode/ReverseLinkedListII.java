/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
*/

import LCLibrary.*;

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode vHead = new ListNode(0);
        vHead.next = head;
        ListNode left = vHead;
        for(int i = 0; i < m - 1; i++) left = left.next;
        ListNode current = left.next, prev = null;
        for(int i = m; i <= n; i++) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        ListNode right = left.next;
        left.next = prev;
        right.next = current;
        return vHead.next;
    }
}

/*
    Second Round
*/
class Solution2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode cur = head, prev = null;
        // locate
        for(int i = 0; i < m - 1; i++) {
            prev = cur;
            cur = cur.next;
        }
        // reverse
        ListNode head2 = cur, tmp = null, last = null;
        for(int i = m; i <= n; i++) {
            tmp = cur.next;
            if(head2 != cur) {
                cur.next = head2;
                head2 = cur;
            }
            else
                last = cur;
            cur = tmp;
        }
        // connect
        if(last != null) last.next = tmp;
        if(prev == null) head = head2;
        else prev.next = head2;
        return head;
    }
}

class Main {
    public static void print(ListNode node) {
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        ListNode head = null, prev = null;
        for(int i = 0; i < 3; i++) {
            ListNode node = new ListNode(i + 1);
            if(head == null) head = node;
            else {
                prev.next = node;
            }
            prev = node;
        }
        print(head);
        print(solution.reverseBetween(head, 2, 3));
    }
}

