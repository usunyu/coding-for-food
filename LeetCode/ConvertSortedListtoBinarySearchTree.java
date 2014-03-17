/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public TreeNode sortedArrayToBST(ArrayList<Integer> array, int start, int end) {
        TreeNode root = null;
        if(start <= end) {
            int mid = start + (end - start) / 2;
            root = new TreeNode(array.get(mid));
            root.left = sortedArrayToBST(array, start, mid - 1);
            root.right = sortedArrayToBST(array, mid + 1, end);
        }
        return root;
    }
    
    // same as Convert Sorted Array to Binary Search Tree
    // using O(N) extra space
    public TreeNode sortedListToBST(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(head == null) return null;
        ListNode current = head;
        ArrayList<Integer> array = new ArrayList<Integer>();
        while(current != null) {
            array.add(current.val);
            current = current.next;
        }
        return sortedArrayToBST(array, 0, array.size() - 1);
    }

    // time complexity : O(N)
    // space complexity : O(1)
    private TreeNode listToBST(ListNode head, int low, int high) {
        if (low > high) return null;
        int mid = low + (high - low) / 2;
        // build up tree recursively
        TreeNode left = listToBST(head, low, mid-1);
        TreeNode root = new TreeNode(head.val);
        root.left = left;
        // Java pass in Object by reference, so we can't change head but we can change its content :)
        if (head.next != null) { // "move to next"
            head.val = head.next.val;
            head.next = head.next.next;
            root.right = listToBST(head, mid+1, high);
        }
        return root;
    }

    // http://n00tc0d3r.blogspot.com/search?q=Convert+Sorted+List+to+Binary+Search+Tree
    public TreeNode sortedListToBST2(ListNode head) {  
        // calculate list length  
        int len = 0; ListNode cur = head;  
        while (cur!=null) {  
            cur = cur.next;  
            len++;  
        }  
        // build the BST  
        return listToBST(head, 0, len-1);  
    }

    /*
        Second Round
    */
    private TreeNode buildBST(ListNode head, int left, int right) {
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        // find mid node
        ListNode node = head;
        for(int i = left; i < mid; i++) {
            node = node.next;
        }
        TreeNode root = new TreeNode(node.val);
        root.left = buildBST(head, left, mid - 1);
        root.right = buildBST(node.next, mid + 1, right);
        return root;
    }
    
    public TreeNode sortedListToBST3(ListNode head) {
        if(head == null) return null;
        int length = 0;
        ListNode node = head;
        while(node != null) {
            node = node.next;
            length++;
        }
        return buildBST(head, 0, length - 1);
    }
}

class Main {    
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = Input.buildSortedList();
        TreeNode root = solution.sortedListToBST3(head);
        Output.levelOrderTraversalTree(root);
    }
}