/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
*/

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

class Solution2 {
    // time complexity : O(M*N)
    // space complexity : O(M+N)
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int l1 = num1.length(), l2 = num2.length();
        int[] n1 = new int[l1];
        int[] n2 = new int[l2];
        for(int i = 0; i < num1.length(); i++) {
            n1[i] = num1.charAt(l1 - 1 - i) - '0';
        }
        for(int i = 0; i < num2.length(); i++) {
            n2[i] = num2.charAt(l2 - 1 - i) - '0';
        }
        StringBuffer strBuff = new StringBuffer();
        int sum = 0;
        for(int i = 0; i < l1 + l2 - 1; i++) {
            for(int j = 0; j <=i; j++) {
                if(j < l1 && i - j < l2) {
                    sum += n1[j] * n2[i - j];
                }
            }
            int dig = sum % 10;
            strBuff.append(dig);
            sum /= 10;
        }
        if(sum > 0) {
            strBuff.append(sum);
        }
        String s = strBuff.reverse().toString();
        return (s.isEmpty()) ? "0" : s;
    }
}
/*
    Second Round
*/
class Solution3 {
    public String multiply(String num1, String num2) {
        int N1 = num1.length(), N2 = num2.length();
        if(N1 == 0 || N2 == 0) return "";
        if(num1.equals("0") || num2.equals("0")) return "0";
        int[] n1 = new int[N1];
        int[] n2 = new int[N2];
        for(int i = 0; i < N1; i++) n1[i] = num1.charAt(i) - '0';
        for(int i = 0; i < N2; i++) n2[i] = num2.charAt(i) - '0';
        // middle result
        ArrayList<Integer> result = null;
        for(int i = N1 - 1; i >= 0; i--) {
            int carry = 0;
            ArrayList<Integer> res = new ArrayList<Integer>();
            // initial, easy to cal result
            for(int j = N1 - 1; j > i; j--)
                res.add(0);
            for(int j = N2 - 1; j >= 0; j--) {
                int val = n1[i] * n2[j] + carry;
                int dig = val % 10;
                carry = val / 10;
                res.add(dig);
            }
            if(carry != 0) res.add(carry);
            if(result == null)  // first
                result = res;
            else {  // calculate result
                int c = 0;
                for(int j = 0; j < res.size(); j++) {
                    int v = res.get(j) + (j >= result.size() ? 0 : result.get(j)) + c;
                    int d = v % 10;
                    c = v / 10;
                    res.set(j, d);
                }
                if(c != 0) res.add(c);
                result = res;
            }
        }
        // calculate result
        StringBuilder ret = new StringBuilder();
        for(int i = result.size() - 1; i >= 0; i--)
            ret.append(result.get(i));
        return ret.toString();
    }
}
/*
    Third Round
*/
class Solution4 {
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null) return "";
        if(num1.equals("0") || num2.equals("0")) return "0";
        int l1 = num1.length(), l2 = num2.length();
        int[] n1 = new int[l1], n2 = new int[l2];
        for(int i = 0; i < l1; i++) n1[i] = num1.charAt(i) - '0';
        for(int i = 0; i < l2; i++) n2[i] = num2.charAt(i) - '0';
        ArrayList<Integer> result = null;
        for(int i = l1 - 1; i >= 0; i--) {
            ArrayList<Integer> sub = new ArrayList<Integer>();
            for(int j = l1 - 1; j > i; j--) sub.add(0);
            int carry = 0, mul = n1[i];
            for(int j = l2 - 1; j >= 0; j--) {
                int val = mul * n2[j] + carry;
                int dig = val % 10;
                carry = val / 10;
                sub.add(dig);
            }
            if(carry != 0) sub.add(carry);
            if(result == null) result = sub;
            else {
                carry = 0;
                for(int j = 0; j < sub.size(); j++) {
                    int val = sub.get(j) + (j >= result.size() ? 0 : result.get(j)) + carry;
                    int dig = val % 10;
                    carry = val / 10;
                    sub.set(j, dig);
                }
                if(carry != 0) sub.add(carry);
                result = sub;
            }
        }
        StringBuilder ret = new StringBuilder();
        for(int i = result.size() - 1; i >= 0; i--)
            ret.append(result.get(i));
        return ret.toString();
    }
}

class Main {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        String num1 = "123", num2 = "456";
        System.out.println(solution.multiply(num1, num2));
    }
}