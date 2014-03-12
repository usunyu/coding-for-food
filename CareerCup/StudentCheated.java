/*
A professor wants to see if two students have cheated when writing a paper. Design a function : 
hasCheated(String s1,String s2, int N) 
that evaluates to true if two strings have a common substring of length N. 
Additional question after implementation. 
Assume you don't have the possibility of using String.contains() and String.substring(). 
How would you implement this?

http://www.careercup.com/question?id=6196366774632448
http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
*/

class Solution {
	public static boolean kmp(String str1, String str2) {
		// preprocess
		int[] lps = new int[str2.length()];
		int i = 1, match = 0;
		while(i < str2.length()) {
			if(str2.charAt(i) == str2.charAt(match)) {
				match++;
				lps[i++] = match;
			}
			else if(match > 0) {
				match = lps[match - 1];
			}
			else i++;
		}
		// kmp
		int j = 0; i = 0;
		while(i < str1.length()) {
			if(str1.charAt(i) == str2.charAt(j)) {
				i++; j++;
			}
			else if(j > 0) {
				j = lps[j - 1];
			}
			else i++;
			if(j == str2.length()) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasCheated(String str1, String str2, int N) {
		for(int i = 0; i < str2.length() - N; i++) {
			String pattern = str2.substring(i, i + N);
			if(kmp(str1, pattern)) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String s1 = "adsfgdsfjhgdsahjagjhds4234gjagsadsasdasdhj";
		String s2 = "sdfsadfasqadcxzxdfa4234sdas";
		int N = 4;
		System.out.println(hasCheated(s1, s2, N));
	}
}