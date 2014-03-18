/*
Assume you have a method isSubstring which checks if one word is a substring of another.
Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one call
to isSubstring (e.g.,"waterbottle" is a rotation of "erbottlewat").
*/

import java.util.*;

class Solution {
	/*
	public static boolean isSubstring(String s1, String s2) {
		if(s1.length() < s2.length())
			return false;
		boolean isSub = false;
		int j = 0;
		for(int i = 0; i < s1.length(); i++) {
			if(s1.charAt(i) == s2.charAt(j))
				j++;
			else
				j = 0;
			if(j == s2.length()) {
				isSub = true;
				break;
			}
		}
		return isSub;
	}

	public static boolean isRotation(String s1, String s2) {
		if(s1.length() != s2.length())
			return false;
		String s1s1 = s1 + s1;
		if(isSubstring(s1s1, s2))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		String testStr1 = "waterbottle";
		String testStr2 = "erbottlewat";
		System.out.println(testStr1);
		System.out.println(testStr2);
		if(isRotation(testStr1, testStr2))
			System.out.println("Is Rotation.");
		else
			System.out.println("Not rotation.");
	}
	*/
	/*
		Second Round
	*/
	public static boolean isSubstring(String s1, String s2) {
		if(s2.length() > s1.length()) return false;
		// preprocess
		int[] ls = new int[s2.length()];
		int i = 1, length = 0;
		while(i < s2.length()) {
			if(s2.charAt(i) == s2.charAt(length)) {
				ls[i++] = ++length;
			}
			else if(length > 0) {
				length = ls[length - 1];
			}
			else i++;
		}
		// kmp
		i = 0; int j = 0;
		while(i < s1.length()) {
			if(s1.charAt(i) == s2.charAt(j)) {
				i++; j++;
			}
			else if(j > 0) {
				j = ls[j - 1];
			}
			else i++;
			if(j == s2.length()) return true;
		}
		return false;
	}

	public static boolean isRotation(String s1, String s2) {
		int len = s1.length();
	    /* check that s1 and s2 are equal length and not empty */
	    if (len == s2.length() && len > 0) { 
	    	/* concatenate s1 and s1 within new buffer */
	    	String s1s1 = s1 + s1;
	    	return isSubstring(s1s1, s2);
	    }
	    return false;
	}

	public static void main(String[] args) {
		String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean is_rotation = isRotation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + is_rotation);
		}
	}
}