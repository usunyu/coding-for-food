/*
	两个anagram string S 和 P，定义两个操作:

	1)相邻的character swap算一次操作
	2)第一个character 和最后一个character swap算一次操作

	问从S变到P的最小操作数。

	http://www.mitbbs.com/article_t/JobHunting/32628909.html
*/

import java.util.HashSet;
import java.util.LinkedList;

class Main {
	public static int bfs(String word1, String word2) {
		StringBuilder start = new StringBuilder(word1);
		HashSet<String> set = new HashSet<String>();
		LinkedList<StringBuilder> list = new LinkedList<StringBuilder>();
		list.add(start);
		set.add(word1);
		int distance = 0;
		while(!list.isEmpty()) {
			LinkedList<StringBuilder> tmp = new LinkedList<StringBuilder>();
			while(!list.isEmpty()) {
				StringBuilder word = list.remove();
				if(word.toString().equals(word2)) {
					return distance;
				}
				for(int i = 0; i < word.length(); i++) {
					StringBuilder nword = new StringBuilder(word);
					char ch = nword.charAt(i);
					int j = (i == word.length() - 1 ? 0 : i + 1);
					nword.setCharAt(i, nword.charAt(j));
					nword.setCharAt(j, ch);
					String sword = nword.toString();
					if(!set.contains(sword)) {
						set.add(sword);
						tmp.add(nword);
					}
				}
			}
			distance++;
			list = tmp;
		}
		return -1;
	}

	public static void main(String[] args) {
		String word1 = "abcccde", word2 = "accecdb";
		System.out.println(bfs(word1, word2));
	}
}