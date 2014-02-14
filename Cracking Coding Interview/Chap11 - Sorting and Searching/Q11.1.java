class Q11_1App {
	public static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void mergeArray(int[] A, int[] B) {
		int n = B.length;
		int index = A.length - 1;
		int preIndex = index;
		for(int i = B.length - 1; i >= 0; i--) {
			while(index >= 0 && n > 0) {
				if(A[index] != 0) {
					if(A[index] < B[i]) {
						for(int j = preIndex - n; j >= index + 1; j--) {
							A[j + n] = A[j];
						}
						A[index + n] = B[i];
						n--;
						preIndex = index + n;
						break;
					}
				}
				index--;
			}
		}
	}

	public static void main(String[] args) {
		int[] A = new int[20];
		int[] B = new int[5];
		A[0] = 1; A[1] = 3; A[2] = 5; A[3] = 7; A[4] = 9;
		A[5] = 11; A[6] = 13; A[7] = 15; A[8] = 17; A[9] = 19;
		B[0] = 2; B[1] = 4; B[2] = 6; B[3] = 8; B[4] = 10;
		System.out.print("A: ");
		printArray(A);
		System.out.print("B: ");
		printArray(B);
		mergeArray(A, B);
		System.out.print("M: ");
		printArray(A);
	}
}