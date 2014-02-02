import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; left = null; right = null; }
}

class Solution {
    // recursion solution
    private ArrayList<TreeNode> genSubTrees(int l, int r) {
        ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
        if(r < l) {
            trees.add(null);
        }
        else {
            for(int i = l; i <= r; i++) {
                ArrayList<TreeNode> lefts = genSubTrees(l, i - 1);
                ArrayList<TreeNode> rights = genSubTrees(i + 1, r);
                for(TreeNode left : lefts) {
                    for(TreeNode right : rights) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        trees.add(root);
                    }
                }
            }
        }
        return trees;
    }
    
    public ArrayList<TreeNode> generateTrees(int n) {
        return genSubTrees(1, n);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateTrees(3).size());
    }
}
