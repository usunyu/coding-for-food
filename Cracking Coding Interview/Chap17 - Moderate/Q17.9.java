import java.util.HashSet;
import java.util.HashMap;

class Main {
	public static HashMap<String, Integer> getFrequency(String book, HashSet<Character> punctuation) {
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();
		for(int i = 0; i < book.length(); i++) {
			char ch = book.charAt(i);
			if(!punctuation.contains(ch)) {
				sb.append(ch);
			}
			else {
				if(sb.length() != 0) {
					String word = sb.toString();
					if(frequency.containsKey(word)) {
						frequency.put(word, frequency.get(word) + 1);
					}
					else {
						frequency.put(word, 1);
					}
					sb = new StringBuilder();
				}
			}
		}
		return frequency;
	}

	public static void main(String[] args) {
		String book = "Hi, this is a test book. book. book!";
		char[] punctuationArray = {'.', ',', '!', ' '};
		HashSet<Character> punctuation = new HashSet<Character>();
		for(char ch : punctuationArray) {
			punctuation.add(ch);
		}
		HashMap<String, Integer> frequency = getFrequency(book, punctuation);
		for(String word : frequency.keySet()) {
			System.out.println(word + " : " + frequency.get(word));
		}
	}
}