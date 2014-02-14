class Solution {
    public int jump(int[] A) {
        if(A.length <= 1) return 0;
        int next = A.length - 1;
        int min = 0;
        while(next > 0) {
            int current = 0;
            for(int i = next - 1; i >= 0; i--) {
                if(A[i] >= (next - i)) {
                    current = i;
                }
            }
            next = current;
            min++;
        }
        return min;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {2,3,1,1,4};
        System.out.println(solution.jump(A));
    }
}
