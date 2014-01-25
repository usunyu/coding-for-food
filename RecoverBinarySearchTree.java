import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class WrapInt {
    int index;
}

class Solution {
    private void inorderSearch(TreeNode root, ArrayList<Integer> array) {
        if(root.left != null) inorderSearch(root.left, array);
        array.add(root.val);
        if(root.right != null) inorderSearch(root.right, array);
    }

    private void inorderFix(TreeNode root, ArrayList<Integer> array,  WrapInt wi) {
        if(root.left != null) inorderFix(root.left, array, wi);
        root.val = array.get(wi.index++);
        if(root.right != null) inorderFix(root.right, array, wi);
    }
    
    // space complexity: O(n)
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        ArrayList<Integer> array = new ArrayList<Integer>();
        inorderSearch(root, array);
        Collections.sort(array);
        inorderFix(root, array, new WrapInt());
    }

    private void swap(TreeNode a, TreeNode b) {
        if (a == null || b == null)  return;
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    // morris traversal: http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
    // space complexity: O(1)
    public void recoverTree2(TreeNode root) {
        if(root == null) return;
        TreeNode cur = root, prev = null, n1 = null, n2 = null;
        while(cur != null) {
            if(cur.left == null) {   // 1. if cur.left = null, output cur and set cur.right to cur
                // visit node
                if(prev != null && cur.val < prev.val) {
                    n2 = cur;
                    if(n1 == null) n1 = prev;
                }
                prev = cur;
                cur = cur.right;
            }
            else {
                // find predecessor
                TreeNode predecessor = cur.left;
                while(predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }
                if(predecessor.right == null) {    // 2. a) if predecessor.right = null, set predecessor.right = cur and set cur = cur.left
                    predecessor.right = cur;
                    cur = cur.left;
                }
                else {  // 2. b) if predecessor.right = cur, set predecessor.right = null(recover tree), output cur and set cur = cur.right
                    predecessor.right = null;
                    // visit node
                    if(prev != null && cur.val < prev.val) {
                        n2 = cur;
                        if(n1 == null) n1 = prev;
                    }
                    prev = cur;
                    cur = cur.right;
                }
            }
        }
        swap(n1, n2);
    }
}

class Main {
    public static ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }
            else {
                node = stack.pop();
                result.add(node.val);
                node = node.right;
            }
        }
        return result;
    }

    public static void print(ArrayList<Integer> result) {
        for(int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(10);
        TreeNode t1 = new TreeNode(5);
        root.left = t1;
        TreeNode t2 = new TreeNode(17);
        root.right = t2;
        TreeNode t3 = new TreeNode(1);
        t1.left = t3;
        TreeNode t4 = new TreeNode(7);
        t1.right = t4;
        TreeNode t5 = new TreeNode(12);
        t2.left = t5;
        TreeNode t6 = new TreeNode(15);
        t2.right = t6;
        print(inorderTraversal(root));
        solution.recoverTree2(root);
        print(inorderTraversal(root));
    }
}
