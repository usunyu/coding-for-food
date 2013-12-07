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
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive(num));
    }
}