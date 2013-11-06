import java.util.*;

class Solution {
    public String multiply(String num1, String num2) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int p1 = num1.length() - 1, p2 = num2.length() - 1;
        int carry = 0;
        ArrayList<String> sums = new ArrayList<String>();
        while(p1 >= 0) {
            String sum = "";
            while(p2 >= 0) {
                int d1 = num1.charAt(p1) - '0';
                int d2 = num2.charAt(p2) - '0';
                int sub = d1 * d2 + carry;
                int dig = sub % 10;
                sum = dig + sum;
                carry = sub / 10;
                p2--;
            }
            if(carry > 0) {
                sum = carry + sum;
                carry = 0;
            }
            // add 0
            for(int i = p1; i < num1.length() - 1; i++) {
                sum += '0';
            }
            sums.add(sum);
            p2 = num2.length() - 1;
            p1--;
        }
        String mul = "";
        carry = 0;
        int i = 0;
        while(true) {
            int sub = 0;
            boolean cal = false;
            for(String sum : sums) {
                int d = 0;
                if(sum.length() - 1 - i >= 0) {
                    d = sum.charAt(sum.length() - 1 - i) - '0';
                    cal = true;
                }
                sub += d;
            }
            if(!cal) {
                break;
            }
            sub += carry;
            int dig = sub % 10;
            carry = sub / 10;
            mul = dig + mul;
            i++;
        }
        if(carry > 0) {
            mul = carry + mul;
        }
        while(mul.charAt(0) == '0' && mul.length() > 1) {
            mul = mul.substring(1);
        }
        return mul;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String num1 = "9133", num2 = "0";
        System.out.println(solution.multiply(num1, num2));
    }
}