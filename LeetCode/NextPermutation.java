/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

import java.util.*;

class Solution {
    private void swap(int[] num, int i1, int i2) {
        int temp = num[i1];
        num[i1] = num[i2];
        num[i2] = temp;
    }

    // quick sort
    private void sort(int[] num, int start, int end) {
        if(start >= end) return;
        int index = partition(num, start, end);
        sort(num, start, index - 1);
        sort(num, index, end);
    }

    private int partition(int[] num, int start, int end) {
        int pivot = num[(start + end) / 2];
        while(start <= end) {
            while(num[start] < pivot) {
                start++;
            }
            while(num[end] > pivot) {
                end--;
            }
            if(start <= end) {
                swap(num, start, end);
                start++; end--;
            }
        }
        return start;
    }
    
    public void nextPermutation(int[] num) {
        // find last reverse order
        int reverse = -1;
        for(int i = num.length - 2; i >= 0; i--) {
            if(num[i + 1] > num[i]) {
                reverse = i;
                break;
            }
        }
        if(reverse == -1) { // arrangement is not possible
            Arrays.sort(num);
        }
        else {  // rearrange
            // find the rightmost number larger than reverse then switch
            for(int i = num.length - 1; i > reverse; i--) {
                if(num[i] > num[reverse]) {
                    swap(num, i, reverse);
                    break;
                }
            }
            // rearrange the rest
            sort(num, reverse + 1, num.length - 1);
        }
    }

    private void reverse(int[] num, int start, int end) {
        for(int i = start, j = end; i < j; i++, j--) {
            swap(num, i, j);
        }
    }

    // no sort
    public void nextPermutation2(int[] num) {
        // find last reverse order
        int reverse = -1;
        for(int i = num.length - 2; i >= 0; i--) {
            if(num[i + 1] > num[i]) {
                reverse = i;
                break;
            }
        }
        if(reverse == -1) { // arrangement is not possible
            Arrays.sort(num);
        }
        else {  // rearrange
            // find the rightmost number larger than reverse then switch
            int switchIndex = 0;
            for(int i = num.length - 1; i > reverse; i--) {
                if(num[i] > num[reverse]) {
                    swap(num, i, reverse);
                    switchIndex = i;
                    break;
                }
            }
            // rearrange the rest
            for(int i = switchIndex; i < num.length - 1; i++) {
                if(num[i] < num[i + 1]) {
                    swap(num, i, i + 1);
                }
            }
            reverse(num, reverse + 1, num.length - 1);
        }
    }
    /*
        Second Round
    */
    public void nextPermutation3(int[] num) {
        // find first inversion
        int inversion = -1;
        for(int i = num.length - 1; i > 0; i--) {
            if(num[i] > num[i - 1]) {
                inversion = i - 1;
                break;
            }
        }
        if(inversion == -1) {
            // reverse
            reverse(num, 0, num.length - 1);
            return;
        }
        // find the smallest higher from end
        int higher = -1;
        for(higher = num.length - 1; higher > inversion; higher --) {
            if(num[higher] > num[inversion]) break;
        }
        swap(num, higher, inversion);
        reverse(num, inversion + 1, num.length - 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {5, 4, 7, 5, 3, 2};
        solution.nextPermutation3(num);
        System.out.println(Arrays.toString(num));
    }
}
