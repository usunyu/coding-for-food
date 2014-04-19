/*
Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/
import java.util.Arrays;

class Solution {
    // does not change the order of array
    // time complexity : O(N)
    // space complexity : O(1)
    public int removeElement(int[] A, int elem) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int count = 0;
        int startIndex = -1;
        // find first elem
        for(int i = 0; i < A.length; i++) {
            if(A[i] == elem) {
                count++;
                startIndex = i;
                break;
            }
        }
        if(startIndex == -1) {
            return A.length;
        }
        if(startIndex + count == A.length) {
            return A.length - count;
        }
        for(int i = startIndex + count; i < A.length; i++) {
            if(A[i] == elem) {
                count++;
            }
            else {
                A[i - count] = A[i];
            }
        }
        return A.length - count;
    }
}
/*
    Second Round
*/
class Solution2 {
    public int removeElement(int[] A, int elem) {
        int first = 0, second = 0;
        while(second < A.length) {
            if(A[second] != elem) {
                A[first++] = A[second++];
            }
            else {
                second++;
            }
        }
        return first;
    }
}

class Main {
	public static void main(String[] args) {
		Solution2 solution = new Solution2();
		int[] A = {1, 2, 3, 2, 4, 5, 2, 4};
		System.out.println(Arrays.toString(A));
		System.out.println(solution.removeElement(A, 2));
		System.out.println(Arrays.toString(A));
	}
}