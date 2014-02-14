class Q9_3App {

	public static int findMagicDup(int[] A, int start, int end) {
		if(start > end)
			return -1;

		int mid = (start + end) / 2;

		if(A[mid] == mid)
			return mid;

		// search left
		int leftIndex = Math.min(mid - 1, A[mid]);
		int left = findMagicDup(A, start, leftIndex);

		if(left >= 0)
			return left;

		// search right
		int rightIndex = Math.max(mid + 1, A[mid]);
		int right = findMagicDup(A, rightIndex, end);

		return right;
	}

	public static int findMagic(int[] A) {
		int magic = -1;
		for(int i = 0; i < A.length; i++) {
			if(A[i] > i)
				break;
			if(A[i] == i) {
				magic = i;
				break;
			}
		}
		return magic;
	}

	public static int findMagic(int[] A, int start, int end) {
		if(start > end)
			return -1;

		int mid = (start + end) / 2;

		if(A[mid] == mid)
			return mid;

		if(A[mid] < mid)
			return findMagic(A, mid + 1, end);

		if(A[mid] > mid)
			return findMagic(A, start, mid - 1);

		return -1;
	}

	public static void main(String[] args) {
		int[] A = {-9, -5, 1, 3, 9, 10, 11, 12};
		System.out.println("The magic number is " + findMagic(A));
		System.out.println("The magic number is " + findMagic(A, 0, A.length - 1));
		int[] D = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
		System.out.println("The magic number(has dup) is " + findMagicDup(D, 0, D.length - 1));
	}
}