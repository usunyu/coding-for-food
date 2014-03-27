/*
Given an array A of N integers, we draw N discs in a 2D plane such that the I-th disc is centered on (0,I) and has a radius of A[I]. 
We say that the J-th disc and K-th disc intersect if J != K and J-th and K-th discs have at least one common point.
Write a function:
class Solution { public int solution(int[] A); }
that, given an array A describing N discs as explained above, returns the number of pairs of intersecting discs. For example, 
given N=6 and:
A[0] = 1  A[1] = 5  A[2] = 2 
A[3] = 1  A[4] = 4  A[5] = 0  
intersecting discs appear in eleven pairs of elements:
0 and 1,
0 and 2,
0 and 4,
1 and 2,
1 and 3,
1 and 4,
1 and 5,
2 and 3,
2 and 4,
3 and 4,
4 and 5.
so the function should return 11.
The function should return -1 if the number of intersecting pairs exceeds 10,000,000.
Assume that:
N is an integer within the range [0..100,000];
each element of array A is an integer within the range [02147483647].
Complexity:
expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/
// you can also use imports, for example:
// import java.math.*;
/*
Test Report                                             Score: 100 of 100
test                                                    time    result
example1 
example test                                            0.650 s.    OK
simple1                                                 0.660 s.    OK
simple2                                                 0.650 s.    OK
simple3                                                 0.650 s.    OK
extreme_small 
empty and [10]                                          0.650 s.    OK
small1                                                  0.650 s.    OK
small2                                                  0.650 s.    OK
small3                                                  0.650 s.    OK
overflow 
arithmetic overflow tests                               0.650 s.    OK
medium1                                                 0.660 s.    OK
medium2                                                 0.660 s.    OK
medium3                                                 0.690 s.    OK
medium4                                                 0.750 s.    OK
10M_intersections 
10.000.000 intersections                                0.730 s.    OK
big1                                                    0.650 s.    OK
big2                                                    0.650 s.    OK
big3 
[0]*50000                                               0.650 s.    OK
*/
import java.util.Arrays;
import java.util.PriorityQueue;
class Segment implements Comparable<Segment> {
	int start, end;
	
	public Segment(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Segment o) {
		return this.start - o.start;
	}

	@Override
	public String toString() {
		return "Segment [start=" + start + ", end=" + end + "]";
	}
}

class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 7
    	Segment[] segments = new Segment[A.length];
    	for(int i = 0; i < A.length; i++) {
    		segments[i] = new Segment(i - A[i], i + A[i]);
    	}
    	// sort based on start
    	Arrays.sort(segments);
    	PriorityQueue<Integer> depth = new PriorityQueue<Integer>();
    	int count = 0;
    	for(int i = 0; i < segments.length; i++) {
    		Segment seg = segments[i];
    		// pop all smaller one
    		while(!depth.isEmpty() && depth.peek() < seg.start) {
    			depth.poll();
    		}
    		count += depth.size();
    		if(count > 10000000) {
    			return -1;
    		}
    		depth.add(seg.end);
    	}
    	return count;
    }
}

class Main {
	public static void main(String[] args) {
		Solution s = new Solution();  
		int[] A = {1, 5, 2, 1, 4, 0};
		System.out.println(s.solution(A));
	}
}