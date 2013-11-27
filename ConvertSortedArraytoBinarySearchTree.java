import java.util.*;

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
        int[] num = {1,2,3,4,5,6,7,8,9,10};
        TreeNode root = solution.sortedArrayToBST(num);
        print(solution.levelOrder(root));
    }
}
