
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null) {
            fast = fast.next;
            if(fast == null) return null;
            fast = fast.next;
            slow = slow.next;
            if(fast == slow) break;
        }
        if(fast == null) return null;
        fast = head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        head.next = head;
        System.out.println(solution.detectCycle(head));
    }
}