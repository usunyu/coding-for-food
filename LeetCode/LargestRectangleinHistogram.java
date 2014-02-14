import java.util.*;

class Solution {
    private int largestRectangleArea(int[] height, int left, int right) {
        if(right < left) return 0;
        int lowHeight = Integer.MAX_VALUE, lowIndex = 0;
        for(int i = left; i <= right; i++) {
            if(height[i] <= lowHeight) {
                lowHeight = height[i];
                lowIndex = i;
            }
        }
        return Math.max((right - left + 1) * lowHeight, 
            Math.max(largestRectangleArea(height, left, lowIndex - 1), largestRectangleArea(height, lowIndex + 1, right)));
    }

    // divide and conquer
    // Time Limit Exceeded
    public int largestRectangleArea(int[] height) {
        return largestRectangleArea(height, 0, height.length - 1);
    }

    public int largestRectangleArea2(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int largest = 0, cur;
        for(cur = 0; cur < height.length; cur++) {
            if(stack.isEmpty()) {
                stack.push(cur);
            }
            else {
                while(!stack.isEmpty() && height[stack.peek()] > height[cur]) {
                    int p = stack.pop();
                    int width = stack.isEmpty() ? cur : cur - stack.peek() - 1;
                    int area = width * height[p];
                    largest = Math.max(largest, area);
                }
                stack.push(cur);
            }
        }
        while(!stack.isEmpty()) {
            int p = stack.pop();
            int width = stack.isEmpty() ? cur : cur - stack.peek() - 1;
            int area = width * height[p];
            largest = Math.max(largest, area);
        }
        return largest;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {0,0,0,0,0,0,0,0,2147483647};
        System.out.println(solution.largestRectangleArea(height));
    }
}
