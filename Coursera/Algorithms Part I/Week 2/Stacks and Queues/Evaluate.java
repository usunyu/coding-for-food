public class Evaluate {
	public static void main(String[] args) {
		if(args.length == 0) {
			System.err.println("Please input expression. e.g. '(1+((2+3)*(4*5)))'");
			System.exit(0);
		}
		String expression = args[0];
		Stack<Character> ops = new Stack<Character>();
		Stack<Double> vals = new Stack<Double>();
		for(int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			switch(ch) {
				case '(':
					break;
				case '+':
					ops.push(ch);
					break;
				case '*':
					ops.push(ch);
					break;
				case ')':
					char op = ops.pop();
					if(op == '+')
						vals.push(vals.pop() + vals.pop());
					else if(op == '*')
						vals.push(vals.pop() * vals.pop());
					break;
				default:
					vals.push((double)(ch - '0'));
			}
		}
		System.out.println(vals.pop());
	}
}