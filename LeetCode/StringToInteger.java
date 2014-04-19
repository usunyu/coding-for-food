/*
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are 
the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all 
the input requirements up front.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on 
the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because 
either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable 
values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/

class Solution {
    int INT_MAX = 2147483647;
    int INT_MIN = -2147483648;
        
    public boolean checkOverflow(int base, int add, boolean neg) {
        boolean overflow = false;
        int max = 0;
        if(!neg) {
            max = INT_MAX / 10;
        }
        else {
            max = INT_MIN / 10;
            max *= -1;
        }
        if(max >= base) {
            base *= 10;
            max = INT_MAX - add;
            if(max >= base) {
                overflow = false;
            }
            else {
                overflow = true;
            }
        }
        else {
            overflow = true;
        }
        return overflow;
    }
    
    public int atoi(String str) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        char[] charArray = str.toCharArray();
        // boolean findFirstNonSpace = false;
        boolean findFirstSign = false;
        boolean findFirstNumber = false;
        boolean negative = false;
        boolean overflow = false;
        int result = 0;
        for(int i = 0; i < charArray.length; i++) {
            if(charArray[i] == ' ' && !findFirstNumber && !findFirstSign) {
                continue;
            }
            // findFirstNonSpace = true;
            if((charArray[i] == '+' || charArray[i] == '-') && !findFirstNumber && !findFirstSign) {
                findFirstSign = true;
                if(charArray[i] == '-') {
                    negative = true;
                }
                continue;
            }
            findFirstSign = true;
            int value = charArray[i] - '0';
            // from here, should be all numbers
            if(value >= 0 && value <= 9) {
                findFirstNumber = true;
                // check overflow
                if(!checkOverflow(result, value, negative)) {
                    result = result * 10 + value;
                }
                else {
                    overflow = true;
                    break;
                }
            }
            else {  // invalid number
                break;
            }
        }
        if(overflow) {
            if(negative) {
                result = INT_MIN;
            }
            else {
                result = INT_MAX;
            }
        }
        else if(negative) {
            result *= -1;
        }
        return result;
    }
}

/*
    Second Round
*/
class Solution2 {
    private boolean isDigit(char ch) {
        int i = ch - '0';
        if(i >= 0 && i <= 9) return true;
        else return false;
    }
    
    public int atoi(String str) {
        if(str == null || str.isEmpty()) return 0;
        long ret = 0;
        int i = 0;
        // skip space
        while(i < str.length() && str.charAt(i) == ' ') i++;
        // get sign
        boolean positive = true;
        if(i < str.length() && (str.charAt(i) == '-' || str.charAt(i) == '+'))
            if(str.charAt(i++) == '-') positive = false;
        while(i < str.length() && isDigit(str.charAt(i))) {
            int d = str.charAt(i++) - '0';
            ret = ret * 10 + d;
        }
        if(!positive) ret = -ret;
        if(ret > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(ret < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int)ret;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int i = solution.atoi("1");
        System.out.println(i);
    }
}

