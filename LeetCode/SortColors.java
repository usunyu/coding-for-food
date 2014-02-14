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

    private void swap(int[] A, int i1, int i2) {
        int temp = A[i1];
        A[i1] = A[i2];
        A[i2] = temp;
    }

    // one-pass solution
    public void sortColors2(int[] A) {
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

class Main {
    public static void print(int[] A) {
        for(int i : A) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {0,1,1,0,0,2,0,2,0,1};
        print(A);
        solution.sortColors2(A);
        print(A);
    }
}