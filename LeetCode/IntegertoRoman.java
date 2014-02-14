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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int num = 4;
        System.out.println(solution.intToRoman(num));
    }
}


