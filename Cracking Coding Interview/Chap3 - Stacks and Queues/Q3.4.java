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

class Q3_4App {
	public static void main(String[] args) {
		Hanoi hanoiTower = new Hanoi();
		// index, char, size
		hanoiTower.put(0, 'C', 3);
		hanoiTower.put(0, 'B', 2);
		hanoiTower.put(0, 'A', 1);
		hanoiTower.move(3, 0, 1, 2);
	}
}





