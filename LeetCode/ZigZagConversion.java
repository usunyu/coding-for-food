/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

class Solution {
    public String convert(String s, int nRows) {
        if(nRows == 1) return s;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < nRows; i++) {
            for(int j = i; j < s.length(); j+= (2 * nRows - 2)) {
                builder.append(s.charAt(j));
                if(i != 0 && i != nRows - 1) {
                    int block = j / (2 * nRows - 2) + 1;
                    int k = block * (2 * nRows - 2) - i;
                    if(k < s.length())
                        builder.append(s.charAt(k));
                }
            }
        }
        return builder.toString();
    }
}
/*
    Second Round
*/
class Solution2 {
    public String convert(String s, int nRows) {
        if(nRows == 1) return s;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nRows; i++) {
            for(int j = i; j < s.length(); j += (nRows * 2 - 2)) {
                sb.append(s.charAt(j));
                if(i != 0 && i != nRows - 1) {
                    int k = (j / (nRows * 2 - 2) + 1) * (nRows * 2 - 2) - i;
                    if(k < s.length()) sb.append(s.charAt(k));
                }
            }
        }
        return sb.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.convert("PAYPALISHIRING", 4));
    }
}
