/*
Given an array of integers, write a method to find indices m and n such that if you sorted elements m 
through n, the entire array would be sorted. Minimize n - m(that is, find the smallest such sequence).
*/

import java.util.PriorityQueue;
import java.util.Comparator;

class Range {
	int left;
	int right;

	public String toString() {
		return "(" + left + "," + right + ")";
	}
}

class Solution {
	public static Range findMinRange(int[] array) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(100);

		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(100,
			new Comparator<Integer>(){
				public int compare(Integer a, Integer b){
					if(a < b) return 1;
					else if(a == b) return 0;
					else return -1;
				}
			}
		);

		for(int i = 0; i < array.length; i++) {
			minHeap.add(array[i]);
			maxHeap.add(array[i]);
		}

		Range range = new Range();
		// search left
		for(int i = 0; i < array.length; i++) {
			int val = minHeap.poll();
			if(array[i] != val) {
				range.left = i;
				break;
			}
		}
		// search right
		for(int i = array.length - 1; i >= 0; i--) {
			int val = maxHeap.poll();
			if(array[i] != val) {
				range.right = i;
				break;
			}
		}
		return range;
	}

	public static Range findMinRange2(int[] array) {
		int left = 0, right = array.length - 1;
		// find left end
		for(int i = 0; i < array.length - 1; i++) {
			if(array[i + 1] < array[i]) {
				left = i;
				break;
			}
		}
		// find right start
		for(int i = array.length - 1; i >= 1; i--) {
			if(array[i] < array[i - 1]) {
				right = i;
				break;
			}
		}
		// find min and mx in middle
		int min = right, max = left;
		for(int i = left + 1; i <= right - 1; i++) {
			if(array[i] < array[min]) min = i;
			if(array[i] > array[max]) max = i;
		}
		Range range = new Range();
		if(left == array.length - 1) {
			return range;
		}
		// search left
		for(int i = 0; i < left; i++) {
			if(array[i] > array[min]) {
				range.left = i;
				break;
			}
		}
		// search right
		for(int i = array.length - 1; i >= right; i--) {
			if(array[i] < array[max]) {
				range.right = i;
				break;
			}
		}
		return range;
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
		System.out.println(findMinRange(array));
		System.out.println(findMinRange2(array));
	}
}