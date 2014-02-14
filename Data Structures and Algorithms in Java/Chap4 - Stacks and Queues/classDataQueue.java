import java.io.*;

class Customer {
	int time;

	public Customer(int t) {
		time = t;
	}

	public int getTime() {
		return time;
	}

	public void decreaseTime() {
		time--;
	}
}

class Queue {
	private int maxSize;
	private Customer[] queArray;
	private int front;
	private int rear;
	private int nItems;
	int num;

	public Queue(int s, int n) {
		maxSize = s;
		queArray = new Customer[maxSize];
		front = 0;
		rear = -1;
		nItems = 0;
		num = n;
	}

	public void insert(Customer j) {
		if(rear == maxSize-1)
			rear = -1;
		queArray[++rear] = j;
		nItems++;
	}

	public Customer remove() {         // take item from front of queue
		Customer temp = queArray[front++]; // get value and incr front
		if(front == maxSize)          // deal with wraparound
			front = 0;
		nItems--;                     // one less item
		return temp;
	}

	public Customer peekFront() {     // peek at front of queue
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
		System.out.println("No." + num +" queue:");
		int i = 0;
		int j = front;
		while(i < nItems) {
			int no = i + 1;
			System.out.print(no + ":" + queArray[j++].getTime() + " ");
			i++;
			if(j == maxSize)
				j = 0;
		}
		System.out.println();
	}
}

class QueueApp {
	public static void main(String[] args) throws IOException {
		Queue[] queue = new Queue[3];
		queue[0] = new Queue(10, 1);
		queue[1] = new Queue(10, 2);
		queue[2] = new Queue(10, 3);
		String input;
		while(true) {
			System.out.print("Enter a customer wait time:");
			System.out.flush();
			input = getString();

			if(input.equals(""))
				break;

			int time = Integer.parseInt(input);
			
			if(time > 0) { // input new customer
				System.out.print("Enter a queue no:");
				input = getString();
				int no = Integer.parseInt(input);
				if(input.equals(""))
					break;
				Customer cust = new Customer(time);
				queue[no - 1].insert(cust);
			}
			else {	// simulate time pass
				for(int i = 0; i < queue.length; i++) {
					if(queue[i].isEmpty())
						continue;
					Customer cust = queue[i].peekFront();
					cust.decreaseTime();
					if(cust.getTime() == 0)
						queue[i].remove();
				}
			}

			// display
			for(int i = 0; i < queue.length; i++) {
				queue[i].display();
			}
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}



