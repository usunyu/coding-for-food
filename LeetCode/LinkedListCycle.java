/*
Given a linked list, determine if it has a cycle in it.

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
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null) {
            fast = fast.next;
            if(fast != null) fast = fast.next;
            else return false;
            slow = slow.next;
            if(slow == fast) return true;
        }
        return false;
    }
    /*
        Second Round
    */
    public boolean hasCycle2(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null) {
            fast = fast.next;
            if(fast != null)
                fast = fast.next;
            else return false;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        // head.next = head;
        System.out.println(solution.hasCycle2(head));
    }
}