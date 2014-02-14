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

	public int size() {return top + 1;}

	public char peekN(int n) {return stackArray[n];}

	public boolean isEmpty() {return top == -1;}

	public boolean isFull() {return top == maxSize - 1;}

	public void displayStack(String s) {
		System.out.print(s);
		System.out.print("Stack (bottom-->top): ");
		for(int j = 0; j < size(); j++) {
			System.out.print(peekN(j));
			System.out.print(' ');
		}
		System.out.println("");
	}
} // end class StackX

/*
1) Scan the Infix string from left to right.
2) Initialise an empty stack.
3) If the scannned character is an operand, add it to the Postfix string. If the scanned 
	character is an operator and if the stack is empty Push the character to stack.
4) If the scanned character is an Operand and the stack is not empty, compare the precedence 
	of the character with the element on top of the stack (topStack). If topStack has higher 
	precedence over the scanned character Pop the stack else Push the scanned character to 
	stack. Repeat this step as long as stack is not empty and topStack has precedence over 
	the character.
5) Repeat this step till all the characters are scanned.
6) (After all characters are scanned, we have to add any character that the stack may have 
	to the Postfix string.) If stack is not empty add topStack to Postfix string and Pop the 
	stack. Repeat this step as long as stack is not empty.
7) Return the Postfix string.
*/

class InToPost {
	private StackX theStack;
	private String input;
	private String output = "";

	public InToPost(String in) {
		input = in;
		int stackSize = input.length();
		theStack = new StackX(stackSize);
	}

	private int getPre(char ch) {
		int pre = -1;
		switch(ch) {
			case '+':
				pre = 0;
				break;
			case '-':
				pre = 0;
				break;
			case '*':
				pre = 1;
				break;
			case '/':
				pre = 1;
				break;
		}
		return pre;
	}

	public String doTrans() {
		for(int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			switch(ch) {
				case '+':
				case '-':
				case '*':
				case '/':
					if(theStack.isEmpty())
						theStack.push(ch);
					else {
						while(!theStack.isEmpty()) {
							char chx = theStack.peek();
							if(getPre(ch) <= getPre(chx)) {
								theStack.pop();
								output += chx;
							}
							else
								break;
						}
						theStack.push(ch);
					}
					break;
				default:
					output += ch;
					break;
			}
		}
		while(!theStack.isEmpty()) {
			char chx = theStack.pop();
			output += chx;
		}
		return output;
	}
}

class InfixApp {
	public static void main(String[] args) throws IOException {
		String input, output;
         while(true) {
         	System.out.print("Enter infix: ");
         	System.out.flush();
         	input = getString();         // read a string from kbd
         	if( input.equals("") )       // quit if [Enter]
            	break;
                                         // make a translator
			InToPost theTrans = new InToPost(input);
			output = theTrans.doTrans(); // do the translation
			System.out.println("Postfix is " + output + '\n');
		}  // end while
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}


