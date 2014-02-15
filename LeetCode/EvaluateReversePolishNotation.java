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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println((solution.evalRPN(tokens)));
    }
}
