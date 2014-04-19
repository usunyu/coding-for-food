/*
You have two numbers represented by a linked list, where each node contains a single digit. 
The digits are stored in reverse order, such that the 1's digit is at the head of the list. 
Write a function that adds the two numbers and returns the sum as a linked list.

FOLLOW UP
Suppose the digits are stored in forward order. Repeat the above problem.
*/

import java.util.*;

class Solution {
	public static void main(String[] args) {
		MyLinkedList number1 = new MyLinkedList();
		MyLinkedList number2 = new MyLinkedList();
		number1.add(9);
		number1.add(9);
		number1.add(1);
		number1.add(3);
		number2.add(8);
		number2.add(5);
		number2.add(8);
		number1.displayValReverseList();
		System.out.print(" + ");
		number2.displayValReverseList();
		System.out.print(" = ");
		MyLinkedList number3 = number1.addList(number2);
		number3.displayValReverseList();
		System.out.println();
		number1.displayValList();
		System.out.print(" + ");
		number2.displayValList();
		System.out.print(" = ");
		number3 = number1.addListForward2(number2);
		number3.displayValList();
		System.out.println();
	}
}
/*
	Second Round
*/
class Solution2 {
	public static void main(String[] args) {
		MyLinkedList number1 = new MyLinkedList();
		MyLinkedList number2 = new MyLinkedList();
		number1.add(9);
		number1.add(9);
		number1.add(1);
		number1.add(3);
		number2.add(8);
		number2.add(5);
		number2.add(8);
		number1.displayValReverseList();
		System.out.print(" + ");
		number2.displayValReverseList();
		System.out.print(" = ");
		MyLinkedList number3 = number1.addList2(number2);
		number3.displayValReverseList();
		System.out.println();
		number1.displayValList();
		System.out.print(" + ");
		number2.displayValList();
		System.out.print(" = ");
		number3 = number1.addListForward3(number2);
		number3.displayValList();
		System.out.println();
	}
}

