/*
Given a set of characters and a positive integer k, print all possible strings of length k that can be formed from the given set.

Examples:

Input: 
set[] = {'a', 'b'}, k = 3

Output:
aaa
aab
aba
abb
baa
bab
bba
bbb


Input: 
set[] = {'a', 'b', 'c', 'd'}, k = 1
Output:
a
b
c
d

http://www.geeksforgeeks.org/print-all-combinations-of-given-length/
*/

class Main {
	private static void printAllStrings(char[] set, StringBuilder path, int k) {
		if(k == 0) {
			System.out.println(path.toString());
			return;
		}
		for(char ch : set) {
			StringBuilder tmp = new StringBuilder(path);
			tmp.append(ch);
			printAllStrings(set, tmp, k - 1);
		}
	}

	public static void printAllStrings(char[] set, int k) {
		printAllStrings(set, new StringBuilder(), k);
	}

	public static void main(String[] args) {
		char[] set = {'a', 'b', 'c', 'd'};
		int k = 1;
		printAllStrings(set, k);
	}
}