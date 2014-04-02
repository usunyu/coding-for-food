/*
A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 
and 13.
A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P. For example, 
2 and 5 are prime divisors of 20.
You are given two positive integers N and M. The goal is to check whether the sets of prime divisors of integers N and M are 
exactly the same.
For example, given:
N = 15 and M = 75, the prime divisors are the same: {3, 5};
N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
Write a function:
class Solution { public int solution(int[] A, int[] B); }
that, given two non-empty zero-indexed arrays A and B of Z integers, returns the number of positions K for which the prime 
divisors of A[K] and B[K] are exactly the same.
For example, given:
    A[0] = 15   B[0] = 75
    A[1] = 10   B[1] = 30
    A[2] = 3    B[2] = 5
the function should return 1, because only one pair (15, 75) has the same set of prime divisors.
Assume that:
Z is an integer within the range [1..6,000];
each element of arrays A, B is an integer within the range [1..2147483647].
Complexity:
expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/
/*
Test Report												Score: 100 of 100
test													time	result
example 
example test											0.650 s.	OK
extreme 
extreme test with small values							0.650 s.	OK
simple_1 
simple test with small values							0.670 s.	OK
simple_2 
simple test with small values							0.650 s.	OK
primes 
powers of primes										0.650 s.	OK
small_primes 
small primes											0.640 s.	OK
small_all_pairs 
all pairs 1-10, length = 100							0.640 s.	OK
small_random 
small random test, length = 100							0.650 s.	OK
large_all_pairs 
all pairs 1-70, length = ~5,000							0.650 s.	OK
large_random 
large random tests, length = ~6,000						0.690 s.	OK
many_factors 
factorial test											0.680 s.	OK
many_factors2 
factorial test											0.680 s.	OK
big_powers 
powers of 2 and 3										0.680 s.	OK
extreme_maximal 
extreme test with maximal values						0.680 s.	OK
*/
// you can also use imports, for example:
// import java.math.*;
// reference: http://codesays.com/2014/solution-to-common-prime-divisors-by-codility/
class Solution {
    public int gcd(int x, int y) {
        if(x % y == 0)
            return y;
        else
            return gcd(y, x % y);
    }
    
    public boolean hasSamePrimeDivisors(int x, int y) {
        int gcdValue = gcd(x, y);   // The gcd contains all the common prime divisors
        
        while(x != 1) {
            int gcdX = gcd(gcdValue, x);
            if(gcdX == 1) { // x does not contain any more common prime divisors
                break;
            }
            x /= gcdX;
        }
        // If x and y have exactly the same common 
        // prime divisors, x must be composed by
        // the prime divisors in gcd_value. So
        // after previous loop, x must be one.
        if(x != 1) return false;
        while(y != 1) {
            int gcdY = gcd(gcdValue, y);
            if(gcdY == 1) { // y does not contain any more common prime divisors
                break;
            }
            y /= gcdY;
        }
        return y == 1;
    }
    
    public int solution(int[] A, int[] B) {
        // write your code in Java SE 7
        int count = 0;
        for(int i = 0; i < A.length; i++) {
            if(hasSamePrimeDivisors(A[i], B[i])) {
                count++;
            }
        }
        return count;
    }
}
