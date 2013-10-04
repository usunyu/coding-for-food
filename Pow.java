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

    // exceed time limit
    public double pow2(double x, int n) {
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

    // 700 ms
    // time complexity : O(logN)
    // space complexity : O(1)
    public double pow3(double x, int n) {
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

    // 720 ms
    // time complexity : O(logN)
    // space complexity : O(1)
    // using recursion
    public double pow4(double x, int n) {
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

    // 748 ms
    // time complexity : O(logN)
    // space complexity : O(1)
    // using bit
    public double pow5(double x, int n) {
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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        double result = solution.pow5(0.00001, 2147483647);
        System.out.println(result);
    }
}