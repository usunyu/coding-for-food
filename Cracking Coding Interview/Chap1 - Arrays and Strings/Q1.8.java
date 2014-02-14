import java.util.*;

class Q1_8App {

	public static boolean isSubstring(String s1, String s2) {
		if(s1.length() < s2.length())
			return false;
		boolean isSub = false;
		int j = 0;
		for(int i = 0; i < s1.length(); i++) {
			if(s1.charAt(i) == s2.charAt(j))
				j++;
			else
				j = 0;
			if(j == s2.length()) {
				isSub = true;
				break;
			}
		}
		return isSub;
	}

	public static boolean isRotation(String s1, String s2) {
		if(s1.length() != s2.length())
			return false;
		String s1s1 = s1 + s1;
		if(isSubstring(s1s1, s2))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		String testStr1 = "waterbottle";
		String testStr2 = "erbottlewat";
		System.out.println(testStr1);
		System.out.println(testStr2);
		if(isRotation(testStr1, testStr2))
			System.out.println("Is Rotation.");
		else
			System.out.println("Not rotation.");
	}
}