// reference:
// http://www.matrix67.com/blog/archives/115
// http://www.inf.fh-flensburg.de/lang/algorithmen/pattern/kmpen.htm
class Main {
	public static boolean isSubstr(String s, String t) {
		// pre-process
		int[] p = new int[t.length() + 1];
		int i = 0, j = -1;
		p[i] = j;
		while(i < t.length()) {
			while(j >= 0 && t.charAt(i) != t.charAt(j)) j = p[j];
			p[++i] = ++j;
		}
		// KMP
		i = 0; j = 0;
		while(i < s.length()) {
			while(j >= 0 && t.charAt(j) != s.charAt(i)) j = p[j];
			++j; ++i;
			if(j == t.length()) return true;
		}
		return false;
	}

	public static void main(String[] args) {
											   // 001201
		System.out.println(isSubstr("abababbaa", "ababba"));
	}
}