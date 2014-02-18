class Main {
	public static int largestSum(int[] array) {
		int max = 0, sum = 0;
		int maxLeft = 0, maxRight = -1;
		int left = 0, right = -1;
		for(int i = 0; i < array.length; i++) {
			sum += array[i];
			if(sum > max) {
				max = sum;
				right = i;
				maxLeft = left;
				maxRight = right;
			}
			if(sum < 0) {
				left = i + 1;
				sum = 0;
			}
		}
		System.out.println("Max is: " + max);
		System.out.print("[");
		for(int i = maxLeft; i <= maxRight; i++) {
			if(i == maxRight)
				System.out.print(array[i]);
			else
				System.out.print(array[i] + ", ");
		}
		System.out.println("]");
		return max;
	}

	public static void main(String[] args) {
		int[] array = {2, -8, 3, -2, 4, -10};
		largestSum(array);
	}
}