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

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		//int[] A = { 11, 12, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] A = { 1, 3, 5};
		System.out.println(solution.search(A, 2));
	}
}