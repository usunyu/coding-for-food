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

class TestApp {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.atoi(" - 321");
        System.out.println(i);
    }
}

