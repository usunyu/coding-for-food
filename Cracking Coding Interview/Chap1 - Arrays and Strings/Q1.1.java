/*
Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
*/

import java.util.*;

class Solution {
	/*
	static String testStr = "ABCDEFGD";
	public static void main(String[] args) {
		System.out.println(testStr);
		// using Hash Set
		HashSet<Character> set = new HashSet<Character>();
		boolean unique = true;
		for(int i = 0; i < testStr.length(); i++) {
			char ch = testStr.charAt(i);
			if(!set.contains(ch))
				set.add(ch);
			else {
				unique = false;
				break;
			}
		}
		if(unique)
			System.out.println("This is a string has all unique characters.");
		else
			System.out.println("This is not a string has all unique characters.");

		// not using data structures
		unique = true;
		for(int i = 0; i < testStr.length(); i++) {
			for(int j = i + 1; j < testStr.length(); j++) {
				if(testStr.charAt(i) == testStr.charAt(j)) {
					unique = false;
					break;
				}
			}
			if(!unique)
				break;
		}
		if(unique)
			System.out.println("This is a string has all unique characters.");
		else
			System.out.println("This is not a string has all unique characters.");
	}
	*/
	/*
		Second Round
	*/
	public static boolean isUniqueChars(String str) {	// assume all the char is in lower case
		int bits = 0;
		for(int i = 0; i < str.length(); i++) {
			int shift = str.charAt(i) - 'a';
			int digit = 1 << shift;
			if((bits & digit) != 0) return false;	// we have same char before
			bits |= digit;
		}
		return true;
	}

	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		for (String word : words) {
			System.out.println(word + ": " + isUniqueChars(word));
		}
	}
}



