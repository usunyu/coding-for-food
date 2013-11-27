import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; next = null; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    // from BinaryTreeLevelOrderTraversal.java for testing
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
        if(root == null) {
            return levels;
        }
        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            LinkedList<TreeNode> tempQueue = new LinkedList<TreeNode>();
            ArrayList<Integer> level = new ArrayList<Integer>();
            while(!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.removeFirst();
                if(node.left != null) {
                    tempQueue.add(node.left);
                }
                if(node.right != null) {
                    tempQueue.add(node.right);
                }
                level.add(node.val);
            }
            // finish one level
            levels.add(level);
            nodeQueue = tempQueue;
        }
        return levels;
    }

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
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> levels) {
        for(ArrayList<Integer> level : levels) {
            for(int val : level) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = null, prev = null;
        for(int i = 1; i <= 10; i++) {
            ListNode node = new ListNode(i);
            if(head == null) {
                head = node;
            }
            else {
                prev.next = node;
            }
            prev = node;
        }
        TreeNode root = solution.sortedListToBST2(head);
        print(solution.levelOrder(root));
    }
}