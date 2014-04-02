/*
You are given N counters, initially set to 0, and you have two possible operations on them:
increase(X) - counter X is increased by 1,
max_counter - all counters are set to the maximum value of any counter.
A non-empty zero-indexed array A of M integers is given. This array represents consecutive operations:
if A[K] = X, such that 1 <= X <= N, then operation K is increase(X),
if A[K] = N + 1 then operation K is max_counter.

You are given N counters, initially set to 0, and you have two possible operations on them:
increase(X) - counter X is increased by 1,
max_counter - all counters are set to the maximum value of any counter.
A non-empty zero-indexed array A of M integers is given. This array represents consecutive operations:
if A[K] = X, such that 1 <= X <= N, then operation K is increase(X),
if A[K] = N + 1 then operation K is max_counter.
For example, given integer N = 5 and array A such that:
    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the values of the counters after each consecutive operation will be:
    (0, 0, 1, 0, 0)
    (0, 0, 1, 1, 0)
    (0, 0, 1, 2, 0)
    (2, 2, 2, 2, 2)
    (3, 2, 2, 2, 2)
    (3, 2, 2, 3, 2)
    (3, 2, 2, 4, 2)
The goal is to calculate the value of every counter after all operations.
Write a function:
class Solution { public int[] solution(int N, int[] A); }
that, given an integer N and a non-empty zero-indexed array A consisting of M integers, returns a sequence of integers representing the values of the counters.
The sequence should be returned as:
a structure Results (in C), or
a vector of integers (in C++), or
a record Results (in Pascal), or
an array of integers (in any other programming language).
For example, given:
    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the function should return [3, 2, 2, 4, 2], as explained above.
Assume that:
N and M are integers within the range [1..100,000];
each element of array A is an integer within the range [1..N + 1].
Complexity:
expected worst-case time complexity is O(N+M);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/

// you can also use imports, for example:
// import java.math.*;
import java.util.Arrays;

/*
Test Report                                             Score: 77 of 100
test                                                    time    result
example 
example test                                            0.650 s.    OK
extreme_small 
all max_counter operations                              0.640 s.    OK
single 
only one counter                                        0.640 s.    OK
small_random1 
small random test, 6 max_counter operations             0.640 s.    OK
small_random2 
small random test, 10 max_counter operations            0.650 s.    OK
medium_random1 
medium random test, 50 max_counter operations           0.670 s.    OK
medium_random2 
medium random test, 500 max_counter operations          0.660 s.    OK
large_random1 
large random test, 2120 max_counter operations          2.220 s.    OK
large_random2 
large random test, 10000 max_counter operations         3.470 s.    TIMEOUT ERROR 
                                                                    running time: >3.47 sec., time limit: 2.92 sec.
extreme_large 
all max_counter operations                              6.280 s.    TIMEOUT ERROR 
                                                                    running time: >6.28 sec., time limit: 5.96 sec.
*/

class Solution {
    public int[] solution(int N, int[] A) {
        if(N == 0) return null;
        // write your code in Java SE 7
        int[] counters = new int[N];
        int max = 0;
        for(int i = 0; i < A.length; i++) {
            int X = A[i];
            if(X >= 1 && X <= N) {  // counter X is increased by 1,
                counters[X - 1]++;
                max = Math.max(max, counters[X - 1]);
            }
            else if(X == N + 1) {   // all counters are set to the maximum value of any counter.
                for(int j = 0; j < counters.length; j++) {
                    counters[j] = max;
                }
            }
        }
        return counters;
    }
}

/*
Test Report                                             Score: 100 of 100
test                                                    time    result
example 
example test                                            0.660 s.    OK
extreme_small 
all max_counter operations                              0.650 s.    OK
single 
only one counter                                        0.650 s.    OK
small_random1 
small random test, 6 max_counter operations             0.660 s.    OK
small_random2 
small random test, 10 max_counter operations            0.660 s.    OK
medium_random1 
medium random test, 50 max_counter operations           0.670 s.    OK
medium_random2 
medium random test, 500 max_counter operations          0.670 s.    OK
large_random1 
large random test, 2120 max_counter operations          1.060 s.    OK
large_random2 
large random test, 10000 max_counter operations         1.180 s.    OK
extreme_large 
all max_counter operations                              1.850 s.    OK
*/
class Solution2 {
    public int[] solution(int N, int[] A) {
        if(N == 0) return null;
        // write your code in Java SE 7
        int[] counters = new int[N];
        // find last N + 1
        int last;
        for(last = A.length - 1; last >= 0; last--) {
            if(A[last] == N + 1) break;
        }
        // calculate max
        int max = 0, lowbound = 0;
        for(int i = 0; i < last; i++) {
            int X = A[i];
            if(X >= 1 && X <= N) {  // counter X is increased by 1
                if(counters[X - 1] < lowbound) counters[X - 1] = lowbound;
                counters[X - 1]++;
                max = Math.max(max, counters[X - 1]);
            }
            else if(X == N + 1) {   // all counters are set to the maximum value of any counter.
                lowbound = max;
            }
        }
        // change all to max
        for(int i = 0; i < N; i++) {
            counters[i] = max;
        }
        // do the left
        for(int i = last + 1; i < A.length; i++) {
            int X = A[i];
            counters[X - 1]++;  // counter X is increased by 1
        }
        return counters;
    }
}

class Main {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        int[] A = {3, 4, 4, 6, 1, 4, 4};
        System.out.println(Arrays.toString(s.solution(5, A)));
    }
}
