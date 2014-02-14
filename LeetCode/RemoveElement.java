class Solution {
    // does not change the order of array
    // time complexity : O(N)
    // space complexity : O(1)
    public int removeElement(int[] A, int elem) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int count = 0;
        int startIndex = -1;
        // find first elem
        for(int i = 0; i < A.length; i++) {
            if(A[i] == elem) {
                count++;
                startIndex = i;
                break;
            }
        }
        if(startIndex == -1) {
            return A.length;
        }
        if(startIndex + count == A.length) {
            return A.length - count;
        }
        for(int i = startIndex + count; i < A.length; i++) {
            if(A[i] == elem) {
                count++;
            }
            else {
                A[i - count] = A[i];
            }
        }
        return A.length - count;
    }
}

class Main {
	public static void print(int[] A) {
		for(int i : A) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] A = {1, 2, 3, 2, 4, 5, 2, 4};
		print(A);
		System.out.println(solution.removeElement(A, 2));
		print(A);
	}
}