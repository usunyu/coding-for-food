/*
Implement an algorithm to find the kth to last element of a singly linked list.
*/

import java.util.*; 

class Solution {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		int[] input = {48, 32, 14, 87, 73, 23, 42, 54, 43, 76, 55, 34};
		myList.initialList(input);
		myList.displayValList();
		System.out.print("nthToLast:\t");
		MyNode node = myList.nthToLast(6);
		if(node != null)
			node.displayVal();
		else
			System.err.println("Not find!");
		System.out.print("nthToLastR:\t");
		node = myList.nthToLastR(6);
		if(node != null)
			node.displayVal();
		else
			System.err.println("Not find!");
		System.out.print("findKthLast:\t");
		node = myList.findKthLast(6);
		if(node != null)
			node.displayVal();
		else
			System.err.println("Not find!");
	}
}

/*
	Second Round
*/
class Solution2 {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		int[] input = {48, 32, 14, 87, 73, 23, 42, 54, 43, 76, 55, 34};
		myList.initialList(input);
		myList.displayValList();
		int n = 6;
		System.out.println("Iteration: ");
		MyNode node = myList.nthToLast2(n);
		if(node != null)
			System.out.println("Last " + n + " number is " + node.val);
		else
			System.err.println("Not found!");
		System.out.println("Recursion: ");
		node = myList.nthToLast2Rec(n);
		if(node != null)
			System.out.println("Last " + n + " number is " + node.val);
		else
			System.err.println("Not found!");
	}
}
