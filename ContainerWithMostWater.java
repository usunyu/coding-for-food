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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {2, 3, 2, 4};
        System.out.println(solution.maxArea2(height));
    }
}
