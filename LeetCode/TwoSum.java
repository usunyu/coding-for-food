import java.util.*;

class Solution {
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

    // time complexity: O(N^2)
    // space complexity: O(1)
    public int[] twoSum3(int[] numbers, int target) {
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
    
    // time complexity: O(NlogN)
    // space complexity: O(N)
    public int[] twoSum2(int[] numbers, int target) {
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

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers = {11, 15, 2, 7};
        int[] result = solution.twoSum(numbers, 9);
        System.out.println("index1=" + result[0] + ", index2=" + result[1]);
    }
}






