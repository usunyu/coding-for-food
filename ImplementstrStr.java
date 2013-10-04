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
}

class Main {

    public static void main(String[] args) {
    	String haystack = "";
    	String needle = "";
        Solution solution = new Solution();
        String str = solution.strStr(haystack, needle);
        System.out.println(str);
    }
}