import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null) return result;
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.add(root);
        while(!list.isEmpty()) {
            LinkedList<TreeNode> temp = new LinkedList<TreeNode>();
            ArrayList<Integer> sub = new ArrayList<Integer>();
            while(!list.isEmpty()) {
                TreeNode node = list.remove();
                sub.add(node.val);
                if(node.left != null) temp.add(node.left);
                if(node.right != null) temp.add(node.right);
            }
            list = temp;
            result.add(sub);
        }
        // just reverse the normal level order traversal
        Collections.reverse(result);
        return result;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> result) {
        for(ArrayList<Integer> list : result) {
            System.out.println(list.toString());
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        root.right = node1;
        TreeNode node2 = new TreeNode(3);
        node1.left = node2;
        TreeNode node3 = new TreeNode(4);
        root.left = node3;
        Solution solution = new Solution();
        print(solution.levelOrderBottom(root));
    }
}
