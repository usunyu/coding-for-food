import java.io.*;

class StackX {
	private int maxSize;        // size of stack array
	private double[] stackArray;
	private int top;            // top of stack

	public StackX(int s) {         // constructor
		maxSize = s;             // set array size
		stackArray = new double[maxSize];  // create array
		top = -1;                // no items yet
	}

	public void push(double j) {  // put item on top of stack
		stackArray[++top] = j;   // increment top, insert item
	}

	public double pop() {         // take item from top of stack
		return stackArray[top--]; // access item, decrement top
	}

	public double peek() {       // peek at top of stack
		return stackArray[top];
	}

	public boolean isEmpty() {return top == -1;}

	public boolean isFull() {return top == maxSize - 1;}
}

class StackApp {
	public static void main(String[] args) {
		StackX theStack = new StackX(10);  // make new stack
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);

		while( !theStack.isEmpty() ) {
			double value = theStack.pop();
			System.out.print(value);
			System.out.print(" ");
		}  // end while
	System.out.println("");
	}
}  // end class StackApp


