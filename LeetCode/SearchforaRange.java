class Solution {
    // time complexity : O(logN), but the worst case is O(N)
    public int[] searchRange(int[] A, int target) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        int low = 0, high = A.length - 1;
        int index = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (A[mid] > target) {
                high = mid - 1;
            } else if (A[mid] < target) {
                low = mid + 1;
            } else {
                index = mid;
                break;
            }
        }
        int[] result = { -1, -1 };
        if (index != -1) {
            int left = index, right = index;
            while (left - 1 >= 0 && A[left - 1] == target)
                left--;
            while (right + 1 < A.length && A[right + 1] == target)
                right++;
            result[0] = left;
            result[1] = right;
        }
        return result;
    }
    
    public int binarySearch(int[] A, double target) {
        int low = 0, high = A.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(A[mid] > target) {
                high = mid - 1;
            }
            else if(A[mid] < target) {
                low = mid + 1;
            }
            else {
                return mid;
            }
        }
        return low;
    }
    
    // time complexity : O(logN)
    public int[] searchRange2(int[] A, int target) {
        int[] result = {-1, -1};
        int left = binarySearch(A, target - 0.5);
        if(left >= A.length || A[left] != target) {
            return result;
        }
        int right = binarySearch(A, target + 0.5) - 1;
        result[0] = left;
        result[1] = right;
        return result;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = { 5, 7, 7, 8, 8, 10 };
        int[] range = solution.searchRange2(A, 8);
        System.out.println("[" + range[0] + ", " + range[1] + "]");
    }
}