import java.util.*;

class Solution {
	// Memory Limit Exceeded
	// recursion
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
			if (s.length() == 0) return true;
			if (s.length() > 0) return false;
		}
		if (s.length() == 0) {
			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) != '*')
					return false;
			}
			return true;
		}
		if (cache[s.length()][p.length()]) {
			return false;
		}

		// who's your daddy
		if (s.charAt(s.length() - 1) != p.charAt(p.length() - 1) && p.charAt(p.length() - 1) != '*' && p.charAt(p.length() - 1) != '?')
			return false;
		if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?') { // match
			int index = 1;
			while (index < s.length() && index < p.length() && (s.charAt(index) == p.charAt(index) || p.charAt(index) == '?'))
				index++;
			boolean result = isMatch(s.substring(index),
					p.substring(index), cache);
			if (!result)
				cache[s.length()][p.length()] = true;
			return result;
		} else if (p.charAt(0) == '*') {
			boolean match = false;
			// one '*' is same as multiply '*', remove multiply to reduce calculate
			int index = 1;
			while (index < p.length() && p.charAt(index) == '*')
				index++;
			String subS = null;
			String subP = p.substring(index);
			for (int i = 0; i <= s.length(); i++) {
				subS = s.substring(i);
				boolean result = false;
				if (!cache[subS.length()][subP.length()]) { // not in the cache
					result = isMatch(subS, subP, cache);
				}
				if (result) {
					match = true;
					break;
				} else {
					cache[subS.length()][subP.length()] = true;
				}
			}
			if (!match) cache[s.length()][p.length()] = true;
			return match;
		} else {
			cache[s.length()][p.length()] = true;
			return false;
		}
	}

	// Time Limit Exceeded
	// recursion
	// using set as cache
	public boolean isMatch2(String s, String p) {
		if (s == null || p == null) return false;
		// calculate count for non-wildcard char
		int count = 0;
		for (Character c : p.toCharArray()) {
			if (c != '*') ++count;
		}
		// the count should not be larger than that of s
		if (count > s.length()) return false;
		if(count == s.length()) {
			int index = 0;
			for (Character c : s.toCharArray()) {
				while(index < p.length()) {
					if(p.charAt(index) != '*') break;
					index++;
				}
				if(c == p.charAt(index) || p.charAt(index) == '?') index++;
				else return false;
			}
			return true;
		}
		// add cache to avoid duplicate calculate
		HashSet<Integer> cache = new HashSet<Integer>();
		return isMatch2(s, p, cache, p.length());
	}

	public boolean isMatch2(String s, String p, HashSet<Integer> cache,
			int shift) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		if (p.length() == 0) {
			if (s.length() == 0) return true;
			if (s.length() > 0) return false;
		}
		if (s.length() == 0) {
			for (int i = 0; i < p.length(); i++) {
				if (p.charAt(i) != '*') return false;
			}
			return true;
		}
		int id = (s.length() << shift) + p.length();
		if (cache.contains(id)) {
			return false;
		}
		// who's your daddy
		if (s.charAt(s.length() - 1) != p.charAt(p.length() - 1) && p.charAt(p.length() - 1) != '*' && p.charAt(p.length() - 1) != '?')
			return false;
		if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?') { // match
			int index = 1;
			while (index < s.length() && index < p.length() && (s.charAt(index) == p.charAt(index) || p.charAt(index) == '?'))
				index++;
			boolean result = isMatch2(s.substring(index), p.substring(index), cache, shift);
			if (!result) cache.add(id);
			return result;
		} else if (p.charAt(0) == '*') {
			boolean match = false;
			// one '*' is same as multiply '*', remove multiply to
			// reduce calculate
			int index = 1;
			while (index < p.length() && p.charAt(index) == '*')
				index++;
			String subS = null;
			String subP = p.substring(index);
			// reverse
			for (int i = s.length(); i >= 0; i--) {
				subS = s.substring(i);
				int subId = (subS.length() << shift) + subP.length();
				boolean result = false;
				if (!cache.contains(subId)) { // not in the cache
					result = isMatch2(subS, subP, cache, shift);
				}
				if (result) {
					match = true;
					break;
				} else {
					cache.add(subId);
				}
			}
			if (!match)
				cache.add(id);
			return match;
		} else {
			cache.add(id);
			return false;
		}
	}

	// dynamic programming
	public boolean isMatch3(String s, String p) {
		if (s == null || p == null)
			return false;
		// calculate count for non-wildcard char
		int count = 0;
		for (Character c : p.toCharArray()) {
			if (c != '*') ++count;
		}
		// the count should not be larger than that of s
		if (count > s.length())
			return false;

		boolean[] matches = new boolean[s.length() + 1];
		matches[0] = true;
		int pid = 0, firstMatch = 0;
		while (pid < p.length()) {
			// skip duplicate '*'
			while(pid + 1 < p.length() && p.charAt(pid) == '*' && p.charAt(pid + 1) == '*') {
				pid++;
			}

			// if '*', fill up the rest of row
			if (p.charAt(pid) == '*') {
				// fill up the rest of row with true, up to the first match in previous row
				for (int i = firstMatch + 1; i <= s.length(); ++i)
					matches[i] = true;
			} else {
				// fill up backwards:
				// - set to true if match current char and previous diagnal also match
				// - otherwise, set to false
				int match = -1;
				for (int i = s.length(); i > firstMatch; --i) {
					matches[i] = (p.charAt(pid) == s.charAt(i - 1) || p.charAt(pid) == '?') && matches[i - 1];
					if (matches[i])
						match = i;
				}
				if (match < 0)
					return false;
				firstMatch = match;
			}
			++pid;
		}

		return matches[s.length()];
	}
}

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		long begintime = System.currentTimeMillis();
		System.out.println(solution.isMatch2("aa", "*aa*"));
		long endtime = System.currentTimeMillis();
		long cost = (endtime - begintime);
		System.out.println("runtime: " + cost + " ms");
	}
}
