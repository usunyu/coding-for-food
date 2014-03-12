/*
http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
*/
import SunLib.Methods;

class Main {
	public static int[] computeLPSArray(String pattern) {
		int[] lps = new int[pattern.length()];
		int len = 0;
		for(int i = 1; i < pattern.length(); i++) {
			int index = lps[i - 1];

		}
		return lps;
	}

	public static void KMPSearch() {

	}

	public static void main(String[] args) {
		
	}
}