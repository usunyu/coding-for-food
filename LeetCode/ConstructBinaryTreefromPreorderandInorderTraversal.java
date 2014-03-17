/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public TreeNode buildTree(int[] preorder, int pstart, int pend, int[] inorder, int istart, int iend) {
        if(pstart <= pend && istart <= iend) {
            TreeNode local = new TreeNode(preorder[pstart]);
            int i, j;
            for(i = istart; i <= iend; i++) {
                if(inorder[i] == local.val) {
                    break;
                }
            }
            for(j = pstart + 1; j <= pend; j++) {
                boolean find = false;
                for(int k = i + 1; k <= iend; k++) {
                    if(preorder[j] == inorder[k]) {
                        find = true;
                        break;
                    }
                }
                if(find) break;
            }
            // build left
            local.left = buildTree(preorder, pstart + 1, j - 1, inorder, istart, i - 1);
            // build right
            local.right = buildTree(preorder, j, pend, inorder, i + 1, iend);
            return local;
        }
        else return null;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree2(int[] preorder, int pstart, int pend, HashMap<Integer, Integer> inorderMap, int istart, int iend) {
        if(pstart <= pend && istart <= iend) {
            TreeNode local = new TreeNode(preorder[pstart]);
            int i, j;
            i = inorderMap.get(local.val);
            j = (i - istart) + pstart + 1;
            // build left
            local.left = buildTree2(preorder, pstart + 1, j - 1, inorderMap, istart, i - 1);
            // build right
            local.right = buildTree2(preorder, j, pend, inorderMap, i + 1, iend);
            return local;
        }
        else return null;
    }

    // using HashMap for cache
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        HashMap<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree2(preorder, 0, preorder.length - 1, inorderMap, 0, inorder.length - 1);
    }

    /*
        Second Round
    */
    private TreeNode buildTree3(int[] preorder, int pstart, int pend, int[] inorder, int istart, int iend) {
        if(pstart > pend || istart > iend) return null;
        TreeNode root = new TreeNode(preorder[pstart]);
        // search index need split in inorder
        int index;
        for(index = istart; index <= iend; index++) {
            if(inorder[index] == preorder[pstart]) break;
        }
        index -= istart;
        root.left = buildTree3(preorder, pstart + 1, pstart + index, inorder, istart, istart + index - 1);
        root.right = buildTree3(preorder, pstart + index + 1, pend, inorder, istart + index + 1, iend);
        return root;
    }
    
    public TreeNode buildTree3(int[] preorder, int[] inorder) {
        if(preorder.length == 0 || preorder.length != inorder.length) return null;
        int length = preorder.length;
        return buildTree3(preorder, 0, length - 1, inorder, 0, length - 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] preorder = {1,2};
        int[] inorder = {1,2};
        TreeNode root = solution.buildTree3(preorder, inorder);
        Output.levelOrderTraversalTree(root);
    }
}
