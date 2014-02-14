import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null) return result;
        // initial
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack2.push(root);
        boolean left = false;
        
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            if(stack1.isEmpty()) {
                // flip flag
                left = left ? false : true;
                ArrayList<Integer> level = new ArrayList<Integer>();
                while(!stack2.isEmpty()) {
                    TreeNode node = stack2.pop();
                    level.add(node.val);
                    if(left) {  // from left to right
                        if(node.left != null) stack1.push(node.left);
                        if(node.right != null) stack1.push(node.right);
                    }
                    else {  // from right to left
                        if(node.right != null) stack1.push(node.right);
                        if(node.left != null) stack1.push(node.left);
                    }
                }
                result.add(level);
            }
            else {
                // flip flag
                left = left ? false : true;
                ArrayList<Integer> level = new ArrayList<Integer>();
                while(!stack1.isEmpty()) {
                    TreeNode node = stack1.pop();
                    level.add(node.val);
                    if(left) {  // from left to right
                        if(node.left != null) stack2.push(node.left);
                        if(node.right != null) stack2.push(node.right);
                    }
                    else {  // from right to left
                        if(node.right != null) stack2.push(node.right);
                        if(node.left != null) stack2.push(node.left);
                    }
                }
                result.add(level);
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        root.left = node1;
        TreeNode node2 = new TreeNode(3);
        root.right = node2;
        TreeNode node3 = new TreeNode(4);
        node1.left = node3;
        TreeNode node4 = new TreeNode(5);
        node2.right = node4;
        Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> result = solution.zigzagLevelOrder(root);
        for(ArrayList<Integer> level : result) {
            for(int i : level) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
