/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
*/

import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // char -> index
        Hashtable<Character, Integer> checkTable = new Hashtable<Character, Integer>();
        int longest = 0;
        int current = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!checkTable.containsKey(c)) {
                current++;
                checkTable.put(c, i);
            }
            else {
                int index = checkTable.get(c);
                if(current > longest) {
                    longest = current;
                }
                current = 0;
                // go back to repeat
                i = index;
                // clear table
                checkTable = new Hashtable<Character, Integer>();
            }
            if(current > longest) {
                longest = current;
            }
        }
        return longest;
    }

    public int lengthOfLongestSubstring2(String s) {
        int length = 'z';
        int[] pos = new int[length + 1];
        int begin = 0, longest = 0;
        // initial
        for(int i = 0; i < length + 1; i++) {
            pos[i] = -1;
        }
        for(int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            begin = Math.max(begin, pos[index] + 1);
            pos[index] = i;
            longest = Math.max(longest, i - begin + 1);
        }
        return longest;
    }
    /*
        Second Round
    */
    public int lengthOfLongestSubstring3(String s) {
        int[] positions = new int[26];
        int longest = 0, begin = 0, index = 0;
        for(int i = 0; i < s.length(); i++) {
            index = s.charAt(i) - 'a';
            begin = Math.max(begin, positions[index]);
            positions[index] = i + 1;
            longest = Math.max(longest, i - begin + 1);
        }
        return longest;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring3("qopubjguxhxdipfzwswybgfylqvjzhar"));
    }
}