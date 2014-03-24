public class SearchInBitonic {
	public static int binarySearch(int[] a, int left, int right, int key) {
		int lo = left, hi = right;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if(key < a[mid]) hi = mid - 1;
			else if(key > a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}

	public static int binarySearchR(int[] a, int left, int right, int key) {
		int lo = left, hi = right;
		while(lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if(key > a[mid]) hi = mid - 1;
			else if(key < a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}

	public static int search(int[] a, int key) {
		// search highest
		int left = 0, right = a.length - 1;
		int largest = 0;
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(a[mid] > a[mid - 1] && a[mid] > a[mid + 1]) {	// largest one
				largest = mid;
				break;
			}
			else if(a[mid] < a[mid - 1]) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		if(a[largest] == key)
			return largest;
		int ret = binarySearch(a, 0, largest - 1, key);
		if(ret != -1) return ret;
		ret = binarySearchR(a, largest + 1, a.length - 1, key);
		return ret;
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 6, 8, 9, 12, 7, 5, 4, 3};
		System.out.println(search(array, 4));
	}
}