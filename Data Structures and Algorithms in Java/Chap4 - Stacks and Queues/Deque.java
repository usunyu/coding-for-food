import java.io.*;

class Deque {
	private int maxSize;
	private int[] queArray;
	private int front;
	private int rear;
	private int nItems;

	public Deque(int s) {
		maxSize = s;
		queArray = new int[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	public void push(int j) {
		insertRight(j);
	}

	public int pop() {
		return removeRight();
	}

	public void insertLeft(int j) {
		front--;
		if(front == -1)
			front = maxSize - 1;
		queArray[front] = j;
		nItems++;
	}

	public void insertRight(int j) {
		if(rear == maxSize-1)
			rear = -1;
		queArray[++rear] = j;
		nItems++;
	}

	public int removeLeft() {
		if(isEmpty())
			return -1;
		int temp = queArray[front++]; // get value and incr front
		if(front == maxSize)          // deal with wraparound
			front = 0;
		nItems--;                     // one less item
		return temp;
	}

	public int removeRight() {
		if(isEmpty())
			return -1;
		int temp = queArray[rear--];
		nItems--;
		if(rear == -1 && !isEmpty())
			rear = maxSize - 1;
		return temp;
	}

	public int peekLeft() {     // peek at front of queue
		return queArray[front];
	}

	public int peekRight() {
		return queArray[rear];
	}

	public boolean isEmpty() {   // true if queue is empty
		return (nItems==0);
	}

	public boolean isFull() {
		return (nItems == maxSize);
	}

	public int size() {
		return nItems;
	}

	public void display() {
		int i = 0;
		int j = front;
		while(i < nItems) {
			System.out.print(queArray[j++] + " ");
			i++;
			if(j == maxSize)
				j = 0;
		}
		System.out.println();
	}
}

class DequeApp {
	public static void main(String[] args) {
		Deque theQueue = new Deque(5);  // queue holds 5 items

		theQueue.push(10);
		theQueue.push(20);
		theQueue.push(30);
		theQueue.push(40);

		while(!theQueue.isEmpty()) {
			int n = theQueue.pop();
			System.out.print(n + " ");
		}
		System.out.println("");

		// theQueue.insertLeft(10);
		// theQueue.insertRight(20);
		// theQueue.insertLeft(30);
		// theQueue.insertRight(40);

		// while(!theQueue.isEmpty()) {
		// 	int n = theQueue.removeLeft();
		// 	System.out.print(n);
		// 	System.out.print(" ");
		// }
		// System.out.println("");
	}
}

