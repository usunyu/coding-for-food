/*
Modify the following code to add a row number for each line is printed


public class Test {
	public static void main(String [] args){
		printParenthesis(3);
	}
	public static void printParenthesis(int n){
		char buffer[] = new char[n*2];
		printP(buffer,0,n,0,0);
	}
	public static void printP(char buffer[], int index, int n, int open, int close){
		if(close == n){
			System.out.println(new String(buffer));
		}else{
			if(open > close){
				buffer[index] = ']';
				printP(buffer, index+1, n, open, close+1);
			}
			if(open < n ){
				buffer[index] = '[';
				printP(buffer,index+1,n,open+1,close);
			}
		}
	}
}

Expected Output:

1.[][][]
2.[][[]]
3.[[]][]
4.[[][]]
5.[[[]]]
*/

class Main {
	static int count;
	
	public static void main(String [] args){
		printParenthesis(3);
	}
	public static void printParenthesis(int n){
		char buffer[] = new char[n*2];
		count = 0;
		printP(buffer,0,n,0,0);
	}
	public static void printP(char buffer[], int index, int n, int open, int close){
		if(close == n){
			System.out.println(++count + "." + new String(buffer));
		}else{
			if(open > close){
				buffer[index] = ']';
				printP(buffer, index+1, n, open, close+1);
			}
			if(open < n ){
				buffer[index] = '[';
				printP(buffer,index+1,n,open+1,close);
			}
		}
	}
}