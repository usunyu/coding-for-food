/*
Given any integer, print an English phrase that describes the integer 
(e.g., "One Thousand, Two Hundred Thirty Four").
*/

class Solution {
	public static String[] digits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	public static String[] teens = {"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	public static String[] tens = {"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	public static String[] bigs = {"", "Thousand", "Million"};

	public static String convert(int num) {
		if(num == 0) {
			return "Zero";
		}

		if(num < 0) {
			return "Negative " + convert(-1 * num);
		}

		int count = 0;
		String str = "";
		while(num > 0) {
			if(num % 1000 != 0) {
				str = convert100(num % 1000) + bigs[count] + " " + str;
			}
			num /= 1000;
			count++;
		}
		return str;
	}

	public static String convert100(int num) {
		String str = "";
		/* Convert hundreds place */
		if(num >= 100) {
			str += digits[num / 100 - 1] + " Hundred ";
			num %= 100;
		}
		/* Convert tens place */
		if(num >= 20 || num == 10) {
			str += tens[num / 10 - 1] + " ";
			num %= 10;
		}
		else if(num >= 11 && num <= 19) {
			str += teens[num - 11] + " ";
			return str;
		}
		/* Convert ones place */
		if(num >= 1 && num <= 9) {
			str += digits[num - 1] + " ";
		}
		return str;
	}

	public static void main(String[] args) {
		int value = 991239928;
		String s = convert(value);
		System.out.println(value + ": " + s);
	}
}