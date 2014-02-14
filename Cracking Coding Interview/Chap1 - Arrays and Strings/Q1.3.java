import java.util.*;

class Q1_3App {
	// test if str1 is permutation of str2
	public static boolean permutation(String str1, String str2) {
		int length = str1.length();
		if(length != str2.length())
			return false;
		int array1[] = new int[256];
		int array2[] = new int[256];
		for(int i = 0; i < length; i++) {
			int c1 = str1.charAt(i);
			int c2 = str2.charAt(i);
			array1[c1]++;
			array2[c2]++;
		}
		// check
		for(int i = 0; i < 256; i++)
			if(array1[i] != array2[i])
				return false;

		return true;
	}

	public static void main(String[] args) {
		String str1 = "ABCDEFG";
		String str2 = "CGBEDFD";
		System.out.println(str1);
		System.out.println(str2);
		if(permutation(str1, str2))
			System.out.println("True");
		else
			System.out.println("False");
	}
}