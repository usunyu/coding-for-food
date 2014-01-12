
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

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

class Main {
    public static void print(ListNode node) {
        while(node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = null, current = null;
        for(int i = 0; i < 5; i++) {
            ListNode node = new ListNode(i + 1);
            if(head == null) {
                head = node;
                current = head;
            }
            else {
                current.next = node;
                current = node;
            }
        }
        print(head);
        print(solution.rotateRight(head, 5));
    }
}
