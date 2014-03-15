/*
后序遍历

后续遍历稍显复杂，需要建立一个临时节点dump，令其左孩子是root。并且还需要一个子过程，就是倒序输出某两个节点之间路径上的各个节点。

步骤：

当前节点设置为临时节点dump。

1. 如果当前节点的左孩子为空，则将其右孩子作为当前节点。

2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。

   a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。

   b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。倒序输出从当前节点的左孩子到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右孩子。

3. 重复以上1、2直到当前节点为空。

http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
*/

import java.util.ArrayList;
import java.util.Collections;

class TreeNode {
	int value;
	TreeNode left, right;

	public TreeNode(int value) {
		this.value = value;
	}
}

class Main {
	public static void visitReverse(TreeNode from, TreeNode to, ArrayList<Integer> result) {
		TreeNode cur = from;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		while(true) {
			temp.add(cur.value);
			if(cur == to) break;
			cur = cur.right;
		}
		Collections.reverse(temp);
		result.addAll(temp);
	}

	public static ArrayList<Integer> morrisPostorder(TreeNode root) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		TreeNode dump = new TreeNode(-1);
		dump.left = root;
		TreeNode cur = dump;
		while(cur != null) {
			if(cur.left == null) {
				cur = cur.right;
			}
			else {
				TreeNode pred = cur.left;
				while(pred.right != null && pred.right != cur)
					pred = pred.right;
				if(pred.right == null) {	// connect
					pred.right = cur;
					cur = cur.left;
				}
				else {
					visitReverse(cur.left, pred, result);	// visit reverse
					pred.right = null;
					cur = cur.right;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
        TreeNode root = new TreeNode(9);
        TreeNode node1 = new TreeNode(5); root.left = node1;
        TreeNode node2 = new TreeNode(8); root.right = node2;
        TreeNode node3 = new TreeNode(1); node1.left = node3;
        TreeNode node4 = new TreeNode(4); node1.right = node4;
        TreeNode node5 = new TreeNode(2); node4.left = node5;
        TreeNode node6 = new TreeNode(3); node4.right = node6;
        TreeNode node7 = new TreeNode(7); node2.right = node7;
        TreeNode node8 = new TreeNode(6); node7.left = node8;

        ArrayList<Integer> result = morrisPostorder(root);
        System.out.println(result);
    }
}