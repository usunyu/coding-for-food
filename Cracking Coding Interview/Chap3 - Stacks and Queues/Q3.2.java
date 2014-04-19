/*
How would you design a stack which, in addition to push and pop, 
also has a function min which returns the minimum element? 
Push, pop and min should all operate in 0(1) time.
*/

import java.util.*;

class MyNode {
	int value;
	int min;
	MyNode next;

	public MyNode(int value, int min) {
		this.value = value;
		this.min = min;
	}
}

class MyStack {
	private MyNode top;
	private int min;

	public MyStack() {
		min = Integer.MAX_VALUE;
		top = null;
	}

	public int min() {
		return min;
	}

	public void push(int value) {
		if(value < min)
			min = value;
		MyNode node = new MyNode(value, min);
		node.next = top;
		top = node;
	}

	public int pop() {
		if(!isEmpty()) {
			int value = top.value;
			top = top.next;
			if(top == null)
				min = Integer.MAX_VALUE;
			else if(top.min != min)
				min = top.min;
			return value;
		}
		else
			return -1;
	}

	public boolean isEmpty() {
		return top == null;
	}
}

class Solution {
	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push(5);
		stack.push(6);
		stack.push(9);
		stack.push(3);
		stack.push(8);
		stack.push(1);
		while(!stack.isEmpty()) {
			int min = stack.min();
			int value = stack.pop();
			System.out.println("Pop value: " + value + ", current min: " + min);
		}
	}
}
/*
	Second Round
*/
class MyNode2 {
	int val;
	MyNode2 next;

	public MyNode2(int v) {val = v;}
}

class MyStack2 {
	MyNode2 top;
	Stack<Integer> mins;

	public MyStack2() { mins = new Stack<Integer>(); }

	public void push(int value) {
		MyNode2 node = new MyNode2(value);
		if(isEmpty() || value <= min()) mins.push(value);
		node.next = top;
		top = node;
	}

	public int pop() {
		if(!isEmpty()) {
			int val = top.val;
			top = top.next;
			if(min() == val) mins.pop();
			return val;
		}
		else return -1;
	}

	public int min() { return mins.peek(); }

	public boolean isEmpty() { return top == null; }
}

class Solution2 {
	public static void main(String[] args) {
		MyStack2 stack = new MyStack2();
		stack.push(5);
		stack.push(6);
		stack.push(9);
		stack.push(3);
		stack.push(8);
		stack.push(1);
		while(!stack.isEmpty()) {
			int min = stack.min();
			int value = stack.pop();
			System.out.println("Pop value: " + value + ", current min: " + min);
		}
	}
}