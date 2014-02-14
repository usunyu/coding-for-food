
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode current = head.next, prev = head;
        while(current != null) {
            // extract the node first
            prev.next = current.next;
            current.next = null;
            // look for insert position
            ListNode ptr = head, pre = null;
            while(ptr != prev.next) {
                if(current.val < ptr.val) {
                    if(pre == null) {
                        head = current;
                    }
                    else {
                        pre.next = current;
                    }
                    current.next = ptr;
                    break;
                }
                pre = ptr;
                ptr = ptr.next;
            }
            if(ptr == prev.next) {
                prev.next = current;
                current.next = ptr;
                prev = current;
            }
            current = prev.next;
        }
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
        Solution solution = new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(3);
        node2.next = node3;
        print(node1);
        print(solution.insertionSortList(node1));
    }
}

