/*
Given two strings,write a method to decide if one is a permutation of the other.
*/

import java.util.*;

class Solution {
	/*
	// test if str1 is permutation of str2
	public static boolean permutation(String str1, String str2) {
		int length = str1.length();
		if(length != str2.length())
			return false;
		int array1[] = new int[256];
		int array2[] = new int[256];
		for(int i = 0; i < length; i++) {
			int c1 = str1.charAt(i);
			int c2 = str2.charAt(i);
			array1[c1]++;
			array2[c2]++;
		}
		// check
		for(int i = 0; i < 256; i++)
			if(array1[i] != array2[i])
				return false;

		return true;
	}

	public static void main(String[] args) {
		String str1 = "ABCDEFG";
		String str2 = "CGBEDFD";
		System.out.println(str1);
		System.out.println(str2);
		if(permutation(str1, str2))
			System.out.println("True");
		else
			System.out.println("False");
	}
	*/
	/*
		Second Round
	*/
	public static boolean permutation(String s, String t) {
		if(s.length() != t.length()) return false;
		// compare hash value
		int add1 = 0, mul1 = 1, add2 = 0, mul2 = 1;
		for(int i = 0; i < s.length(); i++) {
			int v1 = s.charAt(i) - 'a', v2 = t.charAt(i) - 'a';
			add1 += v1; add2 += v2;
			mul1 *= v1; mul2 *= v2;
		}
		int hash1 = add1 + mul1, hash2 = add2 + mul2;
		return hash1 == hash2;
	}

	public static void main(String[] args) {
		String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}, {"aaaa", "bbbb"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean anagram = permutation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + anagram);
		}
	}
}

