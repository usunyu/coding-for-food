/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) 
justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when 
necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly 
between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.
*/

import java.util.*;
import LCLibrary.*;

class Solution {
    public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> text = new ArrayList<String>();
        int start = 0, end = 0, length = 0;
        for(int i = 0; i < words.length; i++) {
            length += words[i].length();
            if(length <= L) {
                if(i == words.length - 1) { // last line
                    String str = "";
                    for(int j = start; j <= end; j++) {
                        if(j < end) str += words[j] + " ";
                        else str += words[j];
                    }
                    int strLen = L - str.length();
                    for(int j = 0; j < strLen; j++) str += " ";
                    text.add(str);
                }
                end++;
                length++;   // count space
            }
            else {
                end--;
                int spaceCount = end - start;
                String str = "";
                if(spaceCount == 0) {
                    str = words[start];
                }
                else {
                    int spaceLength = L - (length - words[i].length() - 1 - spaceCount);
                    int spaceNum, beginLess = spaceCount;
                    if(spaceLength % spaceCount == 0) {
                        spaceNum = spaceLength / spaceCount;
                    }
                    else {
                        spaceNum = spaceLength / spaceCount + 1;
                        // the number of space which is short
                        int lessCount = spaceCount * spaceNum - spaceLength;
                        beginLess = spaceCount - lessCount;
                    }
                    String space = "";
                    for(int j = 0; j < spaceNum - 1; j++) space += " ";
                    for(int j = start; j <= end; j++) {
                        if(j < end && j - start >= beginLess) str += words[j] + space;
                        else if(j < end && j - start < beginLess) str += words[j] + space + " ";
                        else str += words[j];
                    }
                }
                int strLen = L - str.length();
                for(int j = 0; j < strLen; j++) str += " ";
                text.add(str);
                // reset
                start = end + 1;
                end = start;
                length = 0;
                i--;
            }
        }
        return text;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"Listen","to","many,","speak","to","a","few."};
        Output.printStringList(solution.fullJustify(words, 6));
    }
}
