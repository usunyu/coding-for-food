/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines,
which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/

class Solution {
    // Time Limit Exceeded
    public int maxArea(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length; i++) {
            for(int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[i], height[j]);
                int w = j - i;
                int area = h * w;
                if(area > maxArea) maxArea = area;
            }
        }
        return maxArea;
    }

    public int maxArea2(int[] height) {
        // find two heighest lines
        int i1 = 0, i2 = 0;
        for(int i = 1; i < height.length; i++) {
            int h = height[i];
            if(h > height[i1]) {
                i2 = i1;
                i1 = i;
            }
            else if(h > height[i2]) {
                i2 = i;
            }
        }
        if(i1 > i2) {
            int t = i1;
            i1 = i2;
            i2 = t;
        }
        int maxArea = 0;
        // search
        for(int i = i1; i >= 0; i--) {
            for(int j = i2; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                if(area > maxArea) maxArea = area;
            }
        }
        return maxArea;
    }

    /*
        Second Round
    */
    public int maxArea3(int[] height) {
        if(height.length == 0) return 0;
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while(left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(area, maxArea);
            if(height[left] > height[right]) right--;
            else left++;
        }
        return maxArea;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {2, 3, 2, 4};
        System.out.println(solution.maxArea3(height));
    }
}
