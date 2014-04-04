/*
Write a program that breaks up a string of words with no spaces into a string with appropriate spaces.
*/

import java.util.HashSet;
import java.util.ArrayList;

class Main {
	public static String breakStringWithSpace(String str, HashSet<String> dict) {
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> spaces = new ArrayList<Integer>();
		for(int i = 0; i <= str.length(); i++) {
			if(i == str.length()) {
				if(dict.contains(sb.toString())) {	// success
					StringBuilder ret = new StringBuilder(str);
					for(int j = spaces.size() - 1; j >= 0; j--) {
						ret.insert(spaces.get(j), " ");
					}
					return ret.toString();
				}
				else {	// backtracking
					if(spaces.size() == 0) break;	// fail, no way to backtracking
					int last = spaces.get(spaces.size() - 1);
					spaces.remove(spaces.size() - 1);
					int start = spaces.size() > 0 ? spaces.get(spaces.size() - 1) : 0;
					sb = new StringBuilder(str.substring(start, last + 1));
					i = last;
					continue;
				}
			}
			if(dict.contains(sb.toString())) {
				spaces.add(i);
				sb = new StringBuilder();
				i--;
			}
			else {
				sb.append(str.charAt(i));
			}
		}
		return str;	// failed
	}

	public static void main(String[] args) {
		HashSet<String> dict = new HashSet<String>();
		dict.add("Google");
		dict.add("YouTube");
		dict.add("You");
		String str = "GoogleYouTube";
		System.out.println(breakStringWithSpace(str, dict));
	}
}