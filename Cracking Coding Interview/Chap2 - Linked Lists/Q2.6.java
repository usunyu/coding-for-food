import java.util.*; 

class Solution {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add('a');
		myList.add('b');
		myList.add('c');
		myList.add('d');
		myList.add('e');
		myList.displayChList();
		myList.corrupt(2);
		// myList.display();
		MyNode corrupt = myList.findCorrupt2();
		System.out.print("Corrupt: ");
		corrupt.displayCh();
		System.out.println();
	}
}


