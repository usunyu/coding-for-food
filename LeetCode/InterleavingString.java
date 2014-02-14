class Solution {
    // recursion solution
    // Time Limit Exceeded
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        if(s1.length() == 0 && s2.length() == 0 && s3.length() == 0) return true;
        if(s1.length() == 0) return s2.equals(s3);
        if(s2.length() == 0) return s1.equals(s3);
        boolean ret = false;
        if(s1.charAt(0) == s3.charAt(0)) ret = isInterleave(s1.substring(1), s2, s3.substring(1));
        if(ret) return ret;
        if(s2.charAt(0) == s3.charAt(0)) ret = isInterleave(s1, s2.substring(1), s3.substring(1));
        return ret;
    }

    // DP solution
    public boolean isInterleave2(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) return false;
        if(s1.length() == 0) return s2.equals(s3);
        if(s2.length() == 0) return s1.equals(s3);
        // match[i][j] = true if s1[0...i] s2[0...j] matchs s3[0...i + j]
        boolean match[][] = new boolean[s1.length() + 1][s2.length() + 1];
        // initial
        match[0][0] = true;
        for(int i = 1; i < s3.length() + 1; i++) {
            char ch = s3.charAt(i - 1);
            for(int j = 1; j < i + 1; j++) {
                if(i - j <= s1.length() && j - 1 <= s2.length() && match[i - j][j - 1]) {
                    if(i - j < s1.length() && s1.charAt(i - j) == ch) match[i - j + 1][j - 1] = true;
                    if(j - 1 < s2.length() && s2.charAt(j - 1) == ch) match[i - j][j] = true;
                }
            }
        }
        return match[s1.length()][s2.length()];
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        System.out.println(solution.isInterleave2(s1, s2, s3));
    }
}