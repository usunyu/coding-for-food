/*
Implement a method to perform basic string compression using the counts of repeated characters. 
For example, the string aabcccccaaa would become a2blc5a3. If the "compressed" string would not 
become smaller than the original string, your method should return the original string.
*/

import java.util.*;

class Solution {
	/*
	public static String compression(String str) {
		if(str == null || str.isEmpty())
			return str;
		StringBuffer sb = new StringBuffer();
		int cont = 1;
		char lastCh = str.charAt(0);
		sb.append(lastCh);
		boolean compress = true;
		for(int i = 1; i < str.length(); i++) {
			if(lastCh != str.charAt(i)) {
				sb.append(cont);
				cont = 0;
				lastCh = str.charAt(i);
				sb.append(lastCh);
			}
			if(sb.length() > str.length()) {
				compress = false;
				break;
			}
			cont++;
		}
		sb.append(cont);
		if(compress)
			str = sb.toString();
		return str;
	}

	public static void main(String[] args) {
		String testStr = "aabcccccaaa";
		System.out.println(testStr);
		testStr = compression(testStr);
		System.out.println(testStr);
	}
	*/
	/*
		Second Round
	*/
	public static String compression(String str) {
		if(str == null || str.length() == 0) return str;
		StringBuffer sb = new StringBuffer();
		int count = 1;
		char last = str.charAt(0);
		sb.append(last);
		for(int i = 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(ch == last) count++;
			else {
				sb.append(count);
				sb.append(ch);
				last = ch;
				count = 1;
			}
			if(i == str.length() - 1) sb.append(count);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String str = "abbccccccde";
		String str2 = compression(str);
		System.out.println("Old String (len = " + str.length() + "): " + str);
		System.out.println("New String (len = " + str2.length() + "): " + str2);
	}
}

