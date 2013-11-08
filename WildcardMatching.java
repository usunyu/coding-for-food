import java.util.*;

class Solution {
	// Memory Limit Exceeded
	// using array as cache
	public boolean isMatch(String s, String p) {
		// add cache to avoid duplicate calculate
		boolean[][] cache = new boolean[s.length() + 1][p.length() + 1];
		return isMatch(s, p, cache);
	}
	
	public boolean isMatch(String s, String p, boolean[][] cache) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		if (p.length() == 0) {
			if (s.length() == 0)
				return true;
			if (s.length() > 0)
				return false;
		}
		if (s.length() == 0) {
			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) != '*')
					return false;
			}
			return true;
		}
		if(cache[s.length()][p.length()]) {
			return false;
		}
		
		// who's your daddy
		if(s.charAt(s.length() - 1) != p.charAt(p.length() - 1) && p.charAt(p.length() - 1) != '*' && p.charAt(p.length() - 1) != '?') return false;
		if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?') { // match
			int index = 1;
			while(index < s.length() && index < p.length() && (s.charAt(index) == p.charAt(index) || p.charAt(index) == '?')) index++;
			boolean result = isMatch(s.substring(index), p.substring(index), cache);
			if(!result) cache[s.length()][p.length()] = true;
			return result;
		} else if (p.charAt(0) == '*') {
			boolean match = false;
			// one '*' is same as multiply '*', remove multiply to reduce calculate
			int index = 1;
			while(index < p.length() && p.charAt(index) == '*') index++;
			String subS = null;
			String subP = p.substring(index);
			for (int i = 0; i <= s.length(); i++) {
				subS = s.substring(i);
				boolean result = false;
				if(!cache[subS.length()][subP.length()]) {	// not in the cache
					result = isMatch(subS, subP, cache);
				}
				if(result) {
					match = true;
					break;
				}
				else {
					cache[subS.length()][subP.length()] = true;
				}
			}
			if(!match) cache[s.length()][p.length()] = true;
			return match;
		} else {
			cache[s.length()][p.length()] = true;
			return false;
		}
	}
	
	public boolean isMatch2(String s, String p) {
		// add cache to avoid duplicate calculate
		HashSet<String> cache = new HashSet<String>();
		return isMatch2(s, p, cache);
	}
	
	// Time Limit Exceeded
	// using set as cache
	public boolean isMatch2(String s, String p, HashSet<String> cache) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		if (p.length() == 0) {
			if (s.length() == 0)
				return true;
			if (s.length() > 0)
				return false;
		}
		if (s.length() == 0) {
			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) != '*')
					return false;
			}
			return true;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(s.length());
		sb.append(",");
		sb.append(p.length());
		String id = sb.toString();
		if(cache.contains(id)) {
			return false;
		}
		// who's your daddy
		if(s.charAt(s.length() - 1) != p.charAt(p.length() - 1) && p.charAt(p.length() - 1) != '*' && p.charAt(p.length() - 1) != '?') return false;
		if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?') { // match
			int index = 1;
			while(index < s.length() && index < p.length() && (s.charAt(index) == p.charAt(index) || p.charAt(index) == '?')) index++;
			boolean result = isMatch2(s.substring(index), p.substring(index), cache);
			if(!result) cache.add(id);
			return result;
		} else if (p.charAt(0) == '*') {
			boolean match = false;
			// one '*' is same as multiply '*', remove multiply to reduce calculate
			int index = 1;
			while(index < p.length() && p.charAt(index) == '*') index++;
			String subS = null;
			String subP = p.substring(index);
			for (int i = 0; i <= s.length(); i++) {
				subS = s.substring(i);
				sb = new StringBuffer();
				sb.append(subS.length());
				sb.append(",");
				sb.append(subP.length());
				String subId = sb.toString();
				boolean result = false;
				if(!cache.contains(subId)) {	// not in the cache
					result = isMatch2(subS, subP, cache);
				}
				if(result) {
					match = true;
					break;
				}
				else {
					cache.add(subId);
				}
			}
			if(!match) cache.add(id);
			return match;
		} else {
			cache.add(id);
			return false;
		}
	}
}

class Main {
	public static void main(String[] args) {
		Solution solution  = new Solution();
		long begintime = System.currentTimeMillis();
		System.out.println(solution.isMatch2("bababababbabbaabbbbabbbaaaaabbabbbbabaaabbbabbbaabaaaaaaaabbbbaaabababbbabababbbabbabbbbaabbbbabbaabbbabaaababaabbbaaaaaababaaabaaaabbbababbbbaaabbabbbbabaabaabaabbbbbbbaaababbbaaabbbbabbbbbabaabbbaaabbaa", "*b******b*bb*ba*a*baa****ab**aa*b**bab*bab****b*b**bbbbab**b**aab*bb*a*abb**aa*b*b*******baaaaab*b***"));
		long endtime = System.currentTimeMillis();
		long cost = (endtime - begintime);
		System.out.println("runtime: " + cost + " ms");
	}
}
