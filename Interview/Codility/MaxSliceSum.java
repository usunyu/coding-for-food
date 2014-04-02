/*
A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, 
is called a slice of array A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].
Write a function:
class Solution { public int solution(int[] A); }
that, given an array A consisting of N integers, returns the maximum sum of any slice of A.
For example, given array A such that:
A[0] = 3  A[1] = 2  A[2] = -6
A[3] = 4  A[4] = 0
the function should return 5 because:
(3, 4) is a slice of A that has sum 4,
(2, 2) is a slice of A that has sum −6,
(0, 1) is a slice of A that has sum 5,
no other slice of A has sum greater than (0, 1).
Assume that:
N is an integer within the range [1..1,000,000];
each element of array A is an integer within the range [−1,000,000..1,000,000];
the result will be an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:
expected worst-case time complexity is O(N);
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/
/*
Test Report                                             Score: 100 of 100
test													time	result
example													0.660 s.	OK
one_element												0.650 s.	OK
two_elements											0.640 s.	OK
three_elements											0.680 s.	OK
simple													0.690 s.	OK
extreme_minimum											0.690 s.	OK
fifty_random											0.680 s.	OK
neg_const												0.690 s.	OK
pos_const												0.690 s.	OK
high_low_1Kgarbage										0.690 s.	OK
1Kgarbage_high_low										0.690 s.	OK
growing_saw												0.690 s.	OK
blocks													0.870 s.	OK
growing_negative										1.100 s.	OK
*/
// you can also use imports, for example:
// import java.math.*;
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 7
        int max = A[0];
        A[0] = Math.max(A[0], 0);
        for(int i = 1; i < A.length; i++) {
            max = Math.max(A[i - 1] + A[i], max);
            if(i == A.length - 1)
            	max = Math.max(A[i], max);
            A[i] = Math.max(A[i - 1] + A[i], 0);
        }
        return max;
    }
}

class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] A = {-1, 1, -1};
		System.out.println(s.solution(A));
	}
}