class Solution {
    // time complexity : O(logN), worest case O(N)
    public int searchRotate(int[] A, int start, int end) {
        if(end < start) return 0;
        if(start < end && A[start] == A[end]) {
            // check side
            if(start > 0 && A[start] < A[start - 1]) {
                return start;
            }
            if(end < A.length - 1 && A[end] > A[end + 1]) {
                return end + 1;
            }
            // check middle
            while(end > 0 && A[end] == A[end - 1]) end--;
            if(end > 0 && A[end - 1] > A[end]) {
                return end;
            }
            while(start < A.length - 1 && A[start] == A[start + 1]) start++;
            if(start < A.length - 1 && A[start] > A[start + 1]) {
                return start + 1;
            }
            // [1,2,2,3,0,1,1]
            return searchRotate(A, start + 1, end -1);
        }
        int mid = start + (end - start) / 2;
        if(A[start] > A[mid]) { // rotate is in the left side
            // filter duplicate
            while(mid >= 1 && A[mid] == A[mid - 1]) mid--;
            return searchRotate(A, start, mid - 1);
        }
        else if(A[mid] > A[end]) {  // rotate is in the right side
            // filter duplicate
            while(mid < A.length && A[mid] == A[mid + 1]) mid++;
            return searchRotate(A, mid + 1, end);
        }
        else {  // rotate is in the start or end
            int prev = start - 1;
            if(prev >= 0 && A[prev] > A[start]) return start;
            int next = end + 1;
            if(next < A.length && A[next] < A[end]) return next;
            return 0;
        }
    }
    
    public boolean binarySearch(int[] A, int start, int end, int target) {
        if(end < start) return false;
        int mid = start + (end - start) / 2;
        if(A[mid] == target) return true;
        else if(A[mid] > target) {
            // filter duplicate
            while(mid >= 1 && A[mid] == A[mid - 1]) mid--;
            return binarySearch(A, start, mid - 1, target);
        }
        else {
            // filter duplicate
            while(mid < A.length && A[mid] == A[mid + 1]) mid++;
            return binarySearch(A, mid + 1, end, target);
        }
    }
    
    public boolean search(int[] A, int target) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int rotate = searchRotate(A, 0, A.length - 1);
        if(target <= A[A.length - 1] && target >= A[rotate]) {
            return binarySearch(A, rotate, A.length - 1, target);
        }
        else if(rotate > 0 && target >= A[0] && target <= A[rotate - 1]) {
            return binarySearch(A, 0, rotate - 1, target);
        }
        return false;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {3,4,4,4,4,4,4,5,5,6,6,6,6,6,6,6,7,7,7,7,7,7,8,8,8,8,8,8,8,9,9,9,9,9,9,9,9,9,10,10,10,-10,-10,-10,-9,-8,-8,-8,-8,-8,-7,-7,-7,-7,-6,-6,-6,-6,-6,-6,-6,-5,-5,-5,-4,-4,-4,-4,-3,-3,-3,-3,-3,-3,-2,-2,-2,-2,-1,-1,0,0,0,1,1,1,1,1,1,2,2,2,2,2,2,2,2,3,3,3};
        System.out.println(solution.search(A, 2));
    }
}