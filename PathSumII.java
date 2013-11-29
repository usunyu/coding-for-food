import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public void pathSum(TreeNode root, int sum, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> paths) {
        if(root != null) {
            sum -= root.val;
            path.add(root.val);
            if(sum == 0 && root.left == null && root.right == null) {
                paths.add(path);
            }
            else {
                if(root.left != null)  pathSum(root.left, sum, new ArrayList<Integer>(path), paths);
                if(root.right != null)  pathSum(root.right, sum, new ArrayList<Integer>(path), paths);
            }
        }
    }
    
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        if(root == null) return paths;
        pathSum(root, sum, new ArrayList<Integer>(), paths);
        return paths;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> paths) {
        for(ArrayList<Integer> path : paths) {
            for(int v : path) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(-2);
        TreeNode lc1 = new TreeNode(4);
        root.left = lc1;
        TreeNode rc1 = new TreeNode(-3);
        root.right = rc1;
        print(solution.pathSum(root, -5));
    }
}
