/*
Write a method to count the number of 2s between 0 and n.
*/

class Solution {
	public static int count2sInRangeAtDigit(int number, int d) {
		int powerOf10 = (int) Math.pow(10, d);
		int nextPowerOf10 = powerOf10 * 10;
		int right = number % powerOf10;

		int roundDown = number - number % nextPowerOf10;
		int roundUp = roundDown + nextPowerOf10;

		int digit = (number / powerOf10) % 10; 
		if (digit < 2) { // if the digit in spot digit is 
			return roundDown / 10;
		} else if (digit == 2) {
			return roundDown / 10 + right + 1;
		} else {
			return roundUp / 10;
		}
	}

	public static int count2sInRange(int number) {
		int count = 0;
		int len = String.valueOf(number).length();
		for (int digit = 0; digit < len; digit++) {
			count += count2sInRangeAtDigit(number, digit);
		}
		return count;
	}
	/*
		Second Round
	*/
	public static int count2sInRange2(int number) {
		int count = 0;
		count += number / 10;
		count += (number % 10 >= 2 ? 1 : 0);
		int digit = 0, n = number;
		while(n >= 10) {
			digit++;
			n /= 10;
		}
		int base = 20, add = 100, cal = base;
		for(int i = 0; i < digit; i++) {
			while(number >= cal) {
				count += (number - cal + 1 >= (add / 10) ? (add / 10) : number - cal + 1);
				cal += add;
			}
			base *= 10;
			add *= 10;
			cal = base;;
		}
		return count;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			int v = count2sInRange(i);
			int n = count2sInRange2(i);
			if(n != v)
				System.out.println("Between 0 and " + i + ": " + v + " != " + n);
		}
	}
}