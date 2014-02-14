class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(head == null) return head;
        ListNode pivot = head;
        ListNode current = pivot.next;
        while(current != null) {
            if(current.val != pivot.val) {
                pivot.next = current;
                pivot = current;
            }
            current = current.next;
            pivot.next = null;
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
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        ListNode node4 = new ListNode(3);
        node3.next = node4;
        ListNode node5 = new ListNode(4);
        node4.next = node5;
        ListNode node6 = new ListNode(5);
        node5.next = node6;
        ListNode node7 = new ListNode(5);
        node6.next = node7;
        print(solution.deleteDuplicates(head));
    }
}
