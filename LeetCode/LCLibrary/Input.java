package LCLibrary;

public class Input {
	/*
		   1
		  / \
		 2   3
		    /
		   4
		    \
		     5
	*/
	public static TreeNode buildExampleTree() {
		TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2); node1.left = node2;
        TreeNode node3 = new TreeNode(3); node1.right = node3;
        TreeNode node4 = new TreeNode(4); node3.left = node4;
        TreeNode node5 = new TreeNode(5); node4.right = node5;
        return node1;
	}
	/*
		   1
		  / \
		2    3
	   / \  / \
	  4  5 6   7
	*/
	public static TreeNode buildExampleTree2() {
		TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2); node1.left = node2;
        TreeNode node3 = new TreeNode(3); node1.right = node3;
        TreeNode node4 = new TreeNode(4); node2.left = node4;
        TreeNode node5 = new TreeNode(5); node2.right = node5;
        TreeNode node6 = new TreeNode(6); node3.left = node6;
        TreeNode node7 = new TreeNode(7); node3.right = node7;
        return node1;
	}

	/*
		2 -> 4 -> 3
	*/
	public static ListNode buildExampleList() {
		ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(4); node1.next = node2;
        ListNode node3 = new ListNode(3); node2.next = node3;
        return node1;
	}

	/*
		5 -> 6 -> 4
	*/
	public static ListNode buildExampleList2() {
		ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(6); node1.next = node2;
        ListNode node3 = new ListNode(4); node2.next = node3;
        return node1;
	}

	/*
		   1
	      / \
	     /   \
	    0 --- 2
	         / \
	         \_/
	*/
	public static UndirectedGraphNode buildExampleUndirectedGraph() {
		UndirectedGraphNode node0 = new UndirectedGraphNode(0);
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        node0.neighbors.add(node1); node0.neighbors.add(node2);
        node1.neighbors.add(node2); node1.neighbors.add(node0);
        node2.neighbors.add(node2); node0.neighbors.add(node1); node0.neighbors.add(node0);
        return node0;
	}
}

