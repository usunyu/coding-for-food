package LCLibrary;

import java.util.ArrayList;
import java.util.LinkedList;

public class Output {
	public static void printList(ListNode node) {
        while(node != null) {
        	if(node.next != null)
            	System.out.print(node.val + " -> ");
            else
            	System.out.print(node.val);
            node = node.next;
        }
        System.out.println();
    }

    public static void printLevelLists(ArrayList<ArrayList<Integer>> levels) {
        for(ArrayList<Integer> level : levels) {
        	System.out.print("[");
        	for(int i = 0; i < level.size(); i++) {
        		if(i == level.size() - 1)
        			System.out.print(level.get(i));
        		else
        			System.out.print(level.get(i) + ", ");
        	}
            System.out.println("]");
        }
    }

    // from BinaryTreeLevelOrderTraversal.java for testing
    public static void levelOrderTraversalTree(TreeNode root) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
        if(root == null) {
            return;
        }
        LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            LinkedList<TreeNode> tempQueue = new LinkedList<TreeNode>();
            ArrayList<Integer> level = new ArrayList<Integer>();
            while(!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.removeFirst();
                if(node.left != null) {
                    tempQueue.add(node.left);
                }
                if(node.right != null) {
                    tempQueue.add(node.right);
                }
                level.add(node.val);
            }
            // finish one level
            levels.add(level);
            nodeQueue = tempQueue;
        }
        printLevelLists(levels);
    }

    public static void printFlattenTree(TreeNode root) {
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

    public static void printIntervals(ArrayList<Interval> intervals) {
        for(Interval interval : intervals) {
            System.out.print(interval + " ");
        }
        System.out.println();
    }
}