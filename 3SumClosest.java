import java.util.*;

class WrapInt {
    boolean set;
    int sum;
}

class Solution {
    // Time Limit Exceeded
    // time complexity : O(n^3)
    private void threeSumClosest(int[] num, int target, int i1, int i2, int i3, WrapInt s) {
        int sum = num[i1] + num[i2] + num[i3];
        if(!s.set) {
            s.set = true;
            s.sum = sum;
        }
        else {
            if(Math.abs(s.sum -target) > Math.abs(sum - target)) {
                s.sum = sum;
            }
        }
        if(sum < target) {
            // move i1 if we can
            if(i2 - i1 > 1) threeSumClosest(num, target, i1 + 1, i2, i3, s);
            // move i2 if we can
            if(i3 - i2 > 1) threeSumClosest(num, target, i1, i2 + 1, i3, s);
        }
        else if(sum > target) {
            // move i3 if we can
            if(i3 - i2 > 1) threeSumClosest(num, target, i1, i2, i3 - 1, s);
        }
    }
    
    public int threeSumClosest(int[] num, int target) {
        if(num.length <= 3) {
            int sum = 0;
            for(int i = 0; i < num.length; i++) sum += num[i];
            return sum;
        }
        WrapInt s = new WrapInt();
        Arrays.sort(num);
        threeSumClosest(num, target, 0, 1, num.length - 1, s);
        return s.sum;
    }

    // time complexity : O(n^2logn)
    public int threeSumClosest2(int[] num, int target) {
        if(num.length <= 3) {
            int sum = 0;
            for(int i = 0; i < num.length; i++) sum += num[i];
            return sum;
        }
        Arrays.sort(num);
        int ret = num[0] + num[1] + num[2];
        for(int i = 0; i < num.length - 2; i++) {
            // skip duplicates
            if(i > 0 && num[i] == num[i - 1]) continue;
            for(int j = i + 1; j < num.length - 1; j++) {
                // skip duplicates
                if(j > i + 1 && num[j] == num[j-1]) continue;
                // binary search for the third element
                int start = j + 1, end = num.length - 1;
                while(start <= end) {
                    int mid = start + (end - start) / 2;
                    int sum = num[i] + num[j] + num[mid];
                    int diff = sum - target;
                    if(Math.abs(diff) < Math.abs(ret - target)) {
                        ret = sum;
                    }
                    if(diff < 0) {
                        start = mid + 1;
                    }
                    else if(diff > 0) {
                        end = mid - 1;
                    }
                    else return ret;
                }
            }
        }
        return ret;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {-10,0,-2,3,-8,1,-10,8,-8,6,-7,0,-7,2,2,-5,-8,1,-4,6};
        System.out.println(solution.threeSumClosest2(num, 18));
    }
}