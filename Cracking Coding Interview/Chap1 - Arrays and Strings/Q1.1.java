import java.util.*;

class Q1_1App {
	static String testStr = "ABCDEFGD";
	public static void main(String[] args) {
		System.out.println(testStr);
		// using Hash Set
		HashSet<Character> set = new HashSet<Character>();
		boolean unique = true;
		for(int i = 0; i < testStr.length(); i++) {
			char ch = testStr.charAt(i);
			if(!set.contains(ch))
				set.add(ch);
			else {
				unique = false;
				break;
			}
		}
		if(unique)
			System.out.println("This is a string has all unique characters.");
		else
			System.out.println("This is not a string has all unique characters.");

		// not using data structures
		unique = true;
		for(int i = 0; i < testStr.length(); i++) {
			for(int j = i + 1; j < testStr.length(); j++) {
				if(testStr.charAt(i) == testStr.charAt(j)) {
					unique = false;
					break;
				}
			}
			if(!unique)
				break;
		}
		if(unique)
			System.out.println("This is a string has all unique characters.");
		else
			System.out.println("This is not a string has all unique characters.");
	}
}