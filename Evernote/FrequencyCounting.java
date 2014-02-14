/*
Frequency Counting of Words / Top N words in a document.
Given N terms, your task is to find the k most frequent terms from given N terms.

Input format
First line of input contains N, denoting the number of terms to add.
In each of the next N lines, each contains a term.
Next line contains k, most frequent terms.

Output format
Print the k most frequent terms in descending order of their frequency. 
If two terms have same frequency print them in lexicographical order.

Sample input
14
Fee
Fi
Fo
Fum
Fee
Fo
Fee
Fee
Fo
Fi
Fi
Fo
Fum
Fee
3

Sample output
Fee
Fo
Fi

Constraint
0 < N < 300000
0 < term length < 25.
*/

import java.io.*;
import java.util.*;

class DataInQueue {
	String word;
	int frequency;

	public DataInQueue(String w, int f) {
		word = w;
		frequency = f;
	}
}

class Solution {
	static HashMap<String, DataInQueue> WordDataMap;
	static PriorityQueue<DataInQueue> WordDataQueue;

	public static void initial() {
		WordDataMap = new HashMap<String, DataInQueue>();
		WordDataQueue = new PriorityQueue<DataInQueue>(30, new Comparator<DataInQueue>() {
			public int compare(DataInQueue d1, DataInQueue d2) {
				if(d1.frequency != d2.frequency) {
					return d2.frequency - d1.frequency;
				}
				else {
					return d1.word.compareTo(d2.word);
				}
			}
		});
	}

	public static void add(String word) {
		DataInQueue diq;
		if(WordDataMap.containsKey(word)) {
			diq = WordDataMap.get(word);
			WordDataQueue.remove(diq);
			diq.frequency++;
		}
		else {
			diq = new DataInQueue(word, 1);
			WordDataMap.put(word, diq);
		}
		WordDataQueue.offer(diq);
	}

	public static void show(int k) {
		while(k > 0) {
			if(!WordDataQueue.isEmpty()) {
				DataInQueue diq = WordDataQueue.poll();
				System.out.println(diq.word);
			}
			k--;
		}
	}

	public static void main(String[] args) {
		try{
			initial();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// System.out.print("Input n: ");
            int n = Integer.parseInt(br.readLine());
            while(n > 0) {
            	String word = br.readLine();
            	add(word);
            	n--;
            }
            // System.out.print("Input k: ");
            int k = Integer.parseInt(br.readLine());
            show(k);
		}
		catch(Exception e) {
			System.err.println("Exception!");
		}
	}
}

