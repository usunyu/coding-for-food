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

    public int trap2(int[] A) {
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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {0,7,1,4,6};
        System.out.println(solution.trap2(A));
    }
}
