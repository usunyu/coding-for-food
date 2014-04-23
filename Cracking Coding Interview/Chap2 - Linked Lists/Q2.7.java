/*
Implement a function to check if a linked list is a palindrome,
*/

class Solution {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add('a');
		myList.add('b');
		myList.add('c');
		myList.add('b');
		myList.add('a');
		// myList.add('a');
		myList.displayChList();
		
		if(myList.isPalindrome2())
			System.out.println("This string is palindrome.");
		else
			System.out.println("This string is not palindrome.");
	}
}
/*
	Second Round
*/
class Solution2 {
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add('a');
		myList.add('b');
		myList.add('c');
		myList.add('b');
		myList.add('a');
		myList.add('a');
		myList.displayChList();
		
		if(myList.isPalindrome3())
			System.out.println("This string is palindrome.");
		else
			System.out.println("This string is not palindrome.");
	}
}
