/*
You are given two linked lists representing two non-negative numbers.
The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int carry = 0;
        Stack<Integer> stack = new Stack<Integer>();
        while(l1 != null && l2 != null) {
            int sum = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            stack.push(sum);
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 != null) {
            int sum = (l1.val + carry) % 10;
            carry = (l1.val + carry) / 10;
            stack.push(sum);
            l1 = l1.next;
        }
        while(l2 != null) {
            int sum = (l2.val + carry) % 10;
            carry = (l2.val + carry) / 10;
            stack.push(sum);
            l2 = l2.next;
        }
        if(carry != 0) {
            stack.push(carry);
        }
        ListNode head = null;
        while(!stack.isEmpty()) {
            int val = stack.pop();
            if(head == null) {
                head = new ListNode(val);
            }
            else {
                ListNode temp = new ListNode(val);
                temp.next = head;
                head = temp;
            }
        }
        return head;
    }

    /*
        Second Round
    */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2, head = null, prev = null;
        int carry = 0;
        // add
        while(p1 != null && p2 != null) {
            int val = p1.val + p2.val + carry;
            ListNode node = new ListNode(val % 10);
            carry = val / 10;
            if(head == null) {
                head = node;
                prev = node;
            }
            else {
                prev.next = node;
                prev = node;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // finish left
        while(p1 != null) {
            int val = p1.val + carry;
            ListNode node = new ListNode(val % 10);
            carry = val / 10;
            if(head == null) {
                head = node;
                prev = node;
            }
            else {
                prev.next = node;
                prev = node;
            }
            p1 = p1.next;
        }
        while(p2 != null) {
            int val = p2.val + carry;
            ListNode node = new ListNode(val % 10);
            carry = val / 10;
            if(head == null) {
                head = node;
                prev = node;
            }
            else {
                prev.next = node;
                prev = node;
            }
            p2 = p2.next;
        }
        if(carry != 0) {
            ListNode node = new ListNode(carry);
            if(prev != null) prev.next = node;
            else head = node;
        }
        return head;
    }
}

class Main {
    public static void print(ListNode head) {
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head1 = Input.buildExampleList();
        ListNode head2 = Input.buildExampleList2();

        print(head1);
        print(head2);
        print(solution.addTwoNumbers2(head1, head2));
    }
}

