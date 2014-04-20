/*
Given a boolean expression consistingof the symbols 0, 1, &, |, and ^, and a desired boolean result value result, 
implement a function to count the number of ways of parenthesizing the expression such that it evaluates to result.
*/

import java.util.*;

class SubSolution {
	String subExp;
	int index;

	public SubSolution(String subExp, int index) {
		this.subExp = subExp;
		this.index = index;
	}
}

class Solution {

	public static int calSubExp(String expression) {
		int n1 = Character.getNumericValue(expression.charAt(0));
		char sign = expression.charAt(1);
		int n2 = Character.getNumericValue(expression.charAt(2));
		int res = -1;
		if(sign == '^')
			res = n1 ^ n2;
		if(sign == '|')
			res = n1 | n2;
		if(sign == '&')
			res = n1 & n2;
		return res;
	}

	public static boolean isParentheses(char ch) {
		if(ch == '(' || ch == ')')
			return true;
		else
			return false;
	}

	public static void printExp(ArrayList<SubSolution> solutions) {
		String expression = "0";
		for(int i = solutions.size() - 1; i >= 0; i--) {
			SubSolution subSol = solutions.get(i);
			int index = subSol.index;
			String subExp = subSol.subExp;

			StringBuffer buffer = new StringBuffer();

			for(int k = 0, j = 0; k < expression.length(); k++) {
				char ch = expression.charAt(k);
				
				if(isParentheses(ch)) {
					buffer.append(ch);
				}
				else {
					if(j == index) {
						buffer.append("(" + subExp + ")");
					}
					else
						buffer.append(ch);
					j++;
				}
			}

			expression = buffer.toString();
		}
		System.out.println(expression);
	}

	public static void countExpression(String expression, int result, ArrayList<SubSolution> solutions) {
		for(int i = 1; i < expression.length(); i += 2) {
			String subExp = expression.substring(i - 1, i + 2);
			int subRes = calSubExp(subExp);
			String newExp = expression.substring(0, i - 1) + subRes + expression.substring(i + 2);
			// record sub cal
			ArrayList<SubSolution> newSolutions = new ArrayList<SubSolution>(solutions);
			SubSolution subSol = new SubSolution(subExp, i - 1);
			newSolutions.add(subSol);

			if(newExp.length() == 1) {	// final
				int res = Integer.parseInt(newExp);
				if(res == result) {
					System.out.print("Find solution: ");
					printExp(newSolutions);
				}
			}
			else {
				countExpression(newExp, result, newSolutions);
			}
		}
	}

	public static void main(String[] args) {
		String expression = "0^0|1&1^1|0|1";
		ArrayList<SubSolution> solutions = new ArrayList<SubSolution>();
		countExpression(expression, 1, solutions);
	}
}