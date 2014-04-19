/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
*/

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

class Solution2 {
    public int trap(int[] A) {
        if(A.length == 0) return 0;
        // find left
        int left = 1, prev = A[0];
        while(left < A.length) {
            if(A[left] >= prev) {
                prev = A[left++];
            }
            else break;
        }
        left--;
        // scan
        int V = 0;
        while(left < A.length) {
            int current = left + 1;
            int v = 0;
            while(current < A.length) {
                if(A[current] >= A[left]) {
                    V += v;
                    break;
                }
                else {
                    v += A[left] - A[current];
                }
                current++;
            }
            if(current == A.length) {
                // reverse scan
                int right = A.length - 2;
                // find right
                prev = A[A.length - 1];
                while(right > left) {
                    if(A[right] >= prev) {
                        prev = A[right--];
                    }
                    else break;
                }
                right++;
                while(right > left) {
                    current = right - 1;
                    v = 0;
                    while(current > left) {
                        if(A[current] >= A[right]) {
                            break;
                        }
                        else {
                            v = A[right] - A[current];
                            V += v;
                        }
                        current--;
                    }
                    right = current;
                }
                break;
            }
            left = current;
        }
        return V;
    }
}

/*
    Second Round
*/
class Solution3 {
    public int trap(int[] A) {
        if(A == null || A.length == 0) return 0;
        int left = 0;
        while(left < A.length && A[left] == 0) left++;  // skip zeros
        int current = left + 1, tmp = 0, sum = 0;
        while(current < A.length) { // from left to right
            if(A[current] >= A[left]) {
                sum += tmp; // add temp
                tmp = 0;    // reset
                left = current; // move left
            }
            else tmp += A[left] - A[current];
            current++;
        }
        int right = A.length - 1;
        while(right >= left && A[right] == 0) right--;  // skip zeros
        current = right - 1; tmp = 0;
        while(current >= left) { // from right to left
            if(A[current] >= A[right]) {
                sum += tmp; // add temp
                tmp = 0;    // reset
                right = current;    // move right
            }
            else tmp += A[right] - A[current];
            current--;
        }
        return sum;
    }
}

class Solution4 {
    public int trap(int[] A) {
        if(A == null || A.length == 0) return 0;
        int N = A.length;
        int[] leftBound = new int[N], rightBound = new int[N];
        int lmax = A[0];
        for(int i = 0; i < N; i++) {
            lmax = Math.max(A[i], lmax);
            leftBound[i] = lmax;
        }
        int rmax = A[N - 1];
        for(int i = N - 1; i >= 0; i--) {
            rmax = Math.max(A[i], rmax);
            rightBound[i] = rmax;
        }
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += Math.min(leftBound[i], rightBound[i]) - A[i];
        }
        return sum;
    }
}

class Main {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int[] A = {5,4,1,2};
        System.out.println(solution.trap(A));
    }
}
