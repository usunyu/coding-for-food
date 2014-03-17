/*
Implement a function void reversefchar* str) in Cor C++ which reverses a null-termi- nated string.
*/

class Solution {
	/*
	public static String reverse(String str) {
		StringBuffer sb = new StringBuffer(str);
		for(int i = 0, j = sb.length() - 1; i < j; i++, j--) {
			char temp = sb.charAt(i);
			sb.setCharAt(i, sb.charAt(j));
			sb.setCharAt(j, temp);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String testStr = "ABCDEFG";
		System.out.println(testStr);
		testStr = reverse(testStr);
		System.out.println(testStr);
	}
	*/
	/*
		Second Round
	*/
	public static String reverse(String str) {
		StringBuilder sb = new StringBuilder(str);
		for(int i = 0, j = sb.length() - 1; i < j; i++, j--) {
			char tmp = sb.charAt(j);
			sb.setCharAt(j, sb.charAt(i));
			sb.setCharAt(i, tmp);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String[] input = {"abcde", "cat"};
		for(int i = 0; i < 2; i++) {
			System.out.println("reversing the string: " + input[i]);
			System.out.println("reverse of input string is " + reverse(input[i]));
    	}
	}
}