/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") -> false
isMatch("aa","aa") -> true
isMatch("aaa","aa") -> false
isMatch("aa", "a*") -> true
isMatch("aa", ".*") -> true
isMatch("ab", ".*") -> true
isMatch("aab", "c*a*b") -> true
*/

import java.util.*;

class InterMatchResult {
	int sIndex;
	int pIndex;

	public InterMatchResult(int s, int p) {
		sIndex = s;
		pIndex = p;
	}
}

class Solution {
	// DP solution first write
	public boolean isMatch(String s, String p) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		// cheat, judge last first, but remove it can still pass
		if(s.length() > 0 && p.length() > 0 && p.charAt(p.length() - 1) != '.' && p.charAt(p.length() - 1) != '*' && p.charAt(p.length() - 1) != s.charAt(s.length() - 1)) {
			return false;
		}
		Stack<InterMatchResult> interMatchStack = new Stack<InterMatchResult>();
		InterMatchResult initial = new InterMatchResult(0, 0);
		interMatchStack.push(initial);
		while (!interMatchStack.isEmpty()) {
			InterMatchResult inter = interMatchStack.pop();
			int sp = inter.sIndex, pp = inter.pIndex;
			char prevChar = '#';
			InterMatchResult interAdd;
			while (sp < s.length() && pp < p.length()) {
				if (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '.') { // match
					sp++;
					pp++;
				} else if (p.charAt(pp) == '*') {
					// store all the possibile case
					interAdd = new InterMatchResult(sp, pp + 1);		// case 1 skip *
					interMatchStack.push(interAdd);
					interAdd = new InterMatchResult(sp - 1, pp + 1);	// case 2 skip prev matched char
					interMatchStack.push(interAdd);
					// case 3 do not skip *, start match
					if(prevChar != '.') {	// match all prev char
						while (sp < s.length() && s.charAt(sp) == prevChar) {
							sp++;
							interAdd = new InterMatchResult(sp, pp + 1);
							interMatchStack.push(interAdd);
						}
						break;
					}
					else {	// prevChar = . , can match all char
						while(sp < s.length()) {
							sp++;
							interAdd = new InterMatchResult(sp, pp + 1);
							interMatchStack.push(interAdd);
						}
						break;
					}
				} else { // not match
					// check the pattern's next, whether it is *
					if ((pp + 1) < p.length() && p.charAt(pp + 1) == '*') { // still match
						interAdd = new InterMatchResult(sp, pp + 2);
						interMatchStack.push(interAdd);
					}
					break;
				}
				prevChar = p.charAt(pp - 1);
			}
			if (sp == s.length() && pp == p.length()) {
				return true;
			} else if(sp == s.length()) {
				if(pp + 1 < p.length() && p.charAt(pp + 1) == '*') {
					// match
					interAdd = new InterMatchResult(sp, pp + 2);
					interMatchStack.push(interAdd);
				}
				if(p.charAt(pp) == '*') {
					// skip prev match at last
					interAdd = new InterMatchResult(sp - 1, pp + 1);
					interMatchStack.push(interAdd);
					// match
					interAdd = new InterMatchResult(sp, pp + 1);
					interMatchStack.push(interAdd);
				}
			}
		}
		return false;
	}
}

class Solution2 {
	// recursion solution
	public boolean isMatch(String s, String p) {
		if(s == null) return p == null;
		return m(s, p, 0, 0);
	}

	public boolean m(String s, String p, int sp, int pp){
		if(pp == p.length()) return sp == s.length();
		if(pp == p.length() - 1 || p.charAt(pp + 1) != '*') {
			if(sp == s.length()) return false;
			return (p.charAt(pp) == '.' || p.charAt(pp) == s.charAt(sp)) && m(s, p, ++sp, ++pp);
		}
		while(sp < s.length() && (p.charAt(pp) == '.' || p.charAt(pp) == s.charAt(sp))) {
			if(m(s, p, sp++, pp + 2)) return true;
		}
		return m(s, p, sp, pp + 2);
	}
}

