import java.util.Stack;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public void reorderList(ListNode head) {
        if(head == null) return;
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode current = head;
        while(current != null) {
            stack.push(current);
            current = current.next;
        }
        current = head;
        ListNode last = null;
        int half = stack.size() / 2;
        while(stack.size() > half) {
            last = stack.pop();
            ListNode tmp = current.next;
            current.next = last;
            last.next = tmp;
            current = tmp;
        }
        last.next = null;
    }

    public void reorderList2(ListNode head) {
        if(head == null) return;
        // partition the list into 2 sublists of equal length
        ListNode fast = head, slow = head, prev = null;
        while(fast != null) {
            fast = fast.next;
            prev = slow;
            if(fast == null) {
                slow = slow.next;
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }
        if(prev != null) prev.next = null;
        // reverse the second sublist
        ListNode head2 = null;
        while(slow != null) {
            ListNode tmp = slow.next;
            slow.next = head2;
            head2 = slow;
            slow = tmp;
        }
        // merge the 2 sublists as required
        while(head2 != null) {
            ListNode tmp = head.next;
            ListNode tmp2 = head2.next;
            head.next = head2;
            head2.next = tmp;
            head = tmp;
            head2 = tmp2;
        }
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
        for(int i = 1; i <= 5; i++) {
            ListNode node = new ListNode(i);
            if(head == null) {
                head = node;
                prev = node;
            }
            else {
                prev.next = node;
                prev = node;
            }
        }
        print(head);
        solution.reorderList2(head);
        print(head);
    }
}
