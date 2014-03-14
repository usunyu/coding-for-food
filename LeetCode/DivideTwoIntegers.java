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
    /*
        Second Round
    */
    public int divide3(int dividend, int divisor) {
        if(divisor == 0) return Integer.MAX_VALUE;
        long divid = Math.abs((long)dividend), divis = Math.abs((long)divisor);
        int ret = 0;
        while(divid >= divis) {
            long div = divis;
            int quot = 1;
            while((div << 1) < divid) {
                div <<= 1;
                quot <<= 1;
            }
            divid -= div;
            ret += quot;
        }
        if(dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0) return ret;
        else return 0 - ret;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.divide3(-1010369383, -2147483648));
    }
}