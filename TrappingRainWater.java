class Solution {
    public int trap(int[] A) {
        int left = 0;
        int V = 0;
        while(left < A.length - 1) {
            int prev = A[left++];
            // find left
            while(left < A.length) {
                if(A[left] >= prev) {
                    prev = A[left++];
                }
                else break;
            }
            left--;
            if(left == A.length - 1) break;
            // find right, next one which higher than left or highest one but lower than left
            int i = 0;
            prev = A[left];
            // skip decrease
            for(int j = left + 1; j < A.length; j++) {
                if(A[j] <= prev) {
                    prev = A[j];
                    i = j;
                }
                else break;
            }
            if(i == A.length - 1) break;
            int right = i;
            i++;
            while(i < A.length) {
                if(A[i] >= A[left]) {
                    right = i;
                    break;
                }
                if(A[i] >= A[right]) {
                    right = i;
                }
                i++;
            }
            // calculate
            int height = Math.min(A[left], A[right]);
            for(int j = left + 1; j < right; j++) {
                int v = height - A[j];
                if(v < 0) v = 0;
                V += v;
            }
            left = right;
        }
        return V;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {5,3,7,7};
        System.out.println(solution.trap(A));
    }
}
