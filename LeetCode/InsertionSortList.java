/*
Sort a linked list using insertion sort.
*/

import LCLibrary.*;

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
    /*
        Second Round
    */
    public ListNode insertionSortList2(ListNode head) {
        ListNode head2 = null, cur = head, cur2 = null, prev = null, next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = null;
            // find insert place
            if(head2 == null) {
                head2 = cur;
            }
            else {
                cur2 = head2;
                prev = null;
                while(cur2 != null) {
                    if(cur2.val > cur.val) {
                        // insert
                        if(prev == null) {
                            head2 = cur;
                            head2.next = cur2;
                        }
                        else {
                            prev.next = cur;
                            cur.next = cur2;
                        }
                        break;
                    }
                    prev = cur2;
                    cur2 = cur2.next;
                }
                if(cur2 == null) {
                    prev.next = cur;
                }
            }
            cur = next;
        }
        return head2;
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
        ListNode head = Input.buildExampleList2();
        Output.printList(head);
        Output.printList(solution.insertionSortList2(head));
    }
}

