import java.util.*;

class Number {
	int[] intSumList;

	public Number() {
		intSumList = new int[1000];
	}

	// O(1)
	public void track(int value) {
		intSumList[value]++;
	}

	// O(N)
	public int getRankOfNumber(int value) {
		int sum = 0;
		for(int i = 0; i <= value; i++) {
			sum += intSumList[i];
		}
		sum--;
		return sum;
	}
}

class Q11_8App {

	public static void main(String[] args) {
		int[] array = {5, 1, 4, 4, 5, 9, 7, 13, 3};
		Number nObject = new Number();
		for(int i = 0; i < array.length; i++)
			nObject.track(array[i]);
		System.out.println("getRankOfNumber(1) = " + nObject.getRankOfNumber(1));
		System.out.println("getRankOfNumber(3) = " + nObject.getRankOfNumber(3));
		System.out.println("getRankOfNumber(4) = " + nObject.getRankOfNumber(4));
	}
}