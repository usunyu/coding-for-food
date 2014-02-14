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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String number = "005047e+6";
        System.out.println(solution.isNumber(number));
    }
}