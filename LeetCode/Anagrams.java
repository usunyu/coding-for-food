/*
Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.
*/

import java.util.*;

class StrInMap {
    String str;
    boolean used;

    public StrInMap(String str) {
        this.str = str;
    }
}

class Solution {
    public int hash(String str) {
        int hash = 0;
        int mul = 1;
        int add = 0;
        for(int i = 0; i < str.length(); i++) {
            mul *= str.charAt(i);
            add += str.charAt(i);
        }
        hash = mul + add + str.length();
        return hash;
    }
    
    // time complexity : O(N*M)
    // space complexity : O(N)
    public ArrayList<String> anagrams(String[] strs) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        HashMap<Integer, StrInMap> map = new HashMap<Integer, StrInMap>();
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < strs.length; i++) {
            int hash = hash(strs[i]);
            if(!map.containsKey(hash)) {
                StrInMap sim = new StrInMap(strs[i]);
                map.put(hash, sim);
            }
            else {
                StrInMap sim = map.get(hash);
                if(!sim.used) {
                    result.add(sim.str);
                    sim.used = true;
                }
                result.add(strs[i]);
            }
        }
        return result;
    }

    /*
        Second Round
    */
    public ArrayList<String> anagrams2(String[] strs) {
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();
        for(String str : strs) {
            int hash = hash(str);
            ArrayList<String> list;
            if(map.containsKey(hash)) {
                list = map.get(hash);
            }
            else {
                list = new ArrayList<String>();
                map.put(hash, list);
            }
            list.add(str);
        }
        ArrayList<String> result = new ArrayList<String>();
        for(ArrayList<String> list : map.values()) {
            if(list.size() > 1) result.addAll(list);
        }
        return result;
    }
}

class Main {
    public static void print(ArrayList<String> list) {
        for(String str : list) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"abc", "loy", "cba", "tt", "dd", "yol", "yol"};
        ArrayList<String> result = solution.anagrams2(strs);
        print(result);
    }
}