/*
Implement a method rand7() given rand5(). That is, given a method that generates a random number between 0 and 4 
(inclusive), write a method that generates a random number between 0 and 6 (inclusive).
*/

class Solution {
	public static int rand7() {
		while (true) {
			int num = 5 * rand5() + rand5();
			if (num < 21) {
				return num % 7;
			}
		}
	}

	public static int rand5() {
		return (int) (Math.random() * 100) % 5;
	}

	public static void main(String[] args) {
		/* Test: call rand7 many times and inspect the results. */
		int[] arr = new int[7];
		int test_size = 1000000;
		for(int k = 0; k < test_size; k++){
			arr[rand7()]++;
		}

		for (int i = 0; i < 7; i++) {
			double percent = 100.0 * arr[i] / test_size;
			System.out.println(i + " appeared " + percent + "% of the time.");
		}
	}
}