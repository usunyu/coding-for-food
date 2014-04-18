/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) 
are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
*/

import java.util.*;

class Solution {
    // time complexity: O(N)
    // space complexity: O(N)
    public int[] twoSum(int[] numbers, int target) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for(int i = 0; i < numbers.length; i++) {
            int value = numbers[i];
            int need = target - value;
            if(map.containsKey(need)) {
                result[0] = map.get(need) + 1;
                result[1] = i + 1;
                break;
            }
            if(!map.containsKey(value)) {
                map.put(value, i);
            }
        }
        return result;
    }
}

class Solution2 {
    public void quickSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }
    
    public void quickSort(int[] numbers, int start, int end) {
        if(start >= end) {
            return;
        }
        int index = partition(numbers, start, end);
        quickSort(numbers, start, index - 1);
        quickSort(numbers, index, end);
    }
    
    public void swap(int[] numbers, int i1, int i2) {
        int temp = numbers[i1];
        numbers[i1] = numbers[i2];
        numbers[i2] = temp;
    }
    
    public int partition(int[] numbers, int start, int end) {
        int mid = (start + end) / 2;
        int pivot = numbers[mid];
        while(start <= end) {
            while(numbers[start] < pivot) {
                start++;
            }
            while(numbers[end] > pivot) {
                end--;
            }
            if(start <= end) {
                swap(numbers, start, end);
                start++;
                end--;
            }
        }
        return start;
    }

    public int[] findIndex(int[] origin, int v1, int v2) {
        int[] result = {-1, -1};
        for(int i = 0; i < origin.length; i++) {
            if(result[0] != -1 && result[1] != -1) {
                break;
            }
            if(origin[i] == v1) {
                if(result[0] == -1) {
                    result[0] = i + 1;
                }
                else {
                    result[1] = i + 1;
                }
            }
            else if(origin[i] == v2) {
                if(result[0] == -1) {
                    result[0] = i + 1;
                }
                else {
                    result[1] = i + 1;
                }
            }
        }
        return result;
    }

    // time complexity: O(NlogN)
    // space complexity: O(N)
    public int[] twoSum(int[] numbers, int target) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int[] origin = numbers.clone();
        quickSort(numbers);
        int i = 0;
        int j = numbers.length - 1;
        int[] result = new int[2];
        while(i <= j) {
            int sum = numbers[i] + numbers[j];
            if(sum > target) {
                j--;
            }
            else if(sum < target) {
                i++;
            }
            else {
                result = findIndex(origin, numbers[i], numbers[j]);
                if(result[0] > result[1]) {
                    int temp = result[0];
                    result[0] = result[1];
                    result[1] = temp;
                }
                break;
            }
        }
        return result;
    }
}

class Solution3 {
    // time complexity: O(N^2)
    // space complexity: O(1)
    public int[] twoSum(int[] numbers, int target) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int[] result = new int[2];
        boolean find = false;
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                int sum = numbers[i] + numbers[j];
                if(sum == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    find = true;
                    break;
                }
            }
            if(find) {
                break;
            }
        }
        return result;
    }
}
/*
    Second Round
*/
class Solution4 {
    public int[] twoSum(int[] numbers, int target) {
        int[] ret = new int[2];
        if(numbers == null || numbers.length < 2) return ret;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < numbers.length; i++) {
            int need = target - numbers[i];
            if(map.containsKey(need)) {
                ret[0] = map.get(need) + 1;
                ret[1] = i + 1;
                break;
            }
            else map.put(numbers[i], i);
        }
        return ret;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers = {11, 15, 2, 7};
        int target = 9;
        System.out.println("Input: numbers=" + Arrays.toString(numbers) + ", target=" + target);
        int[] result = solution.twoSum(numbers, target);
        System.out.println("Output: index1=" + result[0] + ", index2=" + result[1]);
    }
}






