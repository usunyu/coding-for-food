/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. 
You should gather all requirements up front before implementing one.
*/

class Solution {
    public boolean isNumber(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(s == null || s.equals("")) {
            return false;
        }
        boolean firstNumber = false;
        boolean firstPoint = false;
        boolean firstE = false;
        boolean numberAfterE = false;
        boolean lastSpace = false;
        boolean firstSign = false;
        boolean isNumber = true;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch - '0' >= 0 && ch - '0' <= 9 && !lastSpace) {    // find a number
                firstNumber = true;
                if(firstE) {
                    numberAfterE = true;
                }
                continue;
            }
            if((ch == '-' || ch == '+') && !firstSign && !firstPoint && !firstE && !firstNumber && !lastSpace) {    // find first sign
                firstSign = true;
                continue;
            }
            if((ch == '-' || ch == '+') && i - 1 >= 0 && s.charAt(i - 1) == 'e') {  // second sign after e
                continue;
            }
            if(ch == '.' && !firstPoint && !lastSpace) {  // find first point
                firstPoint = true;
                if(firstE) {
                    isNumber = false;
                    break;
                }
                continue;
            }
            if(ch == 'e' && !firstE && firstNumber && !lastSpace) {  // find first e
                firstE = true;
                continue;
            }
            if(ch == ' ' && !firstNumber && !firstSign && !firstPoint && !firstE && !lastSpace) { // skip first space
                continue;
            }
            if(ch == ' ') { // skip last space
                lastSpace = true;
                continue;
            }
            isNumber = false;
            break;
        }
        if(!firstNumber || (firstE && !numberAfterE)) {  // rest
            isNumber = false;
        }
        return isNumber;
    }
}
/*
    Second Round
*/
class Solution2 {
    private boolean isDigit(char ch) { return ch - '0' <= 9 && ch - '0' >= 0; }
    
    private boolean isSign(char ch) { return ch == '+' || ch == '-'; }
    
    private boolean isE(char ch) { return ch == 'e' || ch == 'E'; }
    
    private boolean isPoint(char ch) { return ch == '.'; }
    
    private boolean prevDigit(int current, int left, String s) {
        for(int i = current; i >= left; i--) {
            char ch = s.charAt(i);
            if(isDigit(ch)) return true;
        }
        return false;
    }
    
    private boolean nextDigit(int current, int right, String s) {
        for(int i = current; i <= right; i++) {
            char ch = s.charAt(i);
            if(isDigit(ch)) return true;
        }
        return false;
    }
    
    public boolean isNumber(String s) {
        if(s == null || s.length() == 0) return false;
        int left = 0, right = s.length() - 1;
        // skip space
        while(left <= right && s.charAt(left) == ' ') left++;
        while(left <= right && s.charAt(right) == ' ') right--;
        if(left > right) return false;
        boolean sign = true, e = true, point = true;
        for(int i = left; i <= right; i++) {
            char ch = s.charAt(i);
            if(isDigit(ch)) {
                sign = false;   // when we have digit, no sign follow
            }
            else if(isPoint(ch) && point && (prevDigit(i - 1, left, s) || nextDigit(i + 1, right, s))) {
                point = false;  // when we have point, no point follow
                sign = false;   // and no sign between sign and other digit
            }
            else if(isSign(ch) && sign) {
                sign = false;   // when we have sign, no sign follow
            }
            else if(isE(ch) && e && prevDigit(i - 1, left, s) && nextDigit(i + 1, right, s)) {
                e = false;  // when we have e, no e follow
                point = false;  // and no sign follow
                sign = true;    // enable sign again, e.g. 10e+6
            }
            else return false;
        }
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        String number = "005.047e+6";
        System.out.println(solution.isNumber(number));
    }
}