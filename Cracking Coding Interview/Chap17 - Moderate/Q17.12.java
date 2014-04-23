/*
Design an algorithm to find all pairs of integers within an array which sum to a specified value.
*/

import java.util.Arrays;
import java.util.HashMap;

class Solution {
	// time: O(nlogn)
	// space: O(1)
	public static void twoSum(int[] array, int target) {
		// sort
		Arrays.sort(array);
		for(int i = 0, j = array.length - 1; i < j;) {
			int sum = array[i] + array[j];
			if(sum == target) {
				System.out.print("[" + array[i] + ", " + array[j] + "]");
				i++; j--;
			}
			else if(sum < target) {
				i++;
			}
			else {
				j--;
			}
		}
		System.out.println();
	}

	// time: O(n)
	// space: O(n)
	public static void twoSum2(int[] array, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i : array) {
			if(map.containsKey(i)) {
				map.put(i, map.get(i) + 1);
			}
			else {
				map.put(i, 1);
			}
		}
		for(int i : array) {
			if(map.containsKey(i)) {
				int need = target - i;
				if(map.containsKey(need)) {
					System.out.print("[" + i + ", " + need + "]");
					if(map.get(i) == 1) {
						map.remove(i);
					}
					else {
						map.put(i, map.get(i) - 1);
					}
					if(map.get(need) == 1) {
						map.remove(need);
					}
					else {
						map.put(need, map.get(need) - 1);
					}
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] array = {4,1,6,3,8,2,4,2,7,3,5};
		twoSum(array, 6);
		twoSum2(array, 6);
	}
}