class Solution3 {
    // DP solution concise
	public boolean isMatch(String s, String p) {
		// IMPORTANT: Please reset any member data you declared, as
		// the same Solution instance will be reused for each test case.
		if(s.length() > 0 && p.length() > 0 && p.charAt(p.length() - 1) != '.' && p.charAt(p.length() - 1) != '*' && p.charAt(p.length() - 1) != s.charAt(s.length() - 1)) {
			return false;
		}
		Stack<InterMatchResult> interMatchStack = new Stack<InterMatchResult>();
		interMatchStack.push(new InterMatchResult(0, 0));
		while (!interMatchStack.isEmpty()) {
			InterMatchResult inter = interMatchStack.pop();
			int sp = inter.sIndex, pp = inter.pIndex;
			if(sp == s.length() && pp == p.length()) return true;
			if(pp == p.length()) continue;
			while(sp < s.length() && pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '.')) {
				sp++; pp++;
				if(sp == s.length() && pp == p.length()) return true;
				if(pp < p.length() && p.charAt(pp) == '*') {
					sp--; pp--;
					break;
				}
			}
			if(pp + 1 < p.length() && p.charAt(pp + 1) == '*') {
				while(sp < s.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '.')) {
					interMatchStack.push(new InterMatchResult(sp++, pp + 2));
				}
				interMatchStack.push(new InterMatchResult(sp, pp + 2));
			}
		}
		return false;
	}
}

/*
    Second Round
*/
// DP solution
class Solution4 {
    public boolean isMatch(String s, String p) {
        if(s == null) return p == null;
        if (p.isEmpty()) return s.isEmpty();
        int N = s.length(), M = p.length();
        Stack[] cache = new Stack[M];
        for(int i = 0; i < M; i++) cache[i] = new Stack<Integer>();	// initial
        int sIt = 0, pIt = 0;
        while(pIt >= 0 && pIt < M) {	// matching
        	if(pIt + 1 < M && p.charAt(pIt + 1) == '*') {	// next is *
        		while(sIt < N && p.charAt(pIt) == s.charAt(sIt)) {	// try to match
        			if(pIt + 2 < M) cache[pIt + 2].push(sIt);	// store possibile
        			sIt++;
        		}
        		if(p.charAt(pIt) == '.') {	// match any
        			while(sIt < N) {
        				if(pIt + 2 < M) cache[pIt + 2].push(sIt);	// store possibile
        				sIt++;
        			}
        		}
        		pIt += 2;
        	}
        	else if(sIt < N && (p.charAt(pIt) == s.charAt(sIt) || p.charAt(pIt) == '.')) {	// match
        		pIt++; sIt++;
        	}
        	else {	// not match
        		while(pIt >= 0) {
        			if(!cache[pIt].isEmpty()) break;
        			pIt--;
        		}
        		if(pIt >= 0)
        			sIt = (int)(cache[pIt].pop());
        	}
        	if(pIt == M && sIt == N) return true;
        }
        return false;
    }
}

// http://leetcode.com/2011/09/regular-expression-matching.html
class Solution5 {
	private boolean match(String s, String p, int sIt, int pIt) {
		int N = s.length(), M = p.length();
		if(pIt == M) return sIt == N;
		// next char is not '*': must match current character
		if(pIt + 1 == M || p.charAt(pIt + 1) != '*')
			return sIt < N && (s.charAt(sIt) == p.charAt(pIt) || p.charAt(pIt) == '.') && match(s, p, sIt + 1, pIt + 1);
		// next char is '*'
		while(sIt < N && (s.charAt(sIt) == p.charAt(pIt) || p.charAt(pIt) == '.')) {
			if(match(s, p, sIt, pIt + 2)) return true;
			sIt++;
		}
		return match(s, p, sIt, pIt + 2);	// skip case
	}

	public boolean isMatch(String s, String p) {
		return match(s, p, 0, 0);
	}
}

class Main {
	public static void main(String[] args) {
		Solution5 solution = new Solution5();
		long start = System.currentTimeMillis();
		System.out.println(solution.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
		long end = System.currentTimeMillis();
		System.out.println("cost: " + (end - start) + " ms");
	}
}
