import java.util.*;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head, retHead = null, retCurrent = null;
        while(current != null) {
            Stack<ListNode> group = new Stack<ListNode>();
            ListNode backUp = current;
            for(int i = 0; i < k && current != null; i++) {
                group.push(current);
                current = current.next;
            }
            // size is enough for reverse
            if(group.size() == k) {
                while(!group.isEmpty()) {
                    ListNode local = group.pop();
                    local.next = null;
                    if(retHead == null) {
                        retHead = local;
                        retCurrent = local;
                    }
                    else {
                        retCurrent.next = local;
                        retCurrent = local;
                    }
                }
            }
            else {  // size is not enough for reverse
                if(retHead == null) {
                    retHead = backUp;
                }
                else {
                    retCurrent.next = backUp;
                }
            }
        }
        return retHead;
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
        for(int i = 0; i < 2; i++) {
            ListNode node = new ListNode(i + 1);
            if(head == null) {
                head = node;
                current = node;
            }
            else {
                current.next = node;
                current = node;
            }
        }
        print(head);
        print(solution.reverseKGroup(head, 2));
    }
}
