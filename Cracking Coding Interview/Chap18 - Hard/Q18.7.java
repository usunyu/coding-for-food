import java.util.HashSet;

class Flag {
	boolean contains;

	public Flag(boolean c) {
		contains = c;
	}
}

class Main {
	private static boolean searchWord(String word, HashSet<String> set, Flag flag) {
		boolean contains = false;
		for(int i = 1; i <= word.length(); i++) {
			String sub = word.substring(0, i);
			if(set.contains(sub)) {
				if(i == word.length() && flag.contains) contains = true;
				else contains = searchWord(word.substring(i), set, new Flag(true));
			}
			if(contains) break;
		}
		return contains;
	}

	public static String findLongestWord(String[] words) {
		HashSet<String> set = new HashSet<String>();
		for(String word : words) {
			set.add(word);
		}
		String longestWord = "";
		// search word
		for(String word : words) {
			if(word.length() > longestWord.length()) {
				if(searchWord(word, set, new Flag(false))) {
					longestWord = word;
				}
			}
		}
		return longestWord;
	}

	public static void main(String[] args) {
		String[] words = {"hello", "nihao", "ohaiyou", "hellonihao", "xxxxxxxooooooo"};
		System.out.println(findLongestWord(words));
	}
}