/*
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
*/

import java.util.*;

class Solution {
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>((int)Math.pow(2, n));
        result.add(0);
        for(int i = 0; i < n; i++) {
            ArrayList<Integer> sub = new ArrayList<Integer>(result.size());
            int bit = 1 << i;
            for(int j = 0; j < result.size(); j++) {
                int val = result.get(j) | bit;
                sub.add(val);
            }
            Collections.reverse(sub);
            result.addAll(sub);
        }
        return result;
    }

    // improve performance
    public ArrayList<Integer> grayCode2(int n) {
        ArrayList<Integer> results = new ArrayList<Integer>(1<<n);
        results.add(0);
        for (int i=0; i < n; ++i) {
            int flipper = 1<<i;
            for (int j=results.size()-1; j>=0; --j) {
                results.add(results.get(j) | flipper);
            }
        }
        return results;
    }
    /*
        Second Round
    */
    public ArrayList<Integer> grayCode3(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(0);
        for(int i = 0; i < n; i++) {
            // reverse
            for(int j = result.size() - 1; j >= 0; j--) {
                result.add((1 << i) + result.get(j));
            }
        }
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.grayCode3(3).toString());
    }
}