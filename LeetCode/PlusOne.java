/*
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
*/

import java.util.Arrays;

class Solution {
    public int[] plusOne(int[] digits) {
        int i;
        for(i = digits.length - 1; i >= 0; i--) {
            int val = digits[i] + 1;
            if(val >= 10) digits[i] = 0;
            else {
                digits[i] = val;
                break;
            }
        }
        if(i == -1) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }
        return digits;
    }
}
/*
    Second Round
*/
class Solution2 {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for(int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i];
            int val = digit + carry;
            digits[i] = val % 10;
            carry = val / 10;
        }
        if(carry != 0) {
            int[] copy = new int[digits.length + 1];
            copy[0] = carry;
            for(int i = 0; i < digits.length; i++)
                copy[i + 1] = digits[i];
            return copy;
        }
        else return digits;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] digits = {8,9,9,9,9};
        System.out.println(Arrays.toString(solution.plusOne(digits)));
    }
}

