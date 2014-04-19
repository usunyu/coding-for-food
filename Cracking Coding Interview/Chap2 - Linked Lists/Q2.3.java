/*
Implement an algorithm to delete a node in the middle of a singly linked list, given only access to that node.
*/

import java.util.*; 

class Solution {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add('a');myList.add('b');myList.add('c');myList.add('d');myList.add('e');
		myList.add('f');myList.add('g');myList.add('h');myList.add('i');
		myList.displayChList();
		// myList.removeMiddle();
		// myList.display();
		MyNode node = myList.get(3);
		myList.removeNode(node);
		myList.displayChList();
	}
}
/*
	Second Round
*/
class Solution2 {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add('a');myList.add('b');myList.add('c');myList.add('d');myList.add('e');
		myList.add('f');myList.add('g');myList.add('h');myList.add('i');
		myList.displayChList();
		MyNode node = myList.get(3);
		myList.removeNode2(node);
		myList.displayChList();
	}
}