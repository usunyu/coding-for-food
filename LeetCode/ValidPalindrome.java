class Solution {
    public boolean isAlphabet(char ch) {
        if(ch - 'a' >= 0 && ch - 'a' <= 25) {
            return true;
        }
        if(ch - 'A' >= 0 && ch - 'A' <= 25) {
            return true;
        }
        return false;
    }
    
    public boolean isSameChar(char ch1, char ch2) {
        if(isAlphabet(ch1) && isAlphabet(ch2)) {
            int shift = 'a' - 'A';
            if(ch1 - ch2 == 0 || Math.abs(ch1 - ch2) == shift) {
                return true;
            }
        }
        if(isNumber(ch1) && isNumber(ch2)) {
            if(ch1 == ch2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isNumber(char ch) {
        if(ch - '0' >= 0 && ch - '0' <= 9) {
            return true;
        }
        return false;
    }
    
    public boolean isPalindrome(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(s == null || s.equals("")) {
            return true;
        }
        boolean isPalindrome = true;
        for(int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            while(!isAlphabet(s.charAt(i)) && !isNumber(s.charAt(i))) {
                i++;
                if(j < i) {
                    break;
                }
            }
            while(!isAlphabet(s.charAt(j)) && !isNumber(s.charAt(j))) {
                j--;
                if(j < i) {
                    break;
                }
            }
            if(j < i) {
                break;
            }
            if(!isSameChar(s.charAt(i), s.charAt(j))) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "A man, a plan, a canal: Panama";
        System.out.println(solution.isPalindrome(str));
    }
}

