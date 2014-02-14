import java.util.*;

class MyNode {
	int value;
	MyNode next;

	public MyNode(int value) {
		this.value = value;
	}

	public void display() {
		System.out.print(value + " ");
	}
}

class MyStack {
	private MyNode top;
	int size;

	public void push(int value) {
		MyNode node = new MyNode(value);
		node.next = top;
		top = node;
		size++;
	}

	public int pop() {
		MyNode temp = top;
		top = top.next;
		size--;
		return temp.value;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}

class MyQueue {
	// stack1 responsible for enqueue
	// stack2 responsible for dequeue
	MyStack stack1, stack2;

	public MyQueue() {
		stack1 = new MyStack();
		stack2 = new MyStack();
	}

	public void enqueue(int value) {
		if(stack1.isEmpty())
			changeStack(stack2, stack1);
		stack1.push(value);
	}

	public int dequeue() {
		if(stack2.isEmpty())
			changeStack(stack1, stack2);
		int value = stack2.pop();
		return value;
	}

	public void changeStack(MyStack s1, MyStack s2) {
		while(!s1.isEmpty()) {
			int value = s1.pop();
			s2.push(value);
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return stack1.size() > stack2.size() ? stack1.size() : stack2.size();
	}
}

class Q3_5App {
	public static void main(String[] args) {
		MyQueue queue = new MyQueue();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		int value = queue.dequeue();
		System.out.print(value + " ");
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		value = queue.dequeue();
		System.out.print(value + " ");
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		while(!queue.isEmpty()) {
			value = queue.dequeue();
			System.out.print(value + " ");
		}
		System.out.println();
	}
}


