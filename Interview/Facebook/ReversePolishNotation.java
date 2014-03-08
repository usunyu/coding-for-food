/*
An expression consisting of operands and binary operators can be written in Reverse Polish Notation (RPN) by 
writing both the operands followed by the operator. For example, 3 + (4 * 5) can be written as "3 4 5 * +".
 
You are given a string consisting of x's and *'s. x represents an operand and * represents a binary operator. 
It is easy to see that not all such strings represent valid RPN expressions. For example, the "x*x" is not a valid 
RPN expression, while "xx*" and "xxx**" are valid expressions. What is the minimum number of insert, delete and 
replace operations needed to convert the given string into a valid RPN expression?
 
Input:
The first line contains the number of test cases T. 
T test cases follow. Each case contains a string consisting only of characters x and *.
 
Output:
Output T lines, one for each test case containing the least number of operations needed.
 
Constraints:
1 <= T <= 100
The length of the input string will be at most 100.
 
Sample Input:
5
x
xx*
xxx**
*xx
xx*xx**
 
Sample Output:
0
0
0
2
0
 
Explanation:
 
For the first three cases, the input expression is already a valid RPN, so the answer is 0.
For the fourth case, we can perform one delete, and one insert operation: *xx -> xx -> xx*
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class Solution {
	static int T;
	static ArrayList<String> testCases = new ArrayList<String>();
	static ArrayList<Integer> minOps = new ArrayList<Integer>();
	
	private static boolean isValidRPN(String expression) {
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if(ch == 'x') {	// operand
				stack.push(ch);
			}
			else {	// operator
				if(stack.size() < 2) return false;
				char opend1 = stack.pop();
				char opend2 = stack.pop();
				if(opend1 == '*' || opend2 == '*') return false;
				stack.push('x');
			}
		}
		if(stack.size() == 1 && stack.peek() == 'x') return true;
		else return false;
	}
	
	// BFS
	// Time Limit Exceeded
	private static int getMinOp(StringBuilder expression) {
		LinkedList<StringBuilder> queue = new LinkedList<StringBuilder>();
		queue.add(expression);
		int ops = 0;
		boolean success = false;
		while(!queue.isEmpty() && !success) {
			LinkedList<StringBuilder> tmpQueue = new LinkedList<StringBuilder>();
			ops++;
			while(!queue.isEmpty() && !success) {
				StringBuilder sb = queue.poll();
				// delete case
				for(int i = 0; i < sb.length() && !success; i++) {
					StringBuilder nsb = new StringBuilder(sb);
					nsb.deleteCharAt(i);
					if(isValidRPN(nsb.toString())) {
						success = true;
					}
					else {
						tmpQueue.add(nsb);
					}
				}
				// replace case
				for(int i = 0; i < sb.length() && !success; i++) {
					StringBuilder nsb = new StringBuilder(sb);
					if(nsb.charAt(i) == '*') {
						nsb.setCharAt(i, 'x');
					}
					else {
						nsb.setCharAt(i, '*');
					}
					if(isValidRPN(nsb.toString())) {
						success = true;
					}
					else {
						tmpQueue.add(nsb);
					}
				}
				// insert case
				for(int i = 0; i < sb.length() && !success; i++) {
					StringBuilder nsb = new StringBuilder(sb);
					nsb.insert(i, '*');
					if(isValidRPN(nsb.toString())) {
						success = true;
					}
					else {
						tmpQueue.add(nsb);
					}
					nsb = new StringBuilder(sb);
					if(isValidRPN(nsb.toString())) {
						success = true;
					}
					else {
						tmpQueue.add(nsb);
					}
				}
			}
			queue = tmpQueue;
		}
		return ops;
	}
	
	// http://stackoverflow.com/questions/10013933/conversion-to-proper-postfix-notation-in-minimum-number-of-steps
	/*
		|
		|         +
		|   +   +   +
		| +   +       +
		|-|-|-|-|-|-|-|-
		  x x * x x * * 
	*/
	private static int getMinOp2(StringBuilder expression) {
		int[] graph = new int[expression.length()];
		int current = 0;
		for(int i = 0; i < expression.length(); i++) {
			if(expression.charAt(i) == 'x') current++;
			else current--;
			graph[i] = current;
		}
		int ops = 0;
		// remove all prev *
		int i;
		for(i = 0; i < graph.length && graph[i] < 0; i++) {
			ops += 1;
			for(int j  = i; j < graph.length; j++) {
				graph[j] += 1;
			}
		}
		// fix middle
		for(; i < graph.length; i++) {
			if(graph[i] <= 0) {
				int j = i;
				int min = 0;
				while(graph[j] <= 0) {
					if(graph[j] < min) min = graph[j];
				}
				int op = 1 - min + 1;
				i = j;
				for(; j < graph.length; j++) {
					graph[j] += 1;
				}
				ops += op;
			}
		}
		// set end to 1
		if(graph[graph.length - 1] != 1) {
			ops += Math.abs(1 - graph[graph.length - 1]);
			graph[graph.length - 1] = 1;
		}
		return ops;
	}
	
	private static int getMinOp(String expression) {
		StringBuilder sb = new StringBuilder(expression);
		return getMinOp2(sb);
	}
	
	private static void RPN() {
		for(String expression : testCases) {
			if(isValidRPN(expression)) {
				minOps.add(0);
			}
			else {
				minOps.add(getMinOp(expression));
			}
		}
	}
	
	private static void input(String filePath) {
		// assume the file is in the right format
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			// read first line for T
			String line = sc.nextLine();
			T = Integer.parseInt(line);
			// read test cases
			while(sc.hasNext()) {
				// read N and K
				line = sc.nextLine();
				testCases.add(line);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void input() {
		// assume the file is in the right format
		Scanner sc = new Scanner(System.in);
		// read first line for T
		String line = sc.nextLine();
		T = Integer.parseInt(line);
		// read test cases
		while(sc.hasNext()) {
			// read N and K
			line = sc.nextLine();
			testCases.add(line);
		}
		sc.close();
	}
	
	private static void output() {
		for(int op : minOps) {
			System.out.println(op);
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			input();
		}
		else {
			input(args[0]);
		}
		RPN();
		output();
	}
}