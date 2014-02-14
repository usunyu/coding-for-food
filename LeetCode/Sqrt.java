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

    // Babylonian method
    // time complexity : O(loglogN)
    // space complexity : O(1)
    public int sqrt2(int x) {
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

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int x = 2147395599;
		System.out.println(solution.sqrt2(x));
	}
}