class Solution {
    public int getNumBase(char roman) {
        int numBase = -1;
        switch(roman) {
            case 'I':
                numBase = 1;
                break;
            case 'V':
                numBase = 5;
                break;
            case 'X':
                numBase = 10;
                break;
            case 'L':
                numBase = 50;
                break;
            case 'C':
                numBase = 100;
                break;
            case 'D':
                numBase = 500;
                break;
            case 'M':
                numBase = 1000;
                break;
        }
        return numBase;
    }
    
    public boolean compareRoman(char r1, char r2) {
        if(r1 == '-' || r1 == '-') {
            return false;
        }
        int n1 = getNumBase(r1);
        int n2 = getNumBase(r2);
        return n1 < n2;
    }
    
    public int romanToInt(String s) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int number = 0;

        for(int i = 0; i < s.length(); i++) {
            char roman = s.charAt(i);
            char nextRoman = '-';
            if(i + 1 < s.length()) {
                nextRoman = s.charAt(i + 1);
            }
            // roman < nextRoman
            if(compareRoman(roman, nextRoman)) {
                number += (getNumBase(nextRoman) - getNumBase(roman));
                i++;
            }
            else {
                number += getNumBase(roman);
            }
        }
        return number;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String roman = "LXXXVII";
        System.out.println(solution.romanToInt(roman));
    }
}
