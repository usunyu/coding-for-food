/*
Implement strStr().

Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
*/

class Solution {
	// time complexity: O(N^2)
    // space complexity: O(1)
    public String strStr(String haystack, String needle) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        if(haystack == null) {
        	return null;
        }
        if(needle == null) {
        	return haystack;
        }
        if(needle.length() > haystack.length()) {
        	return null;
        }
        if(haystack.equals("") && needle.equals("")) {
        	return "";
        }
        for(int i = 0; i < haystack.length(); i++) {
            int p1 = i;
            int p2 = 0;
            while(p1 < haystack.length() && p2 < needle.length() && haystack.charAt(p1) == needle.charAt(p2)) {
                p1++;
                p2++;
            }
            if(p1 == haystack.length() && p2 < needle.length()) {	// exceed
            	return null;
            }
            if(p2 == needle.length()) { // we find it
                return haystack.substring(i);
            }
        }
        return null;
    }
    /*
        Second Round
    */
    // KMP 
    // time complexity : O(n)
    // space complexity : O(m)
    public String strStr2(String haystack, String needle) {
        if(needle == null || needle.length() == 0) return haystack;
        if(haystack == null || haystack.length() < needle.length()) return null;
        // pre-process
        int[] table = new int[needle.length()];
        int i = 1, length = 0;
        while(i < needle.length()) {
            if(needle.charAt(i) == needle.charAt(table[length])) {
                length++;
                table[i++] = length;
            }
            else if(length > 0) {
                length = table[length - 1];
            }
            else {
                i++;
            }
        }
        // kmp
        i = 0; length = 0;
        while(i < haystack.length()) {
            if(haystack.charAt(i) == needle.charAt(length)) {
                length++; i++;
            }
            else if(length > 0) {
                length = table[length - 1];
            }
            else {
                i++;
            }
            if(length == needle.length()) return haystack.substring(i - length);
        }
        return null;
    }
}

class Main {

    public static void main(String[] args) {
    	String haystack = "happyappy";
    	String needle = "app";
        Solution solution = new Solution();
        String str = solution.strStr2(haystack, needle);
        System.out.println(str);
    }
}