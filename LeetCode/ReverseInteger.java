class Solution {
	public int reverse2(int x) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        long res = 0;
	        
        while (x != 0) {  
            res = (res * 10) + x % 10;  
            x /= 10;  
        }
	   
        return (int)res;
    }
	
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

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.reverse(-123));
	}
}