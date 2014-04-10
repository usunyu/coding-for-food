/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. 
How should you handle such cases?

Throw an exception? Good, but what if throwing an exception is not an option? You would then have to re-design the function 
(ie, add an extra parameter).
*/

class Solution {
    public int reverse(int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int result = 0;
        int MAX = Integer.MAX_VALUE;
        boolean neg = x < 0 ? true : false;
        x = Math.abs(x);
        while(x > 0) {
            int digit = x % 10;
            if(result > MAX / 10) {    // overflow
                result = MAX;
                break;
            }
            else if(result == MAX / 10) {
                int maxAdd = MAX - MAX / 10;
                if(digit <= maxAdd) {
                    result = result * 10 + digit;
                }
                else {
                    result = MAX;
                    break;
                }
            }
            else {
                result = result * 10 + digit;
            }
            x /= 10;
        }
        if(neg) {
            result *= -1;
        }
        return result;
    }
}

class Solution2 {
    public int reverse(int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        long res = 0;
            
        while (x != 0) {  
            res = (res * 10) + x % 10;  
            x /= 10;  
        }
       
        return (int)res;
    }
}

/*
    Second Round
*/
class Solution3 {
    public int reverse(int x) {
        long ret = 0;
        while(x != 0) {
            int digit = x % 10;
            ret = ret * 10 + digit;
            x /= 10;
        }
        if(ret > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        return (int)ret;
    }
}

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.reverse(-123));
	}
}