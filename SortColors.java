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
        solution.sortColors(A);
        print(A);S
    }
}