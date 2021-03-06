/*
中序遍历
步骤：

1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。

2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。

   a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。

   b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。

3. 重复以上1、2直到当前节点为空。

http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
*/

import java.util.ArrayList;

class TreeNode {
	int value;
	TreeNode left, right;

	public TreeNode(int value) {
		this.value = value;
	}
}

class Main {
	public static ArrayList<Integer> morrisInorder(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		TreeNode cur = root;
		while(cur != null) {
			if(cur.left == null) {	// visit cur
				result.add(cur.value);
				cur = cur.right;
			}
			else {
				// find predecessor
				TreeNode pred = cur.left;
				while(pred.right != null && pred.right != cur) {
					pred = pred.right;
				}
				if(pred.right == null) {	// connect
					pred.right = cur;
					cur = cur.left;
				}
				else {	// restore and vist cur
					result.add(cur.value);
					pred.right = null;
					cur = cur.right;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2); root.left = node1;
        TreeNode node2 = new TreeNode(3); root.right = node2;
        TreeNode node3 = new TreeNode(4); node2.left = node3;

        ArrayList<Integer> result = morrisInorder(root);
        System.out.println(result);
    }
}