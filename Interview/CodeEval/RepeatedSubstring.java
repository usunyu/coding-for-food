/*
REPEATED SUBSTRING

CHALLENGE DESCRIPTION:

You are to find the longest repeated substring in a given text. Repeated substrings may not overlap.
If more than one substring is repeated with the same length, print the first one you find.(starting from the beginning of the text). 
NOTE: The substrings can't be all spaces.

INPUT SAMPLE:

Your program should accept as its first argument a path to a filename. The input file contains several lines.
Each line is one test case. Each line contains a test string. E.g.

banana
am so uniqe

OUTPUT SAMPLE:

For each set of input produce a single line of output which is the longest repeated substring.
If there is none, print out the string NONE. E.g.

an
NONE

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class Solution {
	public static void repeatedSubstring(String filePath) {
		try {
			// read file
			File file = new File(filePath);
			Scanner sc;
			sc = new Scanner(file);
			while(sc.hasNext()) {
	            String line = sc.nextLine();
	            // remove all space
	            StringBuilder sb = new StringBuilder();
	            for(int i = 0; i < line.length(); i++) {
	            	char ch = line.charAt(i);
	            	if(ch != ' ') sb.append(ch);
	            }
	            line = sb.toString();
	            // using hashMap to record every substring and its first start index
	            HashMap<String, Integer> strMap = new HashMap<String, Integer>();
	            String maxstr = "";
	            // iterate through all substr
	            for(int i = 0; i < line.length(); i++) {
	            	if(i + maxstr.length() > line.length()) break;	// impossible
	            	for(int j = i + 1; j <= line.length(); j++) {
	            		String substr = line.substring(i, j);
	            		if(substr.length() > maxstr.length()) {	// substr length is longer than maxstr
	            			if(strMap.containsKey(substr)) {	// and we already have this substr before
	            				int index = strMap.get(substr);
		            			// check whether substr is overlap
	            				if(i >= index + substr.length()) {	// not overlap
	            					maxstr = substr;
	            				}
	            			}
	            			else {	// find this substr first time
	            				strMap.put(substr, i);
	            			}
	            		}
	            	}
	            }
	            if(maxstr.length() == 0) maxstr = "NONE";
	            System.out.println(maxstr);
	        }
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		repeatedSubstring("test.txt");
	}
}