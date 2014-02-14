import java.io.*;
import java.util.Stack;

class FormulaCal {
	private String input;
	private Stack <Integer> operandStack;
	private Stack <Character> operatorStack;

	public FormulaCal(String in) {
		input = in;
		operatorStack = new Stack <Character> ();
		operandStack = new Stack <Integer> ();
	}

	public int doCal() {
		String operandTemp = "";
		for(int i = 0; i < input.length(); i++) {
			char chThis = input.charAt(i);
			// store the operand
			if(chThis <= '9' && chThis >= '0') {
				operandTemp += chThis;
				if(i == input.length() - 1) {
					int operandValue = Integer.parseInt(operandTemp);
					operandStack.push(operandValue);
				}
			}
			else { // counter the operator
				// first push the operand
				if(!operandTemp.equals("")) {
					int operandValue = Integer.parseInt(operandTemp);
					operandStack.push(operandValue);
					operandTemp = "";
				}
				// if the operatorStack is empty, push the operator directly
				while(!operatorStack.empty()) {
					char chTop = operatorStack.peek();
					if(chTop == '+' || chTop == '-') {
						// do the calculate
						if(chThis == '+' || chThis == '-') {
							int num1 = operandStack.pop();
							int num2 = operandStack.pop();
							chTop = operatorStack.pop();
							int cal = 0;
							if(chTop == '+') {
								cal = num2 + num1;
								operandStack.push(cal);
							}
							if(chTop == '-') {
								cal = num2 - num1;
								operandStack.push(cal);
							}
						}
						else {
							break;
						}
					}
					if(chTop == '*') {
						int num1 = operandStack.pop();
						int num2 = operandStack.pop();
						chTop = operatorStack.pop();
						int cal = num2 * num1;
						operandStack.push(cal);
					}
				}
				operatorStack.push(chThis);
			}
		}
		while(!operatorStack.empty()) {
			char ch = operatorStack.pop();
			int num1 = operandStack.pop();
			int num2 = operandStack.pop();
			int cal = 0;
			switch(ch) {
				case '+':
					cal = num2 + num1;
					operandStack.push(cal);
					break;
				case '-':
					cal = num2 - num1;
					operandStack.push(cal);
					break;
				case '*':
					cal = num2 * num1;
					operandStack.push(cal);
					break;
			}
		}
		return operandStack.pop();
	}
}

class FormulaCalApp {
	public static void main(String[] args) {
		String input = "2*23+6*8+6-3";
		System.out.println(input);
		FormulaCal fc = new FormulaCal(input);
		int cal = fc.doCal();
		System.out.println(cal);
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
}





