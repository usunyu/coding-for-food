/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public TreeNode sortedArrayToBST(int[] num, int start, int end) {
        TreeNode root = null;
        if(start <= end) {
            int mid = start + (end - start) / 2;
            root = new TreeNode(num[mid]);
            root.left = sortedArrayToBST(num, start, mid - 1);
            root.right = sortedArrayToBST(num, mid + 1, end);
        }
        return root;
    }
    
    public TreeNode sortedArrayToBST(int[] num) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(num.length == 0) return null;
        return sortedArrayToBST(num, 0, num.length - 1);
    }

    /*
        Second Round
    */
    private TreeNode buildBST(int[] num, int left, int right) {
        if(left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(num[mid]);
        root.left = buildBST(num, left, mid - 1);
        root.right = buildBST(num, mid + 1, right);
        return root;
    }
    
    public TreeNode sortedArrayToBST2(int[] num) {
        if(num.length == 0) return null;
        return buildBST(num, 0, num.length - 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {1,2,3,4,5,6,7,8,9,10};
        TreeNode root = solution.sortedArrayToBST2(num);
        Output.levelOrderTraversalTree(root);
    }
}
