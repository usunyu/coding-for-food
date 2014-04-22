/*
Write a function that adds two numbers. You should not use + or any arithmetic
operators.
*/

class Solution {
	public static int add(int a, int b) {
		if(b == 0) return a;
		int sum = a ^ b;			// add without carrying
		int carry = (a & b) << 1;	// carry, but don't add
		return add(sum, carry);		// recurse
	}

	public static void main(String[] args) {
		System.out.println(add(759, 674));
	}
}
/*
	Second Round
*/
class Solution2 {
	public static int add(int a, int b) {
		int carry, sum = a;
		while(b != 0) {
			carry = ((a & b) << 1);
			sum = a ^ b;
			a = sum;
			b = carry;
		}
		return sum;
	}

	public static void main(String[] args) {
		System.out.println(add(759, 674));
	}
}