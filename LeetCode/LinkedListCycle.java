
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null) {
            fast = fast.next;
            if(fast != null) fast = fast.next;
            else return false;
            slow = slow.next;
            if(slow == fast) return true;
        }
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        // head.next = head;
        System.out.println(solution.hasCycle(head));
    }
}