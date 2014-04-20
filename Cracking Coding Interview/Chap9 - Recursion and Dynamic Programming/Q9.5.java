/*
Write a method to compute all permutations of a string.
*/
import java.util.*;

class Solution {
	public static String shift(String str, int index) {
		StringBuffer stb = new StringBuffer();
		// append the original until index
		for(int i = 0; i < index; i++) {
			char ch = str.charAt(i);
			stb.append(ch);
		}
		// shift
		for(int i = index + 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			stb.append(ch);
		}
		char end = str.charAt(index);
		stb.append(end);
		return stb.toString();
	}

	public static void permutations(String str, int index) {
		StringBuffer sBuffer;
		if(index == str.length() - 1) {
			System.out.println(str);
			return;
		}
		for(int i = index; i < str.length(); i++) {
			permutations(str, index + 1);
			str = shift(str, index);
		}
	}

	public static void permutations(String str) {
		permutations(str, 0);
	}

	public static void main(String[] args) {
		String str = "ABC";
		permutations(str);
	}
}
/*
	Second Round
*/
class Solution2 {
	private static void swap(StringBuilder sb, int i, int j) {
		char tmp = sb.charAt(i);
		sb.setCharAt(i, sb.charAt(j));
		sb.setCharAt(j, tmp);
	}

	private static void permutations(StringBuilder sb, int index, ArrayList<String> result) {
		if(index == sb.length()) result.add(sb.toString());
		else {
			for(int i = index; i < sb.length(); i++) {
				swap(sb, i, index);
				permutations(sb, index + 1, result);
				swap(sb, i, index);	// backtracking
			}
		}
	}

	public static ArrayList<String> permutations(String str) {
		ArrayList<String> result = new ArrayList<String>();
		permutations(new StringBuilder(str), 0, result);
		return result;
	}

	public static void main(String[] args) {
		String str = "ABC";
		for(String s : permutations(str))
			System.out.println(s);
	}
}
