import java.util.*;

class MyNode {
	int value;
	MyNode next;

	public MyNode(int value) {
		this.value = value;
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

	public int peek() {
		return top.value;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}

class Q3_6App {
	public static void sortStack(MyStack stack) {
		MyStack buffer = new MyStack();
		while(!stack.isEmpty()) {
			int value = stack.pop();
			if(buffer.isEmpty())
				buffer.push(value);
			else {
				int bufValue = buffer.peek();
				while(value > bufValue) {
					stack.push(buffer.pop());
					if(buffer.isEmpty())
						break;
					bufValue = buffer.peek();
				}
				buffer.push(value);
			}
		}
		// store back
		while(!buffer.isEmpty())
			stack.push(buffer.pop());
	}

	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push(6);
		stack.push(7);
		stack.push(3);
		stack.push(5);
		stack.push(9);
		stack.push(4);
		sortStack(stack);
		while(!stack.isEmpty())
			System.out.print(stack.pop() + " ");
		System.out.println();
	}
}

