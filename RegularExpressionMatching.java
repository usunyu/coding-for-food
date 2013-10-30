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

class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		long start = System.currentTimeMillis();
		System.out.println(solution.isMatch("", "c*c*"));
		long end = System.currentTimeMillis();
		System.out.println("cost: " + (end - start));
	}
}
