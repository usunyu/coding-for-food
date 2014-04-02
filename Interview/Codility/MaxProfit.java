/*
A zero-indexed array A consisting of N integers is given. It contains daily prices of a stock share for a period of N consecutive days. If a single share was bought on day P and sold on day Q, where 0 ≤ P ≤ Q < N, then the profit of such transaction is equal to A[Q] − A[P], provided that A[Q] ≥ A[P]. Otherwise, the transaction brings loss of A[P] − A[Q].
For example, consider the following array A consisting of six elements such that:
  A[0] = 23171  
  A[1] = 21011  
  A[2] = 21123
  A[3] = 21366  
  A[4] = 21013  
  A[5] = 21367
If a share was bought on day 0 and sold on day 2, a loss of 2048 would occur because A[2] − A[0] = 21123 − 23171 = −2048. If a share was bought on day 4 and sold on day 5, a profit of 354 would occur because A[5] − A[4] = 21367 − 21013 = 354. Maximum possible profit was 356. It would occur if a share was bought on day 1 and sold on day 5.
Write a function,
class Solution { public int solution(int[] A); }
that, given a zero-indexed array A consisting of N integers containing daily prices of a stock share for a period of N consecutive days, returns the maximum possible profit from one transaction during this period. The function should return 0 if it was impossible to gain any profit.
For example, given array A consisting of six elements such that:
  A[0] = 23171  
  A[1] = 21011  
  A[2] = 21123
  A[3] = 21366  
  A[4] = 21013  
  A[5] = 21367
the function should return 356, as explained above.
Assume that:
N is an integer within the range [0..400,000];
each element of array A is an integer within the range [0..200,000].
Complexity:
expected worst-case time complexity is O(N);
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/
/*
Test Report                                             Score: 100 of 100
test													time	result
example 
example, length=6										0.650 s.	OK
simple_1 
V-pattern sequence, length=7							0.650 s.	OK
simple_desc 
descending and ascending sequence, length=5				0.660 s.	OK
simple_empty 
empty and [0,200000] sequence							0.650 s.	OK
two_hills 
two increasing subsequences								0.660 s.	OK
max_profit_after_max_and_before_min 
max profit is after global maximum and before global minimum	0.650 s.	OK
medium_1 
large value (99) followed by short V-pattern (values from [1..5]) repeated 100 times	0.650 s.	OK
large_1 
large value (99) followed by short pattern (values from [1..6]) repeated 10K times	0.760 s.	OK
large_2 
chaotic sequence of 200K values from [100K..120K], then 200K values from [0..100K]	1.460 s.	OK
large_3 
chaotic sequence of 200K values from [1..200K]			1.040 s.	OK
*/
// you can also use imports, for example:
// import java.math.*;
class Solution {
	public int solution(int[] A) {
		// write your code in Java SE 7
		int min = 0, profit = 0;
		for(int i = 1; i < A.length; i++) {
			if(A[i] > A[min]) {
				profit = Math.max(profit, A[i] - A[min]);
			}
			else if(A[i] < A[min]) {
				min = i;
			}
		}
		return profit;
	}
}