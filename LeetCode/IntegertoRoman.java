/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.

http://literacy.kent.edu/Minigrants/Cinci/romanchart.htm
*/

class Solution {
    public String getRomanBase(int num) {
        String romanBase = "";
        switch(num) {
            case 1:
                romanBase = "I";
                break;
            case 5:
                romanBase = "V";
                break;
            case 10:
                romanBase = "X";
                break;
            case 50:
                romanBase = "L";
                break;
            case 100:
                romanBase = "C";
                break;
            case 500:
                romanBase = "D";
                break;
            case 1000:
                romanBase = "M";
                break;
        }
        return romanBase;
    }
    
    public String intToRoman(int num) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int romanBase = 1000;
        String romanStr = "";
        while(romanBase >= 1) {
            int count = num / romanBase;
            int rest = num % romanBase;

            if(count == 9 && romanBase < 1000) {    // IX
                romanStr += getRomanBase(romanBase);
                romanStr += getRomanBase(romanBase * 10);
            }
            else if(count >= 5 && romanBase < 1000) {    // V
                romanStr += getRomanBase(romanBase * 5);
                for(int i = 0; i < count - 5; i++) {
                    romanStr += getRomanBase(romanBase);
                }
            }
            else if(count == 4 && romanBase < 1000) {   // IV
                romanStr += getRomanBase(romanBase);
                romanStr += getRomanBase(romanBase * 5);
            }
            else {
                for(int i = 0; i < count; i++) {
                    romanStr += getRomanBase(romanBase);
                }
            }
            romanBase /= 10;
            num = rest;
        }
        return romanStr;
    }
    /*
        Second Round
    */
    public String intToRoman2(int num) {
        int romanBase = 1000;
        StringBuilder roman = new StringBuilder();
        while(romanBase >= 1) {
            int count = num / romanBase;
            int rest = num % romanBase;
            
            if(count == 9) {
                roman.append(getRomanBase(romanBase));
                roman.append(getRomanBase(romanBase * 10));
            }
            else if(count >= 5) {
                roman.append(getRomanBase(romanBase * 5));
                for(int i = 0; i < count - 5; i++)
                    roman.append(getRomanBase(romanBase));
            }
            else if(count == 4) {
                roman.append(getRomanBase(romanBase));
                roman.append(getRomanBase(romanBase * 5));
            }
            else {
                for(int i = 0; i < count; i++)
                    roman.append(getRomanBase(romanBase));
            }
            
            romanBase /= 10;
            num = rest;
        }
        return roman.toString();
    }
}

class Solution2 {
    public String intToRoman(int num) {
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] s = new String[]{"M","CM", "D","CD", "C","XC", "L","XL", "X","IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num > 0){
            if (num >= nums[i]){
                sb.append(s[i]);
                num -= nums[i];
            }else{
                i++;
            }
        }
        return sb.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int num = 9;
        System.out.println(solution.intToRoman2(num));
    }
}


