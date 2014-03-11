/*
Given a matrix with 1's and 0's, a rectangle can be made with 1's. What is the maximum area of the rectangle. 

00010 
11100 
11110 
11000 
11010 In this test case the result needs to be 8. 

How: 
00010 00010 
11100 11 100 
11110 11 110 
11000 11 000 
11010 11 010 

If you see above the 11's are used from the first two columns and last four rows making the area or count of 1's to be 8.

http://www.careercup.com/question?id=6299074475065344
http://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
*/

class Main {
	public static int maxAreaOfRect(int[][] M) {
		int[][] S = new int[M.length][M[0].length];
		// preprocess
		for(int i = 0; i < M.length; i++) S[i][0] = M[i][0];
		for(int i = 0; i < M[0].length; i++) S[0][i] = M[0][i];
		for(int i = 1; i < M.length; i++) {
			for(int j = 1; j < M[0].length; j++) {
				if(M[i][j] == 1) {
					S[i][j] = Math.min(S[i - 1][j - 1], Math.min(S[i - 1][j], S[i][j - 1])) + 1;
				}
			}
		}
		// get max
		int max = 0;
		for(int i = 0; i < S.length; i++) {
			for(int j = 0; j < S[0].length; j++) {
				max = Math.max(S[i][j], max);
			}
		}
		return max * max;
	}

	public static void main(String[] args) {
		int M[][] = {
			{0, 1, 1, 0, 1}, 
			{1, 1, 0, 1, 0}, 
			{0, 1, 1, 1, 0},
			{1, 1, 1, 1, 0},
			{1, 1, 1, 1, 1},
			{0, 0, 0, 0, 0}
		};
		System.out.println(maxAreaOfRect(M));
	}
}