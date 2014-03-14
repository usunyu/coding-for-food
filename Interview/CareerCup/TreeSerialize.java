/*
Given a normal binary tree, write a function to serialize the tree into a string representation (returning the string), 
and also a function to deserialize a serialized string into the original binary tree.

   1
  / \
 2   3
    /
   4
    \
     5

{1{2}{3{4{#}{5}}{#}}}
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Main {
	public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null) return result;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while(!queue.isEmpty()) {
            Queue<TreeNode> tmp = new LinkedList<TreeNode>();
            ArrayList<Integer> sub = new ArrayList<Integer>();
            while(!queue.isEmpty()) {
                TreeNode node = queue.poll();
                sub.add(node.val);
                if(node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            result.add(sub);
            queue = tmp;
        }
        return result;
    }

    public static void print(ArrayList<ArrayList<Integer>> levels) {
        for(ArrayList<Integer> level : levels) {
            for(int val : level) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

	public static TreeNode constructTree() {
		TreeNode t5 = new TreeNode(5, null, null);
		TreeNode t4 = new TreeNode(4, null, t5);
		TreeNode t3 = new TreeNode(3, t4, null);
		TreeNode t2 = new TreeNode(2, null, null);
		TreeNode t1 = new TreeNode(1, t2, t3);
		return t1;
	}

	public static String treeToString(TreeNode root) {
		StringBuilder ret = new StringBuilder("{");
		if(root == null) ret.append("#");
		else {
			ret.append(root.val);
			if(root.left != null || root.right != null) {
				ret.append(treeToString(root.left));
				ret.append(treeToString(root.right));
			}
		}
		ret.append("}");
		return ret.toString();
	}

	public static TreeNode stringToTree(String str) {
		// get current tree
		str = str.substring(1, str.length() - 1);	// remove {}
		if(str.charAt(0) == '#') return null;
		int val = str.charAt(0) - '0';
		str = str.substring(1);	// remove root
		TreeNode left = null, right = null;
		int leftBraceCount = 0;
		if(str.length() > 0) {	// has childs
			for(int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if(ch == '{') leftBraceCount++;
				if(ch == '}') leftBraceCount--;
				if(leftBraceCount == 0) {	// we find left and right child
					left = stringToTree(str.substring(0, i + 1));
					right = stringToTree(str.substring(i + 1));
					break;
				}
			}
		}
		TreeNode root = new TreeNode(val, left, right);
		return root;
	}

	public static void main(String[] args) {
		TreeNode root = constructTree();
		String str = treeToString(root);
		System.out.println(str);
		print(levelOrder(stringToTree(str)));
	}
}