/*
Implement int sqrt(int x).

Compute and return the square root of x.
*/

class Solution {
    // time complexity : O(logN)
    // space complexity : O(1)
    public int sqrt(int x) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(x < 2) {
            return x;
        }
        int right = x;
        int left = 0;
        while(right - left > 1) {
            int mid = left + (right - left) / 2;
            int divisor = x / mid;
            if(divisor == mid) {
                return mid;
            }
            else if(divisor > mid) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        return left;
    }
}

class Solution2 {
    // Babylonian method
    // time complexity : O(loglogN)
    // space complexity : O(1)
    public int sqrt(int x) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(x < 2) {
            return x;
        }
        double r0 = 0, r1 = x / 2;
        while(Math.abs(r1 - r0) > 0.01) {
            r0 = r1;
            r1 = (x / r0 + r0) / 2;
        }
        return (int)r1;
    }
}

/*
    Second Round
*/
class Solution3 {
    public int sqrt(int x) {
        if(x < 2) return x;
        int left = 0, right = x;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            int div = x / mid;
            if(mid == div) return mid;
            else if(mid < div) left = mid;
            else right = mid;
        }
        return left;
    }
}

// http://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html

// Binary Search
class Solution4 {
    public int sqrt(int x) {
        if(x < 2) return x;
        int low = 0, high = x / 2 + 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            int div = x / mid;
            if(mid > div) high = mid - 1;
            else if(mid < div) low = mid + 1;
            else return mid;
        }
        return high;
    }
}

// Newton method
class Solution5 {
    public int sqrt(int x) {
        if (x == 0) return 0;
        double last = 0;
        double res = x / 2 + 1;
        while (res != last) {
            last = res;
            res = (res + x / res) / 2;
        }
        return (int)res;
    }
}

// for double
class Solution6 {
    double sqrt(double x) {
        if (x == 0) return 0;
        double last = 0.0;
        double res = 1.0;
        while (res != last) {
            last = res;
            res = (res + x / res) / 2;
        }
        return res;
    }
}

class Main {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();
        Solution6 solution2 = new Solution6();
        int x = 2147395599;
        System.out.println(solution.sqrt(x));
        System.out.println(solution2.sqrt(x));
    }
}