/*
input [2,3,1,4] 
output [12,8,24,6] 

Multiply all fields except it's own position. 

Restrictions: 
1. no use of division 
2. complexity in O(n)

http://www.careercup.com/question?id=5179916190482432
*/

class Main {
	// break restriction (1)
	// time : O(n) space : O(1)
	public static void multiply(int[] input) {
		int front = 1, end = 1;
		// get end
		for(int i = input.length - 1; i >= 0; i--) {
			end *= input[i];
		}
		// set
		for(int i = 0; i < input.length; i++) {
			int tmp = input[i];
			end /= tmp;
			input[i] = front * end;
			front *= tmp;
		}
	}

	// time : O(n) space : O(n)
	public static void multiply2(int[] input) {
		int[] product = new int[input.length];
		int p = 1;
		for(int i = input.length - 1; i >= 0; i--) {
			p = p * input[i];
			product[i] = p;
		}
		p = 1;
		for(int i = 0; i < input.length; i++) {
			int tmp = p * input[i];
			input[i] = p * (i < input.length - 1 ? product[i + 1] : 1);
			p = tmp;
		}
	}

	public static void print(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] input = {2,3,1,4};
		print(input);
		multiply2(input);
		print(input);
	}
}