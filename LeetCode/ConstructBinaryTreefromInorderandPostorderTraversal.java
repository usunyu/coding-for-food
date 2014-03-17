/*
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public TreeNode buildTree(int[] postorder, int start, int end, HashMap<Integer, Integer> inorderMap, int cur) {
        if(start <= end) {
            TreeNode local = new TreeNode(postorder[end]);
            int index = inorderMap.get(postorder[end]);
            local.left = buildTree(postorder, start, index - cur + start - 1, inorderMap, cur);
            local.right = buildTree(postorder, index - cur + start, end - 1, inorderMap, index + 1);
            return local;
        }
        else return null;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(inorder.length == 0 || postorder.length == 0) return null;
        // build inorder map
        HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++) inorderMap.put(inorder[i], i);
        return buildTree(postorder, 0, postorder.length - 1, inorderMap, 0);
    }

    /*
        Second Round
    */
    private TreeNode buildTree2(int[] inorder, int istart, int iend, int[] postorder, int pstart, int pend) {
        if(istart > iend || pstart > pend) return null;
        TreeNode root = new TreeNode(postorder[pend]);
        // search index need split in inorder
        int index;
        for(index = istart; index <= iend; index++) {
            if(inorder[index] == postorder[pend]) break;
        }
        index -= istart;
        root.left = buildTree2(inorder, istart, istart + index - 1, postorder, pstart, pstart + index - 1);
        root.right = buildTree2(inorder, istart + index + 1, iend, postorder, pstart + index, pend - 1);
        return root;
    }
    
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if(inorder.length == 0 || inorder.length != postorder.length) return null;
        int length = inorder.length;
        return buildTree2(inorder, 0, length - 1, postorder, 0, length - 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] inorder = {4,2,5,1,6,3,7};
        int[] postorder = {4,5,2,6,7,3,1};
        TreeNode root = solution.buildTree2(inorder, postorder);
        Output.levelOrderTraversalTree(root);
    }
}
