class Solution {
    public int divide(int dividend, int divisor) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int result = 0;
        long absDividend = Math.abs((long)dividend), absDivisor = Math.abs((long)divisor);
        long rest = absDividend, sum =  absDivisor;
        int exp = 1;
        while(rest >= absDivisor) {
            result += exp;
            rest -= sum;
            sum += sum;
            exp += exp;
            if(sum > Integer.MAX_VALUE || sum > rest) {
                sum = absDivisor;
                exp = 1;
            }
        }
        if((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) {
            return result;
        }
        else {
            return 0 - result;
        }
    }

    // using bit manipulation
    public int divide2(int dividend, int divisor) {
        int result = 0;
        long absDividend = Math.abs((long)dividend), absDivisor = Math.abs((long)divisor);
        long rest = absDividend, sum =  absDivisor;
        int exp = 0;
        while(rest >= absDivisor) {
            result += (1 << exp);
            rest -= sum;
            sum = sum << 1;
            exp++;
            if(sum > Integer.MAX_VALUE || sum > rest) {
                sum = absDivisor;
                exp = 0;
            }
        }
        if((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) {
            return result;
        }
        else {
            return 0 - result;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide2(92, 3));
    }
}