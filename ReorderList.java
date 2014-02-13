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
        for(int i = 1; i <= 4; i++) {
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
        solution.reorderList(head);
        print(head);
    }
}
