import java.io.*;

class Params {
	public int n;
	public int returnAddress;
	public Params(int nn, int ra) {
		n = nn;
		returnAddress = ra;
	}
}

class StackX {
	private int maxSize;
	private Params[] stackArray;
	private int top;
	public StackX(int s) {							// constructor
		maxSize = s;								// set array size
		stackArray = new Params[maxSize];			// create array
		top = -1;									// no items yet
	}

	public void push(Params p) {					// put item on top of stack
		stackArray[++top] = p;						// increment top, insert item
	}

	public Params pop() {
		return stackArray[top--];
	}

	public Params peek() {
		return stackArray[top];
	}
}

class StackTriangleApp {
	static int theNumber;
	static int theAnswer;
	static StackX theStack;
	static int codePart;
	static Params theseParams;

	public static void main(String[] args) throws IOException {
		System.out.print("Enter a number: ");
		System.out.flush();
		theNumber = getInt();
		recTriangle();
		System.out.println("Triangle="+theAnswer);
	}

	public static void recTriangle() {
		theStack = new StackX(10000);
		codePart = 1;
		while( step() == false)
			;
	}

	public static boolean step() {
		switch(codePart) {
			case 1:														// initial call
				theseParams = new Params(theNumber, 6);
				theStack.push(theseParams);
				codePart = 2;
				break;
			case 2:														// method entry
				theseParams = theStack.peek();
				if(theseParams.n == 1) {								// test
					theAnswer = 1;
					codePart = 5;										// exit
				}
				else
					codePart = 3;										// recursive call
				break;
			case 3:														// method call
				Params newParams = new Params(theseParams.n - 1, 4);
				theStack.push(newParams);
				codePart = 2;											// go enter method
				break;
			case 4:														// calculation
				theseParams = theStack.peek();
				theAnswer = theAnswer + theseParams.n;
				codePart = 5;
				break;
			case 5:														// method exit
				theseParams = theStack.peek();
				codePart = theseParams.returnAddress;					// (4 or 6)
				theStack.pop();
				break;
			case 6:														// return point
				return true;
		}
		return false;
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



