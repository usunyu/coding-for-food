// time complexity : O(logN)
// space complexity : O(1)

class Q11_3App {
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