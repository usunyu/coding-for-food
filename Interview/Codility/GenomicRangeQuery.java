/*
A non-empty zero-indexed string S is given. String S consists of N characters from the set of upper-case English letters A, C, G, T.
This string actually represents a DNA sequence, and the upper-case letters represent single nucleotides.
You are also given non-empty zero-indexed arrays P and Q consisting of M integers. These arrays represent queries about minimal 
nucleotides. We represent the letters of string S as integers 1, 2, 3, 4 in arrays P and Q, where A = 1, C = 2, G = 3, T = 4, 
and we assume that A < C < G < T.
Query K requires you to find the minimal nucleotide from the range (P[K], Q[K]), 0 <= P[i] <= Q[i] < N.
For example, consider string S = GACACCATA and arrays P, Q such that:
    P[0] = 0    Q[0] = 8
    P[1] = 0    Q[1] = 2
    P[2] = 4    Q[2] = 5
    P[3] = 7    Q[3] = 7
The minimal nucleotides from these ranges are as follows:
(0, 8) is A identified by 1,
(0, 2) is A identified by 1,
(4, 5) is C identified by 2,
(7, 7) is T identified by 4.
Write a function:
class Solution { public int[] solution(String S, int[] P, int[] Q); }
that, given a non-empty zero-indexed string S consisting of N characters and two non-empty zero-indexed arrays P and Q 
consisting of M integers, returns an array consisting of M characters specifying the consecutive answers to all queries.
The sequence should be returned as:
a Results structure (in C), or
a vector of integers (in C++), or
a Results record (in Pascal), or
an array of integers (in any other programming language).
For example, given the string S = GACACCATA and arrays P, Q such that:
    P[0] = 0    Q[0] = 8
    P[1] = 0    Q[1] = 2
    P[2] = 4    Q[2] = 5
    P[3] = 7    Q[3] = 7
the function should return the values [1, 1, 2, 4], as explained above.
Assume that:
N is an integer within the range [1..100,000];
M is an integer within the range [1..50,000];
each element of array P, Q is an integer within the range [0..N - 1];
P[i] <= Q[i];
string S consists only of upper-case English letters A, C, G, T.
Complexity:
expected worst-case time complexity is O(N+M);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/

// you can also use imports, for example:
// import java.math.*;
import java.util.Arrays;
class Solution {
    private int getInt(char ch) {
        int ret = 0;
        switch(ch) {
            case 'A':
                ret = 1;
                break;
            case 'C':
                ret = 2;
                break;
            case 'G':
                ret = 3;
                break;
            case 'T':
                ret = 4;
                break;
        }
        return ret;
    }

    /*
    Test Report                                             Score: 62 of 100
    example 
    example test                                            0.660 s.    OK
    extreme_sinlge 
    single character string                                 0.660 s.    OK
    extreme_double 
    double character string                                 0.660 s.    OK
    simple 
    simple tests                                            0.660 s.    OK
    small_length_string 
    small length simple string                              0.660 s.    OK
    small_random 
    small random string, length = ~300                      0.660 s.    OK
    almost_all_same_letters 
    GGGGGG..??..GGGGGG..??..GGGGGG                          4.360 s.    TIMEOUT ERROR 
                                                                        running time: >4.36 sec., time limit: 3.52 sec.
    large_random 
    large random string, length                             4.390 s.    TIMEOUT ERROR 
                                                                        running time: >4.39 sec., time limit: 3.48 sec.
    extreme_large 
    all max ranges                                          4.360 s.    TIMEOUT ERROR 
                                                            running time: >4.36 sec., time limit: 3.96 sec.
    */
    public int[] solution(String S, int[] P, int[] Q) {
        // write your code in Java SE 7
    	// get smallest value
        int length = P.length;
        int MIN = 5;
        for(int i = 0; i < S.length(); i++) {
        	int val = getInt(S.charAt(i));
        	MIN = Math.min(MIN, val);
        	if(MIN == 1) break;
        }
        int[] mins = new int[length];
        for(int i = 0; i < length; i++) {
            int min = 5;
            for(int j = P[i]; j <= Q[i]; j++) {
                int val = getInt(S.charAt(j));
                min = Math.min(min, val);
                if(min == MIN) break;
            }
            mins[i] = min;
        }
        return mins;
    }

    /*
    Test Report                                             Score: 100 of 100
    test                                                    time    result
    example 
    example test                                            0.640 s.    OK
    extreme_sinlge 
    single character string                                 0.650 s.    OK
    extreme_double 
    double character string                                 0.650 s.    OK
    simple 
    simple tests                                            0.650 s.    OK
    small_length_string 
    small length simple string                              0.650 s.    OK
    small_random 
    small random string, length = ~300                      0.670 s.    OK
    almost_all_same_letters 
    GGGGGG..??..GGGGGG..??..GGGGGG                          0.780 s.    OK
    large_random 
    large random string, length                             1.350 s.    OK
    extreme_large 
    all max ranges                                          1.470 s.    OK

    */
    // reference: http://rafal.io/posts/codility-genomic-range-query.html
    public int[] solution2(String S, int[] P, int[] Q) {
        // write your code in Java SE 7
        int[][] table = new int[S.length()][4];
        // initial
        table[0][getInt(S.charAt(0)) - 1] = 1;
        for(int i = 1; i < S.length(); i++) {
            int id = getInt(S.charAt(i)) - 1;
            table[i][id] = table[i - 1][id] + 1;
            for(int j = 0; j < 4; j++) {
                if(j != id) table[i][j] = table[i - 1][j];
            }
        }
        // query
        int[] mins = new int[P.length];
        for(int i = 0; i < P.length; i++) {
            int x = P[i], y = Q[i];
            for(int j = 0; j < 4; j++) {
                int pre = x - 1 >= 0 ? table[x - 1][j] : 0;
                if(table[y][j] - pre > 0) {
                    mins[i] = j + 1;
                    break;
                }
            }
        }
        return mins;
    }
}

class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] P = {0, 0, 4, 7};
		int[] Q = {8, 2, 5, 7};
		System.out.println(Arrays.toString(s.solution("GACACCATA", P, Q)));
	}
}
