/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/

import java.util.Arrays;

class Solution {
    public void sortColors(int[] A) {
        int[] colorCount = new int[3];
        for(int i = 0; i < A.length; i++) {
            colorCount[A[i]]++;
        }
        int current = 0;
        for(int j = 0; j < 3; j++) {
            int i;
            for(i = current; i < current + colorCount[j]; i++) {
                A[i] = j;
            }
            current = i;
        }
    }
}

class Solution2 {
    private void swap(int[] A, int i1, int i2) {
        int temp = A[i1];
        A[i1] = A[i2];
        A[i2] = temp;
    }

    // one-pass solution
    public void sortColors(int[] A) {
        int pRed = 0, pBlue = A.length - 1, pCurrent = 0;
        while(pCurrent <= pBlue) {
            switch (A[pCurrent]) {
                case 0: // red
                swap(A, pRed++, pCurrent++);
                break;
                case 1: // white
                pCurrent++;
                break;
                case 2: // blue
                swap(A, pBlue--, pCurrent);
                break;
            }
        }
    }
}

/*
    Second Round
*/
class Solution3 {
    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void sortColors(int[] A) {
        int red = 0, white = 0, blue = A.length - 1;
        while(white <= blue) {
            if(A[white] == 0) {    // find red
                swap(A, white++, red++);
            }
            else if(A[white] == 2) {    // find blue
                swap(A, white, blue--);
            }
            else
                white++;
        }
    }
}
/*
    Third Round
*/
class Solution4 {
    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    public void sortColors(int[] A) {
        if(A == null || A.length == 0) return;
        int n = A.length;
        int red = 0, current = 0, blue = n - 1;
        while(current <= blue) {
            if(A[current] == 0)
                swap(A, red++, current++);
            else if(A[current] == 2)
                swap(A, blue--, current);
            else
                current++;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        int[] A = {0,1,1,0,0,2,0,2,0,1};
        System.out.println(Arrays.toString(A));
        solution.sortColors(A);
        System.out.println(Arrays.toString(A));
    }
}