/*
	"the quick brown fox jumped over the lazy dog" -> "dog lazy the over jumped fox brown quick the"
*/

class Main {
	public static String reverseSentence(String sentence) {
		StringBuilder sb = new StringBuilder(sentence);
		int start = 0, end = 0;
		while(end < sb.length()) {
			// skip space
			while(end < sb.length() && sb.charAt(end) == ' ') {
				start++;
				end++;
			}
			// traversal the word
			while(end < sb.length() && sb.charAt(end) != ' ') end++;
			// reverse the word in place
			int i = start, j = end - 1;
			while(i < j) {
				char tmp = sb.charAt(i);
				sb.setCharAt(i, sb.charAt(j));
				sb.setCharAt(j, tmp);
				i++; j--;
			}
			start = end;
		}
		// result
		sb.reverse();
		return sb.toString();
	}

	public static void main(String[] args) {
		String sentence = "the quick brown fox jumped over the lazy dog";
		System.out.println(sentence);
		System.out.println(reverseSentence(sentence));
	}
}