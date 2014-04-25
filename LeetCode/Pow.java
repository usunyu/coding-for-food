/*
Implement pow(x, n).
*/

import java.util.*;

class Solution {
    // exceed time limit
    public double pow(double x, int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(n == 0) {
            return 1;
        }
        boolean neg = false;
        if(n < 0) {
            n *= -1;
            neg = true;
        }
        double sum = x;
        int i = 1;
        while( i * 2 <= n) {
            sum *= sum;
            i *= 2;
        }
        while(i < n) {
            sum *= x;
            i++;
        }
        if(neg) {
            sum = 1 / sum;
        }
        return sum;
    }
}

class Solution2 {
    // exceed time limit
    public double pow(double x, int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(n == 0) {
            return 1;
        }
        boolean neg = false;
        if(n < 0) {
            n *= -1;
            neg = true;
        }
        double sum = 1;
        int p = 0;
        int interval = 40;

        while(p < n && p < interval) {
            sum *= x;
            p++;
        }
        if(p >= interval && p < n) {
            int mul = n / interval;
            int res = n % interval;
            for(int i = 1; i < mul; i++) {
                sum *= sum;
            }
            for(int i = 0; i < res; i++) {
                sum *= x;
            }
        }
        if(neg) {
            sum = 1 / sum;
        }
        return sum;
    }
}

class Solution3 {
    // time complexity : O(logN)
    // space complexity : O(1)
    public double pow(double x, int n) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(n == 0) {
            return 1;
        }
        boolean neg = false;
        if(n < 0) {
            n *= -1;
            neg = true;
        }
        ArrayList<Double> sumList = new ArrayList<Double>();
        while( n > 0) {
            double sum = x;
            int i = 1;
            while( i <= n / 2) {
                sum *= sum;
                i *= 2;
            }
            n = n - i;
            sumList.add(sum);
        }
        double sum = 1;
        for(double s : sumList) {
            sum *= s;
        }
        if(neg) {
            sum = 1 / sum;
        }
        return sum;
    }
}

class Solution4 {
    // time complexity : O(logN)
    // space complexity : O(1)
    // using recursion
    public double pow(double x, int n) {
        if (n == 0) return 1.0;
        // Compute x^{n/2} and store the result into a temporary
        // variable to avoid unnecessary computing
        double half = pow(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        else if (n > 0)
            return half * half * x;
        else
            return half * half / x;
    }
}

class Solution5 {
    // time complexity : O(logN)
    // space complexity : O(1)
    // using bit
    public double pow(double x, int n) {
        int m = Math.abs(n);
        double ret = 1;
        for ( ; m > 0; x *= x, m >>= 1) {
            if ((m & 1) != 0) {
                ret *= x;
            }
        }
        return (n < 0) ? (1.0 / ret) : (ret);
    }
}

/*
    Second Round
*/
class Solution6 {
    // time complexity : O(logN)
    // space complexity : O(logN)
    public double pow(double x, int n) {
        int m = Math.abs(n), power = 1;
        double ret = 1.0, val = x;
        // preprocess
        ArrayList<Integer> powers = new ArrayList<Integer>();
        while(m > 0) {
            if(m % 2 == 1)
                powers.add(power);
            power *= 2;
            m /= 2;
        }
        // calculate
        power = 1;
        for(int i = 0; i < powers.size(); i++) {
            while(power < powers.get(i)) {
                val *= val;
                power *= 2;
            }
            ret *= val;
        }
        return n > 0 ? ret : 1.0 / ret;
    }
}

class Solution7 {
    // time complexity : O(logN)
    // space complexity : O(1)
    public double pow(double x, int n) {
        int m = Math.abs(n);
        double ret = 1.0, val = x;
        while(m > 0) {
            if(m % 2 == 1) {    // need count
                ret *= val;
            }
            val *= val;
            m /= 2;
        }
        return n > 0 ? ret : 1.0 / ret;
    }
}
/*
    Third round
*/
class Solution8 {
    public double pow(double x, int n) {
        int exp = 1, m = Math.abs(n);
        double res = 1, mul = x;
        while(m > 0) {
            res *= mul;
            mul *= mul;
            m -= exp;
            exp *= 2;
            if(exp > m) {
                mul = x;
                exp = 1;
            }
        }
        return n > 0 ? res : 1.0 / res;
    }
}

class Main {
    public static void main(String[] args) {
        Solution8 solution = new Solution8();
        double result = solution.pow(1.72777, 7);
        System.out.println(result);
    }
}