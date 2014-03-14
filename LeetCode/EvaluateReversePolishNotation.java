/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> operand = new Stack<Integer>();
        for(int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            int op1, op2;
            switch(token) {
                case "+":
                    op1 = operand.pop();
                    op2 = operand.pop();
                    op2 += op1;
                    operand.push(op2);
                    break;
                case "-":
                    op1 = operand.pop();
                    op2 = operand.pop();
                    op2 -= op1;
                    operand.push(op2);
                    break;
                case "*":
                    op1 = operand.pop();
                    op2 = operand.pop();
                    op2 *= op1;
                    operand.push(op2);
                    break;
                case "/":
                    op1 = operand.pop();
                    op2 = operand.pop();
                    op2 /= op1;
                    operand.push(op2);
                    break;
                default:
                    op1 = Integer.parseInt(token);
                    operand.push(op1);
                    break;
            }
        }
        return operand.pop();
    }
    /*
        Second Round
    */
    public int evalRPN2(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for(String token : tokens) {
            int op1, op2;
            switch(token) {
                case "+":
                    op1 = stack.pop();
                    op2 = stack.pop();
                    stack.push(op2 + op1);
                    break;
                case "-":
                    op1 = stack.pop();
                    op2 = stack.pop();
                    stack.push(op2 - op1);
                    break;
                case "*":
                    op1 = stack.pop();
                    op2 = stack.pop();
                    stack.push(op2 * op1);
                    break;
                case "/":
                    op1 = stack.pop();
                    op2 = stack.pop();
                    stack.push(op2 / op1);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println((solution.evalRPN2(tokens)));
    }
}
