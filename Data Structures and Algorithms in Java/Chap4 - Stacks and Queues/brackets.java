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

class BracketChecker {
	private String input;                   // input string

	public BracketChecker(String in){		// constructor
		input = in;
	}

	public void check() {
		int stackSize = input.length();
		StackX theStack = new StackX(stackSize);

		for(int i = 0; i < stackSize; i++) {
			char ch = input.charAt(i);
			switch (ch) {
				case '{':
				case '[':
				case '(':
					theStack.push(ch);
					break;
				case '}':
				case ']':
				case ')':
					if(!theStack.isEmpty()) {
						char chx = theStack.pop();
						if( (ch=='}' && chx!='{') ||
							(ch==']' && chx!='[') ||
							(ch==')' && chx!='(') )
							System.out.println("Error: "+ch+" at "+i);
					}
					else
						System.out.println("Error: "+ch+" at "+i);
					break;
				default:
					break;
			}
		}
		if( !theStack.isEmpty() )
			System.out.println("Error: missing right delimiter");
	}
}

class BracketsApp {
	public static void main(String[] args) throws IOException {
		String input;
		while(true) {
			System.out.print("Enter string containing delimiters: ");
			System.out.flush();
			input = getString();     // read a string from kbd
			if( input.equals("") )   // quit if [Enter]
				break;
                                
			BracketChecker theChecker = new BracketChecker(input);	// make a BracketChecker
			theChecker.check();      // check brackets
		}  // end while
    }  // end main()

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}







