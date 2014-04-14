/*
Sort a linked list in O(n log n) time using constant space complexity.
*/

import LCLibrary.*;

class Solution {
    private ListNode sortList(ListNode left, ListNode right, ListNode flag) {
        ListNode current = left.next, pivot = left, end = right.next, prev = left;
        while(current != end) {
            if(current.val < pivot.val) {
                ListNode tmp = current.next;
                prev.next = current.next;
                if(flag != null) {
                    flag.next = current;
                }
                current.next = left;
                left = current;
                current = tmp;
            }
            else {
                prev = current;
                current = current.next;
            }
        }
        if(left != pivot) left = sortList(left, pivot, flag);
        if(pivot.next != prev && pivot.next != null) sortList(pivot.next, prev, pivot);
        return left;
    }
    
    // quick sort, not stable for O(nlogn)
    // Time Limit Exceeded
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode tail = null, current = head;
        while(current != null) {
            tail = current;
            current = current.next;
        }
        return sortList(head, tail, null);
    }
}

class Solution2 {
    private ListNode sortList(int length) {
        if(length == 1) {
            ListNode tmp = HEAD;
            HEAD = HEAD.next;   // trick part, with doing this, no need to traverse through to get middle
            tmp.next = null;    // for merge
            return tmp;
        }
        ListNode left = sortList(length / 2);
        ListNode right = sortList(length - length / 2);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dump = new ListNode(-1);
        ListNode current = dump;
        while(left != null || right != null) {
            int leftValue = (left == null ? Integer.MAX_VALUE : left.val);
            int rightValue = (right == null ? Integer.MAX_VALUE : right.val);
            if(leftValue < rightValue) {
                current.next = left;
                left = left.next;
            }
            else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        current = dump.next;
        dump.next = null;
        return current;
    }

    static ListNode HEAD;
    // merge sort
    // reference: http://fisherlei.blogspot.com/2013/12/leetcode-sort-list-solution.html
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        int length = 0;
        ListNode current = head;
        while(current != null) {
            current = current.next;
            length++;
        }
        HEAD = head;
        return sortList(length);
    }
}

/*
    Second Round
*/
class Solution3 {
    static ListNode Head;
    
    private ListNode merge(ListNode h1, ListNode h2) {
        ListNode head = null, prev = null;
        while(h1 != null && h2 != null) {
            ListNode current;
            if(h1.val < h2.val) {
                current = h1;
                h1 = h1.next;
            }
            else {
                current = h2;
                h2 = h2.next;
            }
            if(head == null) {
                head = current;
                prev = current;
            }
            else {
                prev.next = current;
                prev = current;
            }
        }
        if(prev != null) {
            if(h1 != null) prev.next = h1;
            if(h2 != null) prev.next = h2;
        }
        else {
            if(h1 != null) head = h1;
            if(h2 != null) head = h2;
        }
        return head;
    }
    
    private ListNode sortList(int length) {
        if(length == 1) {
            ListNode tmp = Head;
            Head = Head.next;
            tmp.next = null;
            return tmp;
        }
        int half = length / 2;
        ListNode h1 = sortList(half);
        ListNode h2 = sortList(length - half);
        return merge(h1, h2);
    }
    
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        Head = head;
        ListNode cur = head;
        int length = 0;
        while(cur != null) {
            length++;
            cur = cur.next;
        }
        return sortList(length);
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] array = {4,19,14,5,-3,1,8,5,11,15};
        ListNode head = Input.buildExampleListWithArray(array);
        Output.printList(head);
        Output.printList(solution.sortList(head));
    }
}


