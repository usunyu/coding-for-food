/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
*/

import java.util.ArrayList;
import java.util.HashMap;
import LCLibrary.*;

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

class Solution2 {
    private String getStrId(int l, int r) { return l + ":" + r; }

    // add cache
    private ArrayList<TreeNode> genSubTrees(int l, int r, HashMap<String, ArrayList<TreeNode>> cache) {
        String strId = getStrId(l, r);
        if(cache.containsKey(strId)) return cache.get(strId);
        ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
        if(r < l) {
            trees.add(null);
        }
        else {
            for(int i = l; i <= r; i++) {
                ArrayList<TreeNode> lefts = genSubTrees(l, i - 1, cache);
                ArrayList<TreeNode> rights = genSubTrees(i + 1, r, cache);
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

    public ArrayList<TreeNode> generateTrees(int n) {
        return genSubTrees(1, n, new HashMap<String, ArrayList<TreeNode>>());
    }
}
/*
    Second Round
*/
class Solution3 {
    private ArrayList<TreeNode> generateTrees(int left, int right) {
        ArrayList<TreeNode> trees = new ArrayList<TreeNode>();
        if(left > right) trees.add(null);
        for(int i = left; i <= right; i++) {
            ArrayList<TreeNode> leftTrees = generateTrees(left, i - 1);
            ArrayList<TreeNode> rightTrees = generateTrees(i + 1, right);
            for(TreeNode leftNode : leftTrees) {
                for(TreeNode rightNode : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode; root.right = rightNode;
                    trees.add(root);
                }
            }
        }
        return trees;
    }
    
    public ArrayList<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.generateTrees(10).size());
    }
}
