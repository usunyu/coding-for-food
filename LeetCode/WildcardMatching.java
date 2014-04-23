/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") -> false
isMatch("aa","aa") -> true
isMatch("aaa","aa") -> false
isMatch("aa", "*") -> true
isMatch("aa", "a*") -> true
isMatch("ab", "?*") -> true
isMatch("aab", "c*a*b") -> false
*/

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
}

class Solution2 {
	// Time Limit Exceeded
	// recursion
	// using set as cache
	public boolean isMatch(String s, String p) {
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
		return isMatch(s, p, cache, p.length());
	}

	public boolean isMatch(String s, String p, HashSet<Integer> cache,
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
			boolean result = isMatch(s.substring(index), p.substring(index), cache, shift);
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
					result = isMatch(subS, subP, cache, shift);
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
}

class Solution3 {
	// dynamic programming
	public boolean isMatch(String s, String p) {
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
/*
	Second Round
*/
// Time Limit Exceeded
class Solution4 {
    public boolean isMatch(String s, String p) {
        if(s == null) return p == null;
        if(p.length() == 0) return s.length() == 0;
        int N = s.length(), M = p.length();
        Stack<Integer>[] cache = new Stack[M];
        for(int i = 0; i < M; i++) cache[i] = new Stack<Integer>();
        int sIt = 0, pIt = 0;
        while(pIt < M) {
            if(p.charAt(pIt) == '*') {  // can match any
                if(pIt + 1 < M && p.charAt(pIt + 1) == '*');	// skip duplicate *
                else {
                	if(pIt + 1 == M) return true;
                    while(sIt < N) cache[pIt + 1].push(sIt++);
                }
                pIt++;
            }
            else if(sIt < N && (s.charAt(sIt) == p.charAt(pIt) || p.charAt(pIt) == '?')) {   // match
                sIt++; pIt++;
            }
            else { // not match
                while(pIt >= 0 && cache[pIt].isEmpty()) pIt--;
                if(pIt < 0) return false;   // no cache
                sIt = (int)(cache[pIt].pop());
            }
            if(pIt == M && sIt == N) return true;
        }
        return false;
    }
}

class Solution5 {
	public boolean isMatch(String s, String p) {
        if(s == null) return p == null;
        if(p.length() == 0) return s.length() == 0;
        int N = s.length(), M = p.length();
        int sIt = 0, pIt = 0, sBack = -1, pBack = -1;
        while(pIt < M || sBack != -1) {
            if(pIt < M && p.charAt(pIt) == '*') {  // can match any
            	while(pIt < M && p.charAt(pIt) == '*') pIt++;	// skip duplicate *
            	if(pIt == M) return true;
            	sBack = sIt;
            	pBack = pIt;
            }
            else if(pIt < M && sIt < N && (s.charAt(sIt) == p.charAt(pIt) || p.charAt(pIt) == '?')) {   // match
                sIt++; pIt++;
            }
            else { // not match
                if(sBack == -1 || sBack >= N || sIt == N) return false;	// no backup choice
                sIt = ++sBack;
                pIt = pBack;
            }
            if(pIt == M && sIt == N) return true;
        }
        return false;
    }
}

class Solution6 {
    public boolean isMatch(String s, String p) {
        if(s == null) return p == null;
        if(p.length() == 0) return s.length() == 0;
        int N = s.length(), M = p.length();
        int sIt = 0, pIt = 0, sBack = -1, pBack = -1;
        while(sIt < N) {
            if(pIt < M && (s.charAt(sIt) == p.charAt(pIt) || p.charAt(pIt) == '?')) {   // match
                sIt++; pIt++;
            }
            else if(pIt < M && p.charAt(pIt) == '*') {  // can match any
                while(pIt < M && p.charAt(pIt) == '*') pIt++;	// skip duplicate *
                if(pIt == M) return true;
                sBack = sIt;
            	pBack = pIt;
            }
            else {
                if(sBack == -1) return false;   // no backup choice
                sIt = ++sBack;
                pIt = pBack;
            }
        }
        while(pIt < M && p.charAt(pIt) == '*') pIt++;
        return pIt == M && sIt == N;
    }
}

class Main {
	public static void main(String[] args) {
		Solution5 solution = new Solution5();
		long start = System.currentTimeMillis();
		System.out.println(solution.isMatch("cabab", "*ab"));
		long end = System.currentTimeMillis();
		System.out.println("cost: " + (end - start) + " ms");
	}
}
