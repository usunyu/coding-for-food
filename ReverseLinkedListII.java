
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

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

