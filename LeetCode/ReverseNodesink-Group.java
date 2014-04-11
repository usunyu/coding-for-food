/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
*/

import java.util.*;
import LCLibrary.*;

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

/*
    Second Round
*/
class Solution2 {
    private void reverse(ListNode first, ListNode end) {
        ListNode cur = first, pivot = null, finish = end.next;
        while(cur != finish) {
            ListNode tmp = cur.next;
            cur.next = pivot;
            pivot = cur;
            cur = tmp;
        }
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head, prev = null, last = null;
        while(cur != null) {
            ListNode first = cur, end = cur;
            int l = 0;
            while(l < k && cur != null) {
                end = cur;
                cur = cur.next;
                l++;
            }
            if(l == k) {    // reverse
                last = end.next;
                reverse(first, end);
                if(prev == null) head = end;
                else prev.next = end;
                first.next = last;
                prev = first;
            }
        }
        return head;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        ListNode head = Input.buildExampleList5();
        Output.printList(head);
        Output.printList(solution.reverseKGroup(head, 2));
    }
}
