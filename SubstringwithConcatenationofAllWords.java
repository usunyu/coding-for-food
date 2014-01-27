import java.util.*;

class Solution {
    private HashMap<String, Integer> prepStrMap(String[] L) {
        HashMap<String, Integer> words = new HashMap<String, Integer>();
        for(String str : L) {
            if(!words.containsKey(str)) {
                words.put(str, 1);
            }
            else {
                words.put(str, words.get(str) + 1);
            }
        }
        return words;
    }

    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        if(L.length == 0) return indices;
        int len = L[0].length();
        HashMap<String, Integer> words = prepStrMap(L);

        for(int i = 0; i <= S.length() - L.length * len; i++) {
            HashMap<String, Integer> strMap = new HashMap<String, Integer>(words);
            for(int j = i; j <= S.length() - len; j += len) {
                String str = S.substring(j, j + len);
                if(!strMap.containsKey(str)) break;
                if(strMap.get(str) > 1)
                    strMap.put(str, strMap.get(str) - 1);
                else
                    strMap.remove(str);
            }
            if(strMap.isEmpty()) {
                indices.add(i);
            }
        }
        return indices;
    }
}

class Main {
    public static void print(ArrayList<Integer> indices) {
        for(int i : indices) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String S = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] L = {"fooo","barr","wing","ding","wing"};
        print(solution.findSubstring(S, L));
    }
}
