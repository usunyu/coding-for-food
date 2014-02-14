import java.util.ArrayList;
import java.util.HashMap;

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

    private String getStrId(int l, int r) {
        return l + ":" + r;
    }

    // add cache
    private ArrayList<TreeNode> genSubTrees2(int l, int r, HashMap<String, ArrayList<TreeNode>> cache) {
        String strId = getStrId(l, r);
        if(cache.containsKey(strId)) return cache.get(strId);
        ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
        if(r < l) {
            trees.add(null);
        }
        else {
            for(int i = l; i <= r; i++) {
                ArrayList<TreeNode> lefts = genSubTrees2(l, i - 1, cache);
                ArrayList<TreeNode> rights = genSubTrees2(i + 1, r, cache);
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
        cache.put(strId, trees);
        return trees;
    }

    public ArrayList<TreeNode> generateTrees2(int n) {
        return genSubTrees2(1, n, new HashMap<String, ArrayList<TreeNode>>());
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateTrees2(10).size());
    }
}
