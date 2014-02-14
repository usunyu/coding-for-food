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

class Main {
    public static void print(int[] num) {
        for(int i : num) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] digits = {8,9,9,9,9};
        print(solution.plusOne(digits));
    }
}

