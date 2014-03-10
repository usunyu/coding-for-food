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

	public static void print(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + "\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] input = {2,3,1,4};
		print(input);
		multiply(input);
		print(input);
	}
}