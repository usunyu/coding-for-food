/*
In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of different sizes which can 
slide onto any tower. The puzzle starts with disks sorted in ascending order of size from top to bottom 
(i.e., each disk sits on top of an even larger one). You have the following constraints:
(1) Only one disk can be moved at a time.
(2) A disk is slid off the top of one tower onto the next rod.
(3) A disk can only be placed on top of a larger disk.
Write a program to move the disks from the first tower to the last using Stacks.
*/

import java.util.*;

class MyNode {
	int size;
	char ch;
	MyNode next;

	public MyNode(char ch, int size) {
		this.ch = ch;
		this.size = size;
	}

	public void display() {
		System.out.print(ch + "(" + size + ")");
	}
}

class MyStack {
	private MyNode top;
	int size;

	public void push(char ch, int value) {
		MyNode node = new MyNode(ch, value);
		node.next = top;
		top = node;
		size++;
	}

	public void push(MyNode node) {
		node.next = top;
		top = node;
		size++;
	}

	public MyNode pop() {
		if(!isEmpty()) {
			MyNode temp = top;
			top = top.next;
			size--;
			return temp;
		}
		else
			return null;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public int size() {
		return size;
	}
}

class Hanoi {
	MyStack[] tower;

	public Hanoi() {
		tower = new MyStack[3];
		for(int i = 0; i < tower.length; i++)
			tower[i] = new MyStack();
	}

	public void put(int index, char ch, int size) {
		tower[index].push(ch, size);
	}

	public MyNode get(int index) {
		return tower[index].pop();
	}

	public void moveNode(int from, int to) {
		MyNode temp = tower[from].pop();
		tower[to].push(temp);
		temp.display();
		System.out.println(": move from tower " + from + " to tower " + to);
	}

	public void move(int disk, int from, int middle, int to) {
		if(disk == 1)
			moveNode(from, to);
		else {
			move(disk - 1, from, to, middle);
			moveNode(from, to);
			move(disk - 1, middle, from, to);
		}
	}
}

class Solution {
	public static void main(String[] args) {
		Hanoi hanoiTower = new Hanoi();
		// index, char, size
		hanoiTower.put(0, 'C', 3);
		hanoiTower.put(0, 'B', 2);
		hanoiTower.put(0, 'A', 1);
		hanoiTower.move(3, 0, 1, 2);
	}
}
/*
	Second Round
*/
class Hanoi2 {
	public static void printMove(int n, int from, int to) {
		System.out.println("Move disk " + n + " from tower " + from + " to tower " + to);
	}

	public static void move(int n, int from, int inter, int to) {
		if(n == 1) printMove(n, from, to);
		else {
			move(n - 1, from, to, inter);
			printMove(n, from, to);
			move(n - 1, inter, from, to);
		}
	}
}

class Solution2 {
	public static void main(String[] args) {
		Hanoi2.move(3, 0, 1, 2);
	}
}


