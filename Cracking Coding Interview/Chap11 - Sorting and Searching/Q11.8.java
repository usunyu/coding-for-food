/*
Imagine you are reading in a stream of integers. Periodically, you wish to be able to look up the rank of a number x 
(the number of values less than or equal to x). Implement the data structures and algorithms to support these 
operations. That is, implement the method track(int x), which is called when each number is generated, and the
method get RankOfNumber(int x), which returns the numberof values less than or equal to x (not including x itself).
*/
import java.util.*;
import CtCILibrary.AssortedMethods;

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

class Solution {

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
/*
	Second Round
*/
class RankNode {
	public int left_size = 0;
	public RankNode left, right;
	public int data = 0;
	public int count = 1;

	public RankNode(int d) {
		data = d;
	}

	public void insert(int d) {
		if(d == data) {
			count++;
		}
		if(d < data) {
			if(left == null) 
				left = new RankNode(d);
			else 
				left.insert(d);
			left_size++;
		} else if(d > data) {
			if(right == null)
				right = new RankNode(d);
			else 
				right.insert(d);
		}
	}

	public int getRank(int d) {
		if(d == data) {
			return left_size;
		}
		else if(d < data) {
			if(left == null) 
				return 0;
			else 
				return left.getRank(d);
		}
		else {
			if(right == null)
				return left_size + 1;
			else 
				return left_size + 1 + right.getRank(d);
		}
	}
}

class RankSystem {
	private static RankNode root;

	public void track(int d) {
		if(root == null)
			root = new RankNode(d);
		else
			root.insert(d);
	}

	public int getRank(int d) {
		return root.getRank(d);
	}
}

class Solution2 {
	public static void main(String[] args) {
		RankSystem rankSys = new RankSystem();
		int size = 100;
		int[] list = AssortedMethods.randomArray(size, -100, 100);
		for (int i = 0; i < list.length; i++) {
			rankSys.track(list[i]);
		}

		int[] tracker = new int[size];
		for (int i = 0; i < list.length; i++) {
			int v = list[i];
			int rank1 = rankSys.getRank(list[i]);
			tracker[rank1] = v;		
		}

		for (int i = 0; i < tracker.length - 1; i++) {
			if (tracker[i] != 0 && tracker[i + 1] != 0) {
				if (tracker[i] > tracker[i + 1]) {
					System.out.println("ERROR at " + i);
				}
			}
		}

		System.out.println("Array: " + AssortedMethods.arrayToString(list));
		System.out.println("Ranks: " + AssortedMethods.arrayToString(tracker));
	}
}
