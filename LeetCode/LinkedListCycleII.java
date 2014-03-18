/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

import LCLibrary.*;

class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null) {
            fast = fast.next;
            if(fast == null) return null;
            fast = fast.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        if(fast == null) return null;
        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    /*
        Second Round
    */
    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head, slow = head;
        // check cycle
        while(fast != null) {
            fast = fast.next;
            if(fast != null) fast = fast.next;
            else return null;
            slow = slow.next;
            if(fast == slow) break;
        }
        if(fast == null) return null;
        // find cycle entrance
        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = head;
        System.out.println(solution.detectCycle2(head).toString());
    }
}