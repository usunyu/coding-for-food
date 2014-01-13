import java.util.*;

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
    public static void print(ArrayList<String> text) {
        for(String str : text) {
            System.out.println("[" + str + "]");
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"Listen","to","many,","speak","to","a","few."};
        print(solution.fullJustify(words, 6));
    }
}
