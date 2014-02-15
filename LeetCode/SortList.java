
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

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

    private ListNode sortList2(int length) {
        if(length == 1) {
            ListNode tmp = HEAD;
            HEAD = HEAD.next;
            tmp.next = null;
            return tmp;
        }
        ListNode left = sortList2(length / 2);
        ListNode right = sortList2(length - length / 2);
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
    public ListNode sortList2(ListNode head) {
        if(head == null || head.next == null) return head;
        int length = 0;
        ListNode current = head;
        while(current != null) {
            current = current.next;
            length++;
        }
        HEAD = head;
        return sortList2(length);
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
        int[] array = {4,19,14,5,-3,1,8,5,11,15};
        ListNode head = null, prev = null;
        for(int i = 0; i < array.length; i++) {
            ListNode node = new ListNode(array[i]);
            if(prev == null) {
                head = node;
                prev = node;
            }
            else {
                prev.next = node;
                prev = node;
            }
        }
        print(head);
        print(solution.sortList2(head));
    }
}


