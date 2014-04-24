/*
Assume we have a binary tree below:

    _30_ 
   /    \    
  10    20
 /     /  \ 
50    45  35
Using pre-order traversal, the algorithm should write the following to a file:

30 10 50 # # # 20 45 # # 35 # #
*/
import java.util.*;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public String toString() {
    	return "[" + val + "]";
    }
}

class IntWraper { int index; }

class Serialization {
	public static String serialize(TreeNode root) {
		if(root == null)
			return "#";
		StringBuilder sb = new StringBuilder();
		sb.append(root.val);
		sb.append(",");
		sb.append(serialize(root.left));
		sb.append(",");
		sb.append(serialize(root.right));
		return sb.toString();
	}

	private static TreeNode deserialize(String text, IntWraper wrap) {
		if(text == null || wrap.index >= text.length()) return null;
		// read next ','
		int i = wrap.index;
		while(i < text.length()) {
			if(text.charAt(i) == ',')
				break;
			i++;
		}
		String str = text.substring(wrap.index, i);
		wrap.index = i + 1;
		if(str.equals("#")) 
			return null;
		int val = Integer.parseInt(str);
		TreeNode root = new TreeNode(val);
		root.left = deserialize(text, wrap);
		root.right = deserialize(text, wrap);
		return root;
	}

	public static TreeNode deserialize(String text) {
		IntWraper wrap = new IntWraper();
		return deserialize(text, wrap);
	}
}

class Main {
	public static TreeNode buildExampleTree() {
		TreeNode node1 = new TreeNode(30);
        TreeNode node2 = new TreeNode(10); node1.left = node2;
        TreeNode node3 = new TreeNode(20); node1.right = node3;
        TreeNode node4 = new TreeNode(50); node2.left = node4;
        TreeNode node5 = new TreeNode(45); node3.left = node5;
        TreeNode node6 = new TreeNode(35); node3.right = node6;
        return node1;
	}

	public static void levelOrder(TreeNode root) {
        if(root == null) return;
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
            System.out.println(sub);
            queue = tmp;
        }
    }

	public static void main(String[] args) {
		TreeNode root = buildExampleTree();

		System.out.println("Tree:");
		levelOrder(root);

		System.out.println("Serialize:");
		String serial = Serialization.serialize(root);
		System.out.println(serial);

		System.out.println("Deserialize:");
		root = Serialization.deserialize(serial);
		levelOrder(root);
	}
}