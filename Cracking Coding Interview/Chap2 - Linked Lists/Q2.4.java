/*
Write code to partition a linked list around a value x, such that all nodes less than x come before alt 
nodes greater than or equal to x.
*/
class Solution {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add(48);
		myList.add(32);
		myList.add(14);
		myList.add(87);
		myList.add(73);
		myList.add(23);
		myList.add(42);
		myList.add(54);
		myList.add(43);
		myList.add(76);
		myList.add(55);
		myList.add(34);
		myList.displayValList();
		myList.partition(50);
		myList.displayValList();
	}
}
/*
	Second Round
*/
class Solution2 {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add(48);
		myList.add(32);
		myList.add(14);
		myList.add(87);
		myList.add(73);
		myList.add(23);
		myList.add(42);
		myList.add(54);
		myList.add(43);
		myList.add(76);
		myList.add(55);
		myList.add(34);
		myList.displayValList();
		// myList.partition2(50);
		myList.partition3(50);
		myList.displayValList();
	}
}
