/*
Given two strings a and b, find whether any anagram of string a is a sub-string of string b. For eg: 
if a = xyz and b = afdgzyxksldfm then the program should return true.
*/
import java.util.HashMap;

class Solution {
	// every char is unique ?
	// if not, we need map, if yes, we can use bit to optimize
	public static boolean anagramSubstring(String a, String b) {
		// preprocess
		HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
		for(int i = 0; i < a.length(); i++) {
			char ch = a.charAt(i);
			if(charMap.containsKey(ch)) {
				charMap.put(ch, charMap.get(ch) + 1);
			}
			else {
				charMap.put(ch, 1);
			}
		}
		// check, not correct now!
		HashMap<Character, Integer> cache = new HashMap<Character, Integer>(charMap);
		for(int i = 0; i < b.length(); i++) {
			if(cache.size() == 0) return true;	// all char found
			char ch = b.charAt(i);
			if(cache.containsKey(ch)) {
				int val = cache.get(ch);
				if(val == 1) {
					cache.remove(ch);
				}
				else {
					cache.put(ch, val - 1);
				}
			}
			else {
				cache = new HashMap<Character, Integer>(charMap);	// recalculate
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(anagramSubstring("xyz", "afdgzyxksldfm"));
	}
}