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

    // time complexity : O(N)
    // space complexity : O(1)
    public ListNode removeNthFromEnd2(ListNode head, int n) {
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

class Main {
    public static ListNode constructList() {
        ListNode head = null, prev = null;
        for(int i = 0; i < 5; i++) {
            ListNode node = new ListNode(i + 1);
            if(i == 0) {
                head = node;
            }
            else {
                prev.next = node;
            }
            prev = node;
        }
        return head;
    }

    public static void print(ListNode head) {
        ListNode current = head;
        while(current != null) {
            System.out.print(current.val);
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = constructList();
        head = solution.removeNthFromEnd(head, 2);
        print(head);
    }
}