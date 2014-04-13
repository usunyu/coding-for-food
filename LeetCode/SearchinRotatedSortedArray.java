/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

class Solution {
	public int searchRotate(int[] A, int start, int end) {
		if (start + 1 == end) {
			return end;
		}
		int mid = start + (end - start) / 2;
		if (A[mid] > A[end]) {
			return searchRotate(A, mid, end);
		} else if (A[start] > A[mid]) {
			return searchRotate(A, start, mid);
		} else {
			return start;
		}
	}

	public int binarySearch(int[] A, int target, int start, int end) {
		if(start > end) {
			return -1;
		}
		int mid = start + (end - start) / 2;
		if (A[mid] > target) {
			return binarySearch(A, target, start, mid - 1);
		} else if (A[mid] < target) {
			return binarySearch(A, target, mid + 1, end);
		} else {
			return mid;
		}
	}

	public int search(int[] A, int target) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		int rotate = searchRotate(A, 0, A.length - 1);
		if (target <= A[A.length - 1] && target >= A[rotate]) {
			return binarySearch(A, target, rotate, A.length - 1);
		} else if (rotate > 0 && target <= A[rotate - 1] && target >= A[0]) {
			return binarySearch(A, target, 0, rotate - 1);
		}
		return -1;
	}
}

/*
	Second Round
*/
class Solution2 {
    public int search(int[] A, int target) {
        int left = 0, right = A.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(A[mid] == target) return mid;
            else if(A[left] <= A[mid]) {
                if(A[left] <= target && A[mid] > target)
                    right = mid - 1;
                else 
                    left = mid + 1;
            }
            else {
                if(A[mid] < target && A[right] >= target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
}

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		//int[] A = { 11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] A = { 1, 3, 5};
		System.out.println(solution.search(A, 2));
	}
}