
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    public void flatten(TreeNode root) {
        if(root != null) {
            TreeNode temp = root.right;
            if(root.left != null) {
                temp = root.right;
                root.right = root.left;
                root.left = null;
                TreeNode travel = root;
                while(travel.right != null) {
                    travel = travel.right;
                }
                travel.right = temp;
                flatten(root.right);
            }
            if(temp != null) {
                flatten(temp);
            }
        }
    }
}

class Main {
    public static void print(TreeNode root) {
        TreeNode current = root;
        while(current != null) {
            if(current.left != null) {
                System.err.print("err ");
            }
            System.out.print(current.val + " ");
            current = current.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = new TreeNode(1);
        TreeNode rc1 = new TreeNode(2);
        root.right = rc1;
        TreeNode lc2 = new TreeNode(3);
        rc1.left = lc2;
        solution.flatten(root);
        print(root);
    }
}
