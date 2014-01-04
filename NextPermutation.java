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
}

class Main {
    public static void print(int[] num) {
        for(int i : num) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {5, 4, 7, 5, 3, 2};
        solution.nextPermutation(num);
        print(num);
    }
}
