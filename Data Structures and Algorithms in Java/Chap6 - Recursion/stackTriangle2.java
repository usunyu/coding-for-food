import java.io.*;

class StackX {
	private int maxSize;        // size of stack array
	private int[] stackArray;
	private int top;            // top of stack

	public StackX(int s) {      // constructor
		maxSize = s;
		stackArray = new int[maxSize];
		top = -1;
	}

	public void push(int p) {    // put item on top of stack
		stackArray[++top] = p;
	}

	public int pop() {			 // take item from top of stack
		return stackArray[top--];
	}

	public int peek() {          // peek at top of stack
		return stackArray[top]; 
	}

	public boolean isEmpty() {	 // true if stack is empty
		return (top == -1);
	}
}

class StackTriangle2App {
	static int theNumber;
	static int theAnswer;
	static StackX theStack;

	public static void main(String[] args) throws IOException {
		System.out.print("Enter a number: ");
		System.out.flush();
		theNumber = getInt();
		stackTriangle();
		System.out.println("Triangle="+theAnswer);
	}  // end main()

	public static void stackTriangle() {
		theStack = new StackX(10000);
		theAnswer = 0;
		while(theNumber > 0) {
			theStack.push(theNumber);
			--theNumber;
		}
		while( !theStack.isEmpty() ) {
			int newN = theStack.pop();
			theAnswer += newN;
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}
}

