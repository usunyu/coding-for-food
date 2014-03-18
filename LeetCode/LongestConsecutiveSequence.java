/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
*/

import java.util.*;

class Solution {

    public int longestConsecutive(int[] num) {
        if(num.length == 0) return 0;
        HashSet<Integer> set = new HashSet<Integer>();
        // cache
        for(int i : num) {
            set.add(i);
        }
        HashSet<Integer> visited = new HashSet<Integer>();
        // calculate
        int longest = 1;
        for(int i : num) {
            if(visited.contains(i)) continue;
            else {
                int local = 1;
                // search left
                int p = i;
                while(true) {
                    p--;
                    if(set.contains(p)) {
                        local++;
                        visited.add(p);
                    }
                    else break;
                }
                // search right
                p = i;
                while(true) {
                    p++;
                    if(set.contains(p)) {
                        local++;
                        visited.add(p);
                    }
                    else break;
                }
                if(local > longest) longest = local;
            }
        }
        return longest;
    }

    private int merge(HashMap<Integer, Integer> map, int left, int right) {
        int upper = right + map.get(right) - 1;
        int lower = left - map.get(left) + 1;
        int len = upper - lower + 1;
        map.put(upper, len);
        map.put(lower, len);
        return len;
    }

    // cluster merge
    public int longestConsecutive2(int[] num) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 1;
        for(int i : num) {
            if(map.containsKey(i)) continue;
            map.put(i, 1);
            if(map.containsKey(i - 1)) {
                max = Math.max(max, merge(map, i - 1, i));
            }
            if(map.containsKey(i + 1)) {
                max = Math.max(max, merge(map, i, i + 1));
            }
        }
        return max;
    }

    /*
        Second Round
    */
    public int longestConsecutive3(int[] num) {
        if(num == null || num.length == 0) return 0;
        // <index, size in this index range>
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        for(int i : num) {
            if(map.containsKey(i)) continue;    // skip
            int size = 1, left = 0, right = 0;
            if(map.containsKey(i - 1))  // check left
                left = map.get(i - 1);  // left size
            if(map.containsKey(i + 1))  // check right
                right = map.get(i + 1); // right size
            size += left + right;   // total
            if(map.containsKey(i - 1))
                map.put(i - left, size);    // merge left
            if(map.containsKey(i + 1))
                map.put(i + right, size);   // merge right
            max = Math.max(size, max);
            map.put(i, size);
        }
        return max;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive3(num));
    }
}