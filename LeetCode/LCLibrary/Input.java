package LCLibrary;

import java.util.ArrayList;

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
		     1
	       /  \
	      2    3
	     / \  / \
	    4  5  6  7
	*/
	public static TreeLinkNode buildExampleTreeLink() {
		TreeLinkNode node1 = new TreeLinkNode(1);
        TreeLinkNode node2 = new TreeLinkNode(2); node1.left = node2;
        TreeLinkNode node3 = new TreeLinkNode(3); node1.right = node3;
        TreeLinkNode node4 = new TreeLinkNode(4); node2.left = node4;
        TreeLinkNode node5 = new TreeLinkNode(5); node2.right = node5;
        TreeLinkNode node6 = new TreeLinkNode(6); node3.left = node6;
        TreeLinkNode node7 = new TreeLinkNode(7); node3.right = node7;
        return node1;
	}
	/*
		     1
	       /  \
	      2    3
	     /      \
	    4        7
	*/
	public static TreeLinkNode buildExampleTreeLink2() {
		TreeLinkNode node1 = new TreeLinkNode(1);
        TreeLinkNode node2 = new TreeLinkNode(2); node1.left = node2;
        TreeLinkNode node3 = new TreeLinkNode(3); node1.right = node3;
        TreeLinkNode node4 = new TreeLinkNode(4); node2.left = node4;
        TreeLinkNode node7 = new TreeLinkNode(7); node3.right = node7;
        return node1;
	}
	/*
		    1
	       /
	      2
	     /
	    3
	   /
	  4
	 /
	5
	*/
	public static TreeLinkNode buildExampleTreeLink3() {
		TreeLinkNode node1 = new TreeLinkNode(1);
        TreeLinkNode node2 = new TreeLinkNode(2); node1.left = node2;
        TreeLinkNode node3 = new TreeLinkNode(3); node2.left = node3;
        TreeLinkNode node4 = new TreeLinkNode(4); node3.left = node4;
        TreeLinkNode node5 = new TreeLinkNode(5); node4.left = node5;
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
		1 -> 2 -> 3
	*/
	public static ListNode buildExampleList3() {
		ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2); node1.next = node2;
        ListNode node3 = new ListNode(3); node2.next = node3;
        return node1;
	}

	/*
		4 -> 5 -> 6
	*/
	public static ListNode buildExampleList4() {
		ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(5); node1.next = node2;
        ListNode node3 = new ListNode(6); node2.next = node3;
        return node1;
	}

	/*
		1->4->3->2->5->2
	*/
	public static ListNode buildExampleList5() {
		ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4); node1.next = node2;
        ListNode node3 = new ListNode(3); node2.next = node3;
        ListNode node4 = new ListNode(2); node3.next = node4;
        ListNode node5 = new ListNode(5); node4.next = node5;
        ListNode node6 = new ListNode(2); node5.next = node6;
        return node1;
	}

	/*
		1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
		1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
		1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
		1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
		1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
	*/
	public static ArrayList<ListNode> buildExampleLists() {
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		int n = 5;
		int length = 10;
		for(int i =0; i < n; i++) {
			ListNode head = null ;
			for(int j = length; j > 0; j--) {
				ListNode temp = new ListNode(j);
				if(head == null) {
					head = temp;
				}
				else {
					temp.next = head;
					head = temp;
				}
			}
			lists.add(head);
		}
		return lists;
	}

	/*
		[1,2],[3,5],[6,7],[8,10],[12,16]
		no overlap
	*/
	public static ArrayList<Interval> buildExampleIntervals() {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1, 5));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(12, 16));
		return intervals;
	}

	/*
		[1,3],[2,6],[8,10],[15,18]
		with overlap
	*/
	public static ArrayList<Interval> buildExampleIntervals2() {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));
		return intervals;
	}

	/*
		[15,18],[1,4],[2,6],[1,3],[8,10]
		with overlap, random order
	*/
	public static ArrayList<Interval> buildExampleIntervals3() {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(15, 18));
		intervals.add(new Interval(1, 4));
		intervals.add(new Interval(2, 6));
		intervals.add(new Interval(1, 3));
        intervals.add(new Interval(8, 10));
		return intervals;
	}

	/*
		1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
	*/
	public static ListNode buildSortedList() {
		ListNode head = null, prev = null;
        for(int i = 1; i <= 10; i++) {
            ListNode node = new ListNode(i);
            if(head == null) {
                head = node;
            }
            else {
                prev.next = node;
            }
            prev = node;
        }
        return head;
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
        node1.neighbors.add(node2);
        node2.neighbors.add(node2);
        return node0;
	}
}

