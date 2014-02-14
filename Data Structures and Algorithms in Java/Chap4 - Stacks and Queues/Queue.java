import java.io.*;

class Queue {
	private int maxSize;
	private int[] queArray;
	private int front;
	private int rear;
	private int nItems;

	public Queue(int s) {
		maxSize = s;
		queArray = new int[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
	}

	public void insert(int j) {
		if(rear == maxSize-1)
			rear = -1;
		queArray[++rear] = j;
		nItems++;
	}

	public int remove() {         // take item from front of queue
		int temp = queArray[front++]; // get value and incr front
		if(front == maxSize)          // deal with wraparound
			front = 0;
		nItems--;                     // one less item
		return temp;
	}

	public int peekFront() {     // peek at front of queue
		return queArray[front];
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

class Queue2 {
	private int maxSize;
	private int[] queArray;
	int front;
	int rear;

	public Queue2(int s) {
		maxSize = s;
		queArray = new int[maxSize + 1];
		front = 0;
		rear = -1;
	}

	public void insert(int value) {
		if(isFull())
			return;
		if(rear == maxSize-1)
			rear = -1;
		queArray[++rear] = value;
	}

	public int remove() {
		if(isEmpty())
			return -1;
		int value = queArray[front++];
		if(front == maxSize)
			front = 0;
		return value;
	}

	public int peekFront() {
		return queArray[front];
	}

	public boolean isEmpty() {
		return(rear + 1 == front || front + maxSize - 1 == rear);
	}

	public boolean isFull() {
		return(front + 2 == rear || front + maxSize - 1 == rear);
	}

	public int size() {
		if(rear > front)
			return rear - front + 1;
		else
			return maxSize - front + rear + 1;
	}
}

class QueueApp {
	public static void main(String[] args) {
		Queue theQueue = new Queue(5);  // queue holds 5 items
		theQueue.insert(10);
		theQueue.insert(20);
		theQueue.insert(30);
		theQueue.insert(40);
		theQueue.remove();
		theQueue.remove();
		theQueue.remove();
		theQueue.insert(50);
		theQueue.insert(60);
		theQueue.insert(70);
		theQueue.insert(80);
		theQueue.display();

		while(!theQueue.isEmpty()) {
			int n = theQueue.remove();
			System.out.print(n);
			System.out.print(" ");
		}
		System.out.println("");
	}
}

