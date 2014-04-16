/*
Write code to remove duplicates from an unsorted linked list. FOLLOW UP
How would you solve this problem if a temporary buffer is not allowed?
*/

import java.util.*; 

class Solution {
	public static void initialList(String str, LinkedList<Character> list) {
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			list.add(ch);
		}
	}

	public static void displayList(LinkedList<Character> list) {
		for(int i = 0; i < list.size(); i++)
			System.out.print(list.get(i));
		System.out.println();
	}

	public static void removeDuplicate(LinkedList<Character> list) {
		for(int i = 0; i < list.size(); i++)
			for(int j = i + 1; j < list.size(); j++)
				if(list.get(i) == list.get(j))
					list.remove(j--);
	}

	public static void main(String[] args) {
		String listStr = "ABSNDAASXK";
		System.out.println("Use LinkedList:");

		LinkedList<Character> list = new LinkedList<Character>();
		initialList(listStr, list);
		displayList(list);
		removeDuplicate(list);
		displayList(list);

		System.out.println("Use My LinkedList:");
		MyLinkedList myList = new MyLinkedList();
		MyLinkedList.initialList(listStr, myList);
		MyLinkedList.displayList(myList);
		myList.removeDuplicate();
		MyLinkedList.displayList(myList);
	}
}
/*
	Second Round
*/
class Solution2 {
	public static void main(String[] args) {
		String listStr = "ABSNDAASXK";
		MyLinkedList myList = new MyLinkedList();
		MyLinkedList.initialList(listStr, myList);
		MyLinkedList.displayList(myList);
		myList.removeDuplicate2();
		MyLinkedList.displayList(myList);
	}
}

