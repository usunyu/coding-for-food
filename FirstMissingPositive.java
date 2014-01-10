import java.util.*;

class Solution {
    // time complexity: O(NlogN)
    public int firstMissingPositive(int[] A) {
        if(A.length == 0) return 1;
        Arrays.sort(A);
        int i;
        for(i = 0; i < A.length - 1; i++) {
            if(A[i] > 0) break;
        }
        if(A[i] > 1) return 1;
        for(; i < A.length - 1; i++) {
            if(A[i + 1] - A[i] > 1) return A[i] + 1;
        }
        return A[A.length - 1] + 1;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {3,4,-1,1};
        System.out.println(solution.firstMissingPositive(A));
    }
}
