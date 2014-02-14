import java.util.HashMap;

class Solution {
    public int singleNumber(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i : A) {
            if(map.containsKey(i)) {
                if(map.get(i) == 2) {   // third time
                    map.remove(i);
                }
                else {  // second time
                    map.put(i, 2);
                }
            }
            else {  // first time
                map.put(i, 1);
            }
        }
        int ret = 0;
        for(int i : map.keySet()) {
            ret = i;
        }
        return ret;
    }

    public int singleNumber2(int[] A) {
        int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < A.length; i++) {
            twos |= ones & A[i];
            ones ^= A[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 3, 3, 1, 5, 3, 1, 5, 1, 5};
        System.out.println(solution.singleNumber2(nums));
    }
}