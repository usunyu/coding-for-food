/*
String s is called unique if all the characters of s are different.

String s2 is producible from string s1, if we can remove some characters of s1 to obtain s2.

String s1 is more beautiful than string s2 if length of s1 is more than length of s2 or they have equal length and s1 is 
lexicographically greater than s2.

Given a string s you have to find the most beautiful unique string that is producible from s.

Input:

First line of input comes a string s having no more than 1,000,000(10^6) characters. all the characters of s are lowercase 
english letters.

Output:

Print the most beautiful unique string that is producable from s

Sample Input:

babab 

Sample Output:

ba

Explanation

In the above test case all unique strings that are producible from s are "ab" and "ba" and "ba" is more beautiful than "ab".
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

class Solution {
	static String input;
	static String output;
	static boolean findBeauty = false;
	
	private static void input(String filePath) {
		// assume the file is in the right format
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			input = sc.nextLine();
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void input() {
		// assume the file is in the right format
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();
		sc.close();
	}
	
	private static void output() {
		System.out.println(output);
	}
	
	public static void shift(StringBuilder sb, int start) {
        char temp = sb.charAt(start);
        for(int i = start; i < sb.length() - 1; i++) {
            sb.setCharAt(i, sb.charAt(i + 1));
        }
        sb.setCharAt(sb.length() - 1, temp);
    }
	
	public static void permute(StringBuilder sb, int start) {
		if(findBeauty) return;
        if(start == sb.length() - 1) {
        	String str = sb.toString();
        	if(isValid(str)) {
        		output = str;
        		findBeauty = true;
        	}
            return;
        }
        for(int i = start; i < sb.length(); i++) {
            StringBuilder temp = new StringBuilder(sb);
            permute(temp, start + 1);
            shift(sb, start);
        }
    }
	
	private static void permute(StringBuilder sb) {
		permute(sb, 0);
	}
	
	// check if current str is valid 
	private static boolean isValid(String str) {
		int i = 0, j = 0;
		while(i < str.length() && j < input.length()) {
			// find char of str in input
			char ch = str.charAt(i);
			while(j < input.length() && input.charAt(j) != ch) j++;
			if(j < input.length()) i++;
		}
		if(i == str.length()) return true;
		else return false;
	}
	
	private static void process() {
		HashSet<Character> charSet = new HashSet<Character>();
		for(int i = 0; i < input.length(); i++) {
			charSet.add(input.charAt(i));
		}
		// process
		StringBuilder sb = new StringBuilder();
		// first come up with largest possible beauty
		for(char ch : charSet) {
			if(sb.length() == 0) {
				sb.append(ch);
			}
			else {
				boolean inserted = false;
				for(int i = 0; i < sb.length() && !inserted; i++) {
					if(ch > sb.charAt(i)) {
						sb.insert(i, ch);
						inserted = true;
					}
				}
				if(!inserted) sb.append(ch);
			}
		}
		// permute, and check valid
		permute(sb);
	}
	
	// Greedy Solution
	// http://www.careercup.com/question?id=14463150
	/*
	 * Algorithm : 
	 * Let P[] has the required unique and beautiful string and let s[] has the input string. 
	 * For each character c in s do the following steps :- 
	 * check for occurrence of c in P. 
	 * if(not found) 
	 * 		append c in P; 
	 * else { 
	 * 		p1=position of c in P; 
	 * 		find character c1 in P after p1 which is lexicographically greater than c; 
	 * 		if(c1 found) { 
	 * 			p2=position of c1 in P; 
	 * 			see whether characters between p1 and p2 are repeated in input string after c; 
	 * 			if(yes) { 
	 * 				shift all characters from p1+1 to left by 1 position in P; 
	 * 				P[end]=c; 
	 * 			} 
	 * 		} 
	 *	} 
	 */
	private static void process2() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			// check for occurrence of c in P. 
			int pos = posInSb(ch, sb);
			if(pos == -1) {	// if not found 
				sb.append(ch);
			}
			else {
				// find character c1 in P after p1 which is lexicographically greater than c
				int pos2 = findLarPos(pos + 1, ch, sb);
				if(pos2 != -1) { // if c1 found
					// see whether characters between p1 and p2 are repeated in input string after c
					if(repeated(i, pos, pos2, sb)) { // if yes
						// shift all characters from p1+1 to left by 1 position in P
						sb.deleteCharAt(pos);
						sb.append(ch);
					}
				}
			}
		}
		output = sb.toString();
	}
	
	private static boolean repeated(int c, int pos1, int pos2, StringBuilder sb) {
		HashSet<Character> charSet = new HashSet<Character>();
		for(int i = c + 1; i < input.length(); i++) {
			charSet.add(input.charAt(i));
		}
		for(int i = pos1 + 1; i < pos2; i++) {
			if(!charSet.contains(sb.charAt(i))) return false;
		}
		return true;
	}
	
	private static int findLarPos(int index, char ch, StringBuilder sb) {
		int ret = -1;
		for(int i = index; i < sb.length(); i++) {
			if(sb.charAt(i) > ch) {
				ret = i;
				break;
			}
		}
		return ret;
	}
	
	private static int posInSb(char ch, StringBuilder sb) {
		int ret = -1;
		if(sb.length() == 0) return ret;
		for(int i = 0; i < sb.length(); i++) {
			if(sb.charAt(i) == ch) {
				ret = i;
				break;
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		if (args.length == 0) input();
		else input(args[0]);
		process2();
		output();
	}
}