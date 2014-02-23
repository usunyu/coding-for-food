import java.util.HashMap;

class Main {
	private static boolean searchWord(String word, HashMap<String, Integer> map, boolean flag) {
		boolean contains = false;
		for(int i = 1; i <= word.length(); i++) {
			String sub = word.substring(0, i);
			if(map.containsKey(sub) && map.get(sub) > 0) {
				if(i == word.length() && flag)
					contains = true;
				else {
					map.put(sub, map.get(sub) - 1);
					contains = searchWord(word.substring(i), map, true);
					// recover
					map.put(sub, map.get(sub) + 1);
				}
			}
			if(contains) break;
		}
		return contains;
	}

	public static String findLongestWord(String[] words) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(String word : words) {
			if(map.containsKey(word))
				map.put(word, map.get(word) + 1);
			else
				map.put(word, 1);
		}
		String longestWord = "";
		// search word
		for(String word : words) {
			if(word.length() > longestWord.length()) {
				if(searchWord(word, map, false)) {
					longestWord = word;
				}
			}
		}
		return longestWord;
	}

	public static void main(String[] args) {
		String[] words = {"hello", "nihao", "ohaiyou", "hellonihaonihao", "xxxxxxxooooooo", "hellonihao"};
		System.out.println(findLongestWord(words));
	}
}