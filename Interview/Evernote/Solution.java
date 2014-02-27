/*
Problem:
Write a function that takes two parameters:
	(1) a String representing a text document and 
	(2) an integer providing the number of items to return.
Implement the function such that it returns a list of Strings ordered by word frequency, 
the most frequently occurring word first. 
Use your best judgement to decide how words are separated. Your solution should run in O(n) 
time where n is the number of characters in the document. Implement this function as you would 
for a production/commercial system. You may use any standard data structures. 

Description:
I used two hashmap to store word's frequency and frequency's words.
In first iteration, I can count word's frequency in O(N), meanwhile, I also keep the record for frequency's words,
I used hashset to store these words, so that update(add and remove) operation can be done in O(1).
In the second part, I extract the k frequency words from my data structure, it will terminate when it found k words.
*/

import java.util.*;

class Solution {
	// use hashset to store word separator, can be expand in defineWordSeparate() function
	public static HashSet<Character> WordSeparatorHashSet = new HashSet<Character>();

	public void defineWordSeparator() {
		WordSeparatorHashSet.add(',');
		WordSeparatorHashSet.add('.');
		WordSeparatorHashSet.add('!');
		WordSeparatorHashSet.add('?');
		WordSeparatorHashSet.add('(');
		WordSeparatorHashSet.add(')');
		WordSeparatorHashSet.add(':');
		WordSeparatorHashSet.add(';');
		WordSeparatorHashSet.add(' ');
	}

	private void updateFrequencyWord(String word, int frequency, HashMap<Integer, HashSet<String>> frequencyWordSetHashMap) {
		if(frequencyWordSetHashMap.containsKey(frequency)) {
			HashSet<String> wordSet = frequencyWordSetHashMap.get(frequency);
			// add a new word
			if(!wordSet.contains(word)) {
				wordSet.add(word);
			}
		}
		else {
			// initial the frequency's words
			HashSet<String> wordSet = new HashSet<String>();
			wordSet.add(word);
			frequencyWordSetHashMap.put(frequency, wordSet);
		}
	}

	public ArrayList<String> getKFrequencyWords(String document, int k) {
		if(document == null || k < 0) {
			return null;
		}

		defineWordSeparator();

		// HashMap <Word, Frequency>
		// given a word, we can find its frequency in O(1)
		HashMap<String, Integer> wordFrequencyHashMap = new HashMap<String, Integer>();
		// HashMap <Frequency, HashSet<Word>>
		// given a frequency, we can find which words hold that frequency in O(1)
		HashMap<Integer, HashSet<String>> frequencyWordSetHashMap = new HashMap<Integer, HashSet<String>>();

		int maxFrequency = 1;

		StringBuffer strBuffer = new StringBuffer();
		for(int i = 0; i < document.length(); i++) {
			char ch = document.charAt(i);
			// it is a separator
			if(WordSeparatorHashSet.contains(ch)) {
				// find a word
				if(strBuffer.length() > 0) {
					String word = strBuffer.toString();
					if(wordFrequencyHashMap.containsKey(word)) {
						// increase the word's frequency
						int frequency = wordFrequencyHashMap.get(word);
						wordFrequencyHashMap.put(word, frequency + 1);
						// remove the frequency's word
						HashSet<String> wordSet = frequencyWordSetHashMap.get(frequency);
						wordSet.remove(word);
						// update the (frequency + 1)'s word
						updateFrequencyWord(word, frequency + 1, frequencyWordSetHashMap);
						// keep record of max frequency
						if(frequency + 1 > maxFrequency) {
							maxFrequency = frequency + 1;
						}
					}
					else {
						// initial the word's frequency
						wordFrequencyHashMap.put(word, 1);
						// initial the frequency's words
						updateFrequencyWord(word, 1, frequencyWordSetHashMap);
					}
					// clean the buffer
					strBuffer = new StringBuffer(); 
				}
			}
			else {	// it is part of word
				strBuffer.append(ch);
			}
		}

		ArrayList<String> wordList = new ArrayList<String>();
		// get k highst frequency word
		for(int i = maxFrequency; i >= 0 && k > 0; i--) {
			HashSet<String> wordSet = frequencyWordSetHashMap.get(i);
			if(wordSet != null) {
				for(String word : wordSet) {
					wordList.add(word);
					k--;
					if(k <= 0) {	// already find k words
						break;
					}
				}
			}
		}

		return wordList;
	}
}

class Test {
	public static void printList(ArrayList<String> wordList) {
		if(wordList == null) {
			return;
		}
		for(String word : wordList) {
			System.out.println(word);
		}
	}

	public static void main(String[] args) {
		String document = "Evernote is an easy-to-use, free app that helps you remember " + 
						"everything across all of the devices you use. Stay organized, save " + 
						"your ideas and improve productivity. Evernote lets you take notes, " + 
						"capture photos, create to-do lists, record voice reminders--and makes " + 
						"these notes completely searchable, whether you are at home, at work, " + 
						"or on the go.";
		Solution solution = new Solution();
		printList(solution.getKFrequencyWords(document, 2));
	}
}


