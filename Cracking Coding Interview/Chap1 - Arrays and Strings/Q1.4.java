/*
Write a method to replace all spaces in a string with '%20'. 
You may assume that the string has sufficient space at the end of the string to hold 
the additional characters, and that you are given the "true" length of the string. 
(Note: if implementing in Java, please usea character array so that you can perform this 
operation inplace.)
*/

import java.util.*;
import CtCILibrary.AssortedMethods;

class Solution {
	/*
	public static String replaceSpace(String str) {
		char[] charArray = str.toCharArray();
		// first count space number
		boolean spaceCont = false;
		int spaceNum = 0;
		int charNum = 0;
		for(int i = 0; i < charArray.length; i++) {
			if(charArray[i] != ' ') {
				charNum++;
				if(spaceCont) {
					spaceNum++;
					spaceCont = false;
				}
			}
			else {
				spaceCont = true;
			}
		}
		
		int endIndex = charNum + spaceNum * 3 - 1;
		int j = endIndex;
		spaceCont = false;
		for(int i = endIndex; i >= 0; i--) {
			if(charArray[i] != ' ') {
				if(spaceCont) {
					charArray[j--] = '0';
					charArray[j--] = '2';
					charArray[j--] = '%';
					spaceCont = false;
				}
				charArray[j--] = charArray[i];
			}
			else {
				spaceCont = true;
			}
		}
		str = new String(charArray, 0, endIndex + 1);
		return str;
	}

	public static void main(String[] args) {
		String testStr = "AB   CD   EF                             ";
		System.out.println(testStr);
		testStr = replaceSpace(testStr);
		System.out.println(testStr);
	}
	*/
	/*
		Second Round
	*/
	public static void replaceSpaces(char[] str, int length) {
		// count chars and space
		int chars = 0, spaces = 0;
		for(int i = 0; i < length; i++) {
			if(str[i] == ' ') spaces++;
			else chars++;
		}
		// calculate last index
		int last = chars + 3 * spaces;
		int i = last - 1, j = length - 1;
		while(j >= 0) {
			if(str[j] == ' ') {	// replace ' ' with '%20'
				str[i--] = '0';
				str[i--] = '2';
				str[i--] = '%';
			}
			else str[i--] = str[j];	// copy charcter
			j--;
		}
	}

	public static void main(String[] args) {
		String str = "abc d e f";
		char[] arr = new char[str.length() + 3 * 2 + 1];
		for (int i = 0; i < str.length(); i++) {
			arr[i] = str.charAt(i);
		}
		replaceSpaces(arr, str.length());	
		System.out.println("\"" + AssortedMethods.charArrayToString(arr) + "\"");
	}
}



