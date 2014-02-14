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

class Q3_2App {
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