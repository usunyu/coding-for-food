
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public ListNode partition(ListNode head, int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(head == null) return head;
        ListNode ptr = head, prev = null;
        ListNode ghead = null, gptr = null;
        while(ptr != null) {
            if(ptr.val >= x) {
                if(ghead == null) {
                    ghead = ptr;
                }
                else {
                    gptr.next = ptr;
                }
                gptr = ptr;
            }
            else {
                if(prev == null) {
                    head = ptr;
                }
                else {
                    prev.next = ptr;
                }
                prev = ptr;
            }
            ptr = ptr.next;
        }
        if(prev != null) prev.next = ghead;
        if(gptr != null) gptr.next = null;
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
        // ListNode node1 = new ListNode(4);
        // head.next = node1;
        // ListNode node2 = new ListNode(3);
        // node1.next = node2;
        // ListNode node3 = new ListNode(2);
        // node2.next = node3;
        // ListNode node4 = new ListNode(5);
        // node3.next = node4;
        // ListNode node5 = new ListNode(2);
        // node4.next = node5;
        print(solution.partition(head, 0));
    }
}
