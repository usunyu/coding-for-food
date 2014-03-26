/*
You are helping the port management to organize boats moored in a port. There is a straight wharf with N mooring bollards and N boats.
The wharf (and the dock in front of it) is of length M. Each boat has the same width: 2*X. The bollards are located at the very edge 
of the wharf. It is possible for more than one bollard to be at the same position.

You have to moor each boat to a separate bollard so that the following rules are satisfied:

each boat is fixed with a single mooring rope to the bank of the wharf,
the mooring rope connects the middle of the boat's bow with a bollard,
the sides of the boats can touch each other,
boats cannot overlap,
boats cannot be placed outside the dock or extend it,
two boats cannot be tied to the same bollard.
All the boats must have mooring ropes of the same length. The goal is to minimize this length.

More formally, let the max_distance be the largest distance between the middle of any boat's bow and the bollard to which the boat 
is moored. The goal is to align the boats so that the max_distance is as small as possible. You are given a non-empty zero-indexed 
array R of N integers, and two positive integers X and M. Array R contains the positions of the bollards along the wharf. 
The wharf's ends are at positions 0 and M.

For example, the following array R, and integers X and M:

  R[0] = 1    X = 2
  R[1] = 3    M = 16
  R[2] = 14
describe:

three bollards at positions 1, 3 and 14,
three boats of width 4,
a wharf of length 16.
You can set:

the center of the first boat at position 2,
the center of the second boat at position 6,
the center of the third boat at position 14.


Between the first boat and the first ring the distance is 1; between the second boat and the second ring it is 3; and between 
the third boat and the third ring it is 0; so the max_distance is 3.

Write a function:

class Solution { public int solution(int[] R, int X, int M); }

that, given a zero-indexed array R consisting of N integers, given two integers X and M, returns the minimal max_distance you 
can achieve.

If it is not possible to tie all the boats, the function should return −1.

For example, given the following array R, integers X and M:

  R[0] = 1    X = 2
  R[1] = 3    M = 16
  R[2] = 14
the function should return 3, as explained above.

Assume that:

N is an integer within the range [1..100,000];
X and M are integers within the range [1..1,000,000,000];
each element of array R is an integer within the range [0..M];
array R is sorted in non-decreasing order.
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
 */

// you can also use imports, for example:
// import java.math.*;
import java.util.Arrays;

class Solution {
	// greedy solution
	public int solution(int[] R, int X, int M) {
		// write your code in Java SE 7
		int N = R.length;
		if (N * X * 2 > M)
			return -1; // If it is not possible to tie all the boats, the function should return -1.
		int[] P = new int[N];
		// place boat from left first
		int left;
		for (left = 0; left < N; left++) {
			int fromLeft = X * (2 * left + 1);
			if (R[left] <= fromLeft) {
				P[left] = fromLeft; // we have no choice
			} else
				break;
		}
		// adjust boat from left
		int boats = N - left - 1;	// boats right waiting for place
		for (int i = left; i < N; i++) {
			if(M - (boats * 2 + 1) * X > R[i]) {	// we have enough space on right
				if(i == left || P[i - 1] + 2 * X <= R[i]) {	// i == left, no need to check
					P[i] = R[i];
				}
				else {
					P[i] = P[i - 1] + 2 * X;
				}
				boats--;
			}
			else {
				while(i < N) {
					P[i++] = M - (boats * 2 + 1) * X;
					boats--;
				}
			}
		}
		// re-adjust, find all continues boats
		int current = 0;
		int leftBorder = X;
		while(current < N) {
			int ptr = current + 1;
			int localMax = Math.abs(P[current] - R[current]);
			while(ptr < N) {
				if(P[ptr] == P[ptr - 1] + 2 * X) {	// continue
					localMax = Math.max(localMax, Math.abs(P[ptr] - R[ptr]));
					ptr++;
				}
				else 
					break;
			}
			// try move left and recalculate
			if(localMax > 1) {
				while(P[current] > leftBorder) {	// we can move left
					int curMax = 0;
					for(int i = current; i < ptr; i++) {
						P[i]--;
						curMax = Math.max(curMax, Math.abs(P[i] - R[i]));
					}
					if(curMax > localMax) {	// no need to continue
						// one step back;
						for(int i = current; i < ptr; i++) {
							P[i]++;
						}
						break;
					}
					localMax = curMax;
				}
			}
			current = ptr;
			leftBorder = P[ptr - 1] + X;
		}
		
		// calculate
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, Math.abs(P[i] - R[i]));
		}
		// debug
		System.out.println("R: " + Arrays.toString(R));
		System.out.println("P: " + Arrays.toString(P));
		return max;
	}
}

class Main {
	public static void main(String[] args) {
		int[] R = {4, 7, 9, 11, 14, 21, 21, 22, 22, 23};
		int X = 1, M = 37;
		Solution s = new Solution();
		System.out.println(s.solution(R, X, M));
	}
}
