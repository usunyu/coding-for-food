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
        int[] inorder = {4,2,5,1,6,3,7};
        int[] postorder = {4,5,2,6,7,3,1};
        TreeNode root = solution.buildTree(inorder, postorder);
        print(solution.levelOrder(root));
    }
}
