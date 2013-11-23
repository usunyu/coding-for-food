import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

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
        int[] preorder = {1,2,3};
        int[] inorder = {3,2,2};
        TreeNode root = solution.buildTree(preorder, inorder);
        print(solution.levelOrder(root));
    }
}
