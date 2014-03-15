/*
http://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
*/

class Main {
	public static void print(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	public static int[] computeLPSArray(String pattern) {
		int[] lps = new int[pattern.length()];
		int match = 0, i = 1;
		while(i < pattern.length()) {
			if(pattern.charAt(i) == pattern.charAt(match)) {
				match++;
				lps[i++] = match;
			}
			else if(match > 0) {
				match = lps[match - 1];
			}
			else i++;
		}
		return lps;
	}

	public static void KMPSearch(String text, String pattern) {
		int[] lps = computeLPSArray(pattern);
		int i = 0, j = 0;
		while(i < text.length()) {
			if(text.charAt(i) == pattern.charAt(j)) {
				i++; j++;
			}
			else if(j > 0) {
				j = lps[j - 1];
			}
			else i++;
			if(j == pattern.length()) {
				System.out.println("Found pattern at index " + (i - j));
				j = lps[j - 1];
			}
		}
	}

	public static void main(String[] args) {
		String text = "AABAACAABDAABAAABAA", pattern = "AABA";
		print(computeLPSArray(pattern));
		KMPSearch(text, pattern);
	}
}