import java.util.*;

class Solution {
    // Time Limit Exceeded
    // Time Complexity: O(N^4)
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        if(num.length < 4) return null;
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        for(int i1 = 0; i1 <= num.length - 4; i1++) {
            for(int i2 = i1 + 1; i2 <= num.length - 3; i2++) {
                for(int i3 = i2 + 1; i3 <= num.length - 2; i3++) {
                    for(int i4 = i3 + 1; i4 <= num.length - 1; i4++) {
                        if(num[i1] + num[i2] + num[i3] + num[i4] == target) {
                            ArrayList<Integer> quadruplets = new ArrayList<Integer>();
                            quadruplets.add(num[i1]);
                            quadruplets.add(num[i2]);
                            quadruplets.add(num[i3]);
                            quadruplets.add(num[i4]);
                            results.add(quadruplets);
                        }
                    }
                }
            }
        }
        return results;
    }

    // Time Complexity: O(N^3)
    public ArrayList<ArrayList<Integer>> fourSum2(int[] num, int target) {
        if(num.length < 4) return null;
        Arrays.sort(num);
        HashSet<Integer> cache = new HashSet<Integer>();
        for(int i : num) {
            cache.add(i);
        }
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        for(int i1 = 0; i1 <= num.length - 4; i1++) {
            for(int i2 = i1 + 1; i2 <= num.length - 3; i2++) {
                for(int i3 = i2 + 1; i3 <= num.length - 2; i3++) {
                    int need = target - num[i1] - num[i2] - num[i3];
                    if(need > num[i3] && cache.contains(need)) {
                        ArrayList<Integer> quadruplets = new ArrayList<Integer>();
                        quadruplets.add(num[i1]);
                        quadruplets.add(num[i2]);
                        quadruplets.add(num[i3]);
                        quadruplets.add(need);
                        results.add(quadruplets);
                    }
                    if(need < num[i3]) break;
                }
            }
        }
        return results;
    }
}

class Main {
    public static void print(ArrayList<ArrayList<Integer>> results) {
        for(ArrayList<Integer> quadruplets : results) {
            for(int n : quadruplets) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = {1, 0, -1, 0, -2, 2};
        print(solution.fourSum2(array, 0));
    }
}
