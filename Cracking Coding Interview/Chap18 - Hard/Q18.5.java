/*
You have a large text file containing words. Given any two words, find the shortest distance 
(in terms of number of words) between them in the file. If the operation will be repeated many times for the 
same file (but different pairs of words), can you optimize your solution?
*/

import java.util.ArrayList;
import java.util.HashMap;

class Solution {
	public static HashMap<String, ArrayList<Integer>> getLocationMap(String[] words) {
		HashMap<String, ArrayList<Integer>> wordLocMap = new HashMap<String, ArrayList<Integer>>();
		for(int i = 0; i < words.length; i++) {
			ArrayList<Integer> list;
			if(wordLocMap.containsKey(words[i])) {
				list = wordLocMap.get(words[i]);
			}
			else {
				list = new ArrayList<Integer>();
				wordLocMap.put(words[i], list);
			}
			list.add(i);
		}
		return wordLocMap;
	}

	public static int getMinDistance(String word1, String word2, HashMap<String, ArrayList<Integer>> wordLocMap) {
		ArrayList<Integer> list1 = wordLocMap.get(word1);
		ArrayList<Integer> list2 = wordLocMap.get(word2);
		int i1 = 0, i2 = 0;
		int min = Integer.MAX_VALUE;
		while(i1 < list1.size() && i2 < list2.size()) {
			int dis = Math.abs(list1.get(i1) - list2.get(i2));
			if(dis < min) min = dis;
			if(list1.get(i1) > list2.get(i2)) {
				i2++;
			}
			else if(list1.get(i1) < list2.get(i2)) {
				i1++;
			}
			else break;
		}
		return min;
	}

	public static void main(String[] args) {
		String[] words = {"hello", "world", "hi", "test", "hello"};
		HashMap<String, ArrayList<Integer>> wordLocMap = getLocationMap(words);
		System.out.println(getMinDistance("hello", "test", wordLocMap));
	}
}