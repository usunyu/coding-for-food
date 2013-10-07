import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
    }
}

