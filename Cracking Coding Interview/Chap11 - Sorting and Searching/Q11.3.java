/*
Given a sorted array of n integers that has been rotated an unknown number of times, 
write code to find an element in the array. You may assume that the array was originally sorted 
in increasing order.
*/

// time complexity : O(logN)
// space complexity : O(1)

class Solution {
	// find rotate point
	public static int find(int[] array, int start, int end) {
		if(end == start + 1 && array[end] < array[start])
			return start;
		int mid = (start + end) / 2;
		if(array[start] > array[mid])
			return find(array, start, mid);
		if(array[mid] > array[end])
			return find(array, mid, end);
		if(array[start] == array[mid]) {
			int result = -1;
			if(start < mid) {
				result = find(array, start, mid);
				if(result == -1 && mid < end)
					result = find(array, mid, end);
			}
			return result;
		}
		return -1;
	}
	// find value
	public static int find(int[] array, int start, int end, int value) {
		int mid = (start + end) / 2;
		if(start > end)
			return -1;
		if(array[mid] > value) {
			return find(array, start, mid - 1, value);
		}
		else if(array[mid] < value) {
			return find(array, mid + 1, end, value);
		}
		else
			return mid;
	}

	public static void main(String[] args) {
		// int[] array = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		int[] array = {3, 3, 3, 5, 3, 3, 3, 3, 3, 3};
		int index = find(array, 0, array.length - 1);
		System.out.println("Rotate point: " + index);
		int vndex = find(array, 0, index, 5);
		if(vndex == -1)
			vndex = find(array, index + 1, array.length - 1, 5);
		System.out.println("Target index: " + vndex);
	}
}
/*
	Second Round
*/
class Solution2 {
	public static int search(int[] array, int value) {
		int left = 0, right = array.length - 1;
		while(left <= right) {
			while(left <= right && array[left] == array[right]) {
				if(array[left] == value) return left;
				left++; right--;
			}
			if(left > right) return -1;
			int mid = left + (right - left) / 2;
			if(array[mid] == value) return mid;
			else if(array[left] <= array[mid]) {	// left is not rotate
				if(array[left] <= value && array[mid] > value)
					right = mid - 1;
				else 
					left = mid + 1;
			}
			else {	// right is not rotate
				if(array[mid] < value && array[right] >= value)
					left = mid + 1;
				else 
					right = mid - 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		// int[] array = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		int[] array = {3, 3, 3, 5, 3, 3, 3, 3, 3, 3};
		System.out.println("Target index: " + search(array, 5));
	}
}
