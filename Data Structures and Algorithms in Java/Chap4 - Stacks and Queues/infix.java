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

class InToPost {
	private StackX theStack;
	private String input;
	private String output = "";

	public InToPost(String in) {
		input = in;
		int stackSize = input.length();
		theStack = new StackX(stackSize);
	}

	public String doTrans() {
		for(int j = 0; j < input.length(); j++) {
			char ch = input.charAt(j);
			switch(ch) {
				case '+':
				case '-':
					gotOper(ch, 1);
					break;
				case '*':
				case '/':
					gotOper(ch, 2);
					break;
				case '(':
					theStack.push(ch);
					break;
				case ')':
					gotParen(ch);
					break;
				default:
					output += ch;
					break;
			}
		}
		while(!theStack.isEmpty()) {
			output += theStack.pop();
		}
		return output;
	}

	public void gotOper(char opThis, int prec1) {
		while(!theStack.isEmpty()) {
			char opTop = theStack.pop();
			if(opTop == '(') {
				theStack.push(opTop);
				break;
			}
			else {
				int prec2;
				if(opTop == '+' || opTop == '-')
					prec2 = 1;
				else
					prec2 = 2;
				if(prec1 <= prec2)
					output += opTop;
				else {
					theStack.push(opTop);
					break;
				}
			}
		}
		theStack.push(opThis);
	}

	public void gotParen(char ch) {
		while(!theStack.isEmpty()) {
			char opTop = theStack.pop();
			if(opTop == '(')
				break;
			else {
				output += opTop;
			}
		}
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


