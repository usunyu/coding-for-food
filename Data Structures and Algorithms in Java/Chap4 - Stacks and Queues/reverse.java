import java.io.*;

class StackX {
	private int maxSize;        // size of stack array
	private char[] stackArray;
	private int top;            // top of stack

	public StackX(int s) {         // constructor
		maxSize = s;             // set array size
		stackArray = new char[maxSize];  // create array
		top = -1;                // no items yet
	}

	public void push(char j) {  // put item on top of stack
		stackArray[++top] = j;   // increment top, insert item
	}

	public char pop() {         // take item from top of stack
		return stackArray[top--]; // access item, decrement top
	}

	public char peek() {       // peek at top of stack
		return stackArray[top];
	}

	public boolean isEmpty() {return top == -1;}

	public boolean isFull() {return top == maxSize - 1;}
}

class Reverser {
	private String input;                // input string
	private String output;               // output string
	public Reverser(String in) { input = in; }

	public String doRev() {
		int stackSize = input.length();   // get max stack size
		StackX theStack = new StackX(stackSize);  // make stack

		for(int j = 0; j < input.length(); j++) {
			char ch = input.charAt(j);
			theStack.push(ch);
		}

		output = "";
		while(!theStack.isEmpty()) {
			char ch = theStack.pop();
			output += ch;
		}
		return output;
	}
}

class ReverseApp {
	public static void main(String[] args) throws IOException {
		String input, output;
		while(true) {
			System.out.print("Enter a string:");
			System.out.flush();
			input = getString();

			if(input.equals(""))
				break;

			Reverser theReverser = new Reverser(input);
			output = theReverser.doRev(); // use it
			System.out.println("Reversed: " + output);
		}
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}  // end class ReverseApp


