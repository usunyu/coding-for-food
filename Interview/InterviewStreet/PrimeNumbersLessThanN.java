/*
	Complete the function getNumberOfPrimes which takes in an integer N as its parameter, 
	to return the number of prime numbers that are less than N

	Sample Testcases:
	Input #00:
	100
	Output #00:
	25

	Input #01:
	1000000
	Output #01:
	78498
*/

class Solution {
	static int getNumberOfPrimes(int N) {
		if(N < 2) return 0;
	    int count = 1;
	    for(int i = 3; i < N; i++) {
	        boolean prime = true;
	        for(int d = 2; d <= Math.sqrt(i); d++) {
	            if(i % d == 0) {
	                prime = false;
	                break;
	            }
	        }
	        if(prime) count++;
	    }
	    return count;
	}

    public static void main(String args[] ) throws Exception {
    	System.out.println(getNumberOfPrimes(1000000));
    }
}