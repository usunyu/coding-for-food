import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

class WordHelper {
	String word;
	WordHelper prev;

	public WordHelper(String w, WordHelper p) {
		word = w;
		prev = p;
	}
}

class Dict {
	HashSet<String> dictionary;
	HashMap<String, ArrayList<String>> wordNexts;

	public Dict(HashSet<String> dict) {
		dictionary = dict;
	}

	private void addWord(String word, String next) {
		if(wordNexts.containsKey(word)) {
			wordNexts.get(word).add(next);
		}
		else {
			ArrayList<String> nexts = new ArrayList<String>();
			nexts.add(next);
			wordNexts.put(word, nexts);
		}
	}

	public void preProcess() {
		wordNexts = new HashMap<String, ArrayList<String>>();
		// assume all small letter
		for(String word : dictionary) {
			for(int i = 0; i < word.length(); i++) {
				StringBuilder sb = new StringBuilder(word);
				char original = sb.charAt(i);
				for(int j = 0; j < 26; j++) {
					char next = (char)('a' + j);
					if(original == next) continue;
					sb.setCharAt(i, next);
					String str = sb.toString();
					if(dictionary.contains(str)) {
						addWord(word, str);
					}
				}
			}
		}
	}

	// using bfs
	public ArrayList<ArrayList<String>> findTransStep(String start, String end) {
		// swap start and end
        String tmp = start; start = end; end = tmp;
		ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
		LinkedList<WordHelper> queue = new LinkedList<WordHelper>();
		queue.add(new WordHelper(start, null));
		int length = 0, max = Integer.MAX_VALUE;
		// add cache
		HashSet<String> cache = new HashSet<String>();
		while(!queue.isEmpty()) {
			length++;
			if(length > max) break;
			LinkedList<WordHelper> queueTemp = new LinkedList<WordHelper>();
			while(!queue.isEmpty()) {
				WordHelper wordHelper = queue.remove();
				cache.add(wordHelper.word);
				if(wordHelper.word.equals(end)) {
					max = length;
					ArrayList<String> path = new ArrayList<String>();
					while(wordHelper != null) {
						path.add(wordHelper.word);
						wordHelper = wordHelper.prev;
					}
					paths.add(path);
				}
				else if(wordNexts.containsKey(wordHelper.word)) {
					for(String str : wordNexts.get(wordHelper.word)) {
						if(!cache.contains(str))
							queueTemp.add(new WordHelper(str, wordHelper));
					}
				}
			}
			queue = queueTemp;
		}
		return paths;
	}
}

class Main {
	public static void print(ArrayList<ArrayList<String>> steps) {
		for(ArrayList<String> path : steps) {
			System.out.println(path);
		}
	}

	public static void main(String[] args) {
		HashSet<String> dict = new HashSet<String>();
		dict.add("hello");
		dict.add("hallo");
		dict.add("halto");
		dict.add("abcde");
		dict.add("hapto");
		Dict dictClass = new Dict(dict);
		dictClass.preProcess();
		ArrayList<ArrayList<String>> paths = dictClass.findTransStep("hello", "hapto");
		print(paths);
	}
}