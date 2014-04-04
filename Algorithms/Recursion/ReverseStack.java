class Node {
	int data;
	Node next;

	public Node(int d) {
		data = d;
	}
}

class Stack {
	Node top;

	public boolean isEmpty() {
		return top == null;
	}

	public void push(int data) {
		Node n = new Node(data);
		if(top == null) {
			top = n;
		}
		else {
			n.next = top;
			top = n;
		}
	}

	public int pop() {
		if(top == null) {
			System.err.println("Stack is empty.");
			return -1;
		}
		else {
			Node n = top;
			top = top.next;
			return n.data;
		}
	}

	//Below is a recursive function that inserts an element at the bottom of a stack.
	public void insertAtBottom(int data) {
		if(isEmpty()) {
			push(data);
		}
		else {
			/* Hold all items in Function Call Stack until we reach end of 
		       the stack. When the stack becomes empty, the isEmpty(*top_ref) 
		       becomes true, the above if part is executed and the item is 
		       inserted at the bottom */
			int tmp = pop();
			insertAtBottom(data);
			/* Once the item is inserted at the bottom, push all the
          		items held in Function Call Stack */
          	push(tmp);
		}
	}

	//Below is the function that reverses the given stack using insertAtBottom()
	public void reverse() {
		if(!isEmpty()) {
			reverse(pop());
		}
	}

	private void reverse(int data) {
		if(!isEmpty()) {
			reverse(pop());
			insertAtBottom(data);
		}
		else {
			push(data);
		}
	}
}

class Main {
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.insertAtBottom(0);
		stack.reverse();
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}