/*
Imagine a (literal) stack of plates. If the stack gets too high, it migh t topple. Therefore, in real life, 
we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure 
SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack 
once the previous one exceeds capacity. SetOfStacks.push() and SetOfStacks. pop () should behave identically 
to a single stack (that is, pop () should return the same values as it would if there were just a single stack).

FOLLOW UP
Implement a function popAt(int index) which performs apop operation on a specific sub-stack.
*/

import java.util.*;

class MyNode {
	int value;
	MyNode next;
	MyNode left;

	public MyNode(int value) { this.value = value; }
}

class SetOfStacks {
	int singleSize;
	int size;
	MyNode top;

	public SetOfStacks(int singleSize) {
		this.singleSize = singleSize;
		size = 0;
	}

	public void push(int value) {
		MyNode node = new MyNode(value);
		if(top != null) {
			if(size % singleSize == 0)	// need expand
				node.left = top;
			else
				node.next = top;
		}
		top = node;
		size++;
	}

	public int pop() {
		if(isEmpty())
			return -1;
		int value = top.value;
		if(top.left != null)
			top = top.left;
		else if(top.next != null)
			top = top.next;
		else
			top = null;
		size--;
		return value;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}

class SetOfStacks2 {
	int singleSize;
	ArrayList<Stack<Integer>> stacks;

	public SetOfStacks2(int singleSize) {
		this.singleSize = singleSize;
		stacks = new ArrayList<Stack<Integer>>();
	}

	public Stack<Integer> getLastStack() {
		int index = stacks.size() - 1;
		if(index < 0)
			return null;
		else
			return stacks.get(index);
	}

	public void push(int value) {
		Stack<Integer> stack = getLastStack();
		if(stack == null || stack.size() == singleSize) {	// need create a new stack
			stack = new Stack<Integer>();
			stacks.add(stack);
		}
		stack.push(value);
	}

	public int pop() {
		if(isEmpty())
			return -1;
		Stack<Integer> stack = getLastStack();
		int value = stack.pop();
		if(stack.size() == 0)
			stacks.remove(stacks.size() - 1);
		return value;
	}

	public int popAt(int index) {
		if(index >= stacks.size())
			return -1;
		Stack<Integer> stack = stacks.get(index);

		int value = stack.pop();
		if(stack.size() == 0)
			stacks.remove(index);

		return value;
	}

	public boolean isEmpty() {
		return stacks.size() == 0;
	}
}

class Solution {
	public static void main(String[] args) {
		SetOfStacks2 stackSet = new SetOfStacks2(3);
		stackSet.push(0);
		stackSet.push(1);
		stackSet.push(2);
		stackSet.push(3);
		stackSet.push(4);
		stackSet.push(5);
		stackSet.push(6);
		stackSet.push(7);
		stackSet.push(8);
		stackSet.push(9);

		System.out.println("Pop at sub stack 1: " + stackSet.popAt(1));
		System.out.println("Pop at sub stack 1: " + stackSet.popAt(1));
		System.out.println("Pop at sub stack 1: " + stackSet.popAt(1));
		System.out.println("Pop at sub stack 1: " + stackSet.popAt(1));

		while(!stackSet.isEmpty()) {
			int value = stackSet.pop();
			System.out.print(value + " ");
		}
		System.out.println();
	}
}

