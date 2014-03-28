/*
A non-empty zero-indexed array A consisting of N integers is given.
A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
For example, array A such that:
    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
contains the following example double slices:
double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
double slice (3, 4, 5), sum is 0.
The goal is to find the maximal sum of any double slice.
Write a function:
class Solution { public int solution(int[] A); }
that, given a non-empty zero-indexed array A consisting of N integers, returns the maximal sum of any double slice.
For example, given:
    A[0] = 3
    A[1] = 2
    A[2] = 6
    A[3] = -1
    A[4] = 4
    A[5] = 5
    A[6] = -1
    A[7] = 2
the function should return 17, because no double slice of array A has a sum of greater than 17.
Assume that:
N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−10,000..10,000].
Complexity:
expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/
/*
Test Report                                             Score: 100 of 100
test													time	result
example 
example test											0.680 s.	OK
simple1 
first simple test										0.650 s.	OK
simple2 
second simple test										0.660 s.	OK
simple3 
third simple test										0.660 s.	OK
negative 
all negative numbers									0.660 s.	OK
positive 
all positive numbers									0.670 s.	OK
extreme_triplet 
three elements											0.660 s.	OK
small_random1 
random, numbers form -10**4 to 10**4, length = 70		0.660 s.	OK
small_random2 
random, numbers from -30 to 30, length = 300			0.660 s.	OK
medium_range 
-1000, ..., 1000										0.670 s.	OK
large_ones 
random numbers from -1 to 1, length = ~100,000			0.790 s.	OK
large_random 
random, length = ~100,000								0.840 s.	OK
extreme_maximal 
all maximal values, length = ~100,000					0.890 s.	OK
large_sequence 
many the same small sequences, length = ~100,000		0.790 s.	OK
*/
// reference: http://rafal.io/posts/codility-max-double-slice-sum.html
// you can also use imports, for example:
// import java.math.*;
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 7
        int[] dpforward = new int[A.length];
        int[] dpbackward = new int[A.length];
        // preprocess
        for(int i = 1; i < A.length - 1; i++) {
            dpforward[i] = Math.max(dpforward[i - 1] + A[i], 0);
        }
        for(int i = A.length - 2; i > 0; i--) {
            dpbackward[i] = Math.max(dpbackward[i + 1] + A[i], 0);
        }
        int max = 0;
        // calculate
        for(int i = 1; i < A.length - 1; i++) {
            max = Math.max(max, dpforward[i - 1] + dpbackward[i + 1]);
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] A = {3, 2, 6, -1, 4, 5, -1, 2};
        System.out.println(s.solution(A));
    }
}

