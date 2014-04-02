/*
A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), 
such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). 
The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. 
To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
For example, array A such that:
    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
contains the following example slices:
slice (1, 2), whose average is (2 + 2) / 2 = 2;
slice (3, 4), whose average is (5 + 1) / 2 = 3;
slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
The goal is to find the starting position of a slice whose average is minimal.
Write a function:
class Solution { public int solution(int[] A); }
that, given a non-empty zero-indexed array A consisting of N integers, returns the starting position of the 
slice with the minimal average. If there is more than one slice with a minimal average, you should return the 
smallest starting position of such a slice.
For example, given array A such that:
    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
the function should return 1, as explained above.
Assume that:
N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−10,000..10,000].
Complexity:
expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for 
input arguments).
Elements of input arrays can be modified.
*/
// you can also use imports, for example:
// import java.math.*;

// nice proof: http://codesays.com/2014/solution-to-min-avg-two-slice-by-codility/
/*
Test Report                                             Score: 100 of 100
test                                                    time    result
example 
example test                                            0.650 s.    OK
double_quadruple 
two or four elements                                    0.640 s.    OK
simple1 
simple test, the best slice has length 3                0.650 s.    OK
simple2 
simple test, the best slice has length 3                0.650 s.    OK
small_random 
random, length = 100                                    0.640 s.    OK
medium_range 
increasing, decreasing (legth = ~100) and small functional  0.650 s.    OK
medium_random 
random, N = ~700                                        0.650 s.    OK
large_ones 
numbers from -1 to 1, N = ~100,000                      0.740 s.    OK
large_random 
random, N = ~100,000                                    0.820 s.    OK
extreme_values 
all maximal values, N = ~100,000                        0.840 s.    OK
large_sequence 
many seqeneces, N = ~100,000                            0.730 s.    OK
*/
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 7
        if(A.length < 2) return -1;
        int minLen = A.length > 2 ? 3 : 2;
        int start = 0;
        double minAvg = Double.MAX_VALUE;
        for(int length = 2; length <= minLen; length++) {
            // initial
            int sum = 0;
            for(int i = 0; i < length; i++) {
                sum += A[i];
            }
            for(int i = 0; i <= A.length - length; i++) {
                double avg = (double) sum / length;
                if(avg < minAvg) {
                    minAvg = avg;
                    start = i;
                }
                sum -= A[i];
                if(i + length < A.length)
                    sum += A[i + length];
            }
        }
        return start;
    }
}

class Main {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] A = {4, 2, 2, 5, 1, 5, 8};
        System.out.println(s.solution(A));
    }
}
