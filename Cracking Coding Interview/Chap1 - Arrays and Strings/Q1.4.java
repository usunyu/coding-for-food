import java.util.*;

class Q1_4App {
	public static String replaceSpace(String str) {
		char[] charArray = str.toCharArray();
		// first count space number
		boolean spaceCont = false;
		int spaceNum = 0;
		int charNum = 0;
		for(int i = 0; i < charArray.length; i++) {
			if(charArray[i] != ' ') {
				charNum++;
				if(spaceCont) {
					spaceNum++;
					spaceCont = false;
				}
			}
			else {
				spaceCont = true;
			}
		}
		
		int endIndex = charNum + spaceNum * 3 - 1;
		int j = endIndex;
		spaceCont = false;
		for(int i = endIndex; i >= 0; i--) {
			if(charArray[i] != ' ') {
				if(spaceCont) {
					charArray[j--] = '0';
					charArray[j--] = '2';
					charArray[j--] = '%';
					spaceCont = false;
				}
				charArray[j--] = charArray[i];
			}
			else {
				spaceCont = true;
			}
		}
		str = new String(charArray, 0, endIndex + 1);
		return str;
	}

	public static void main(String[] args) {
		String testStr = "AB   CD   EF                             ";
		System.out.println(testStr);
		testStr = replaceSpace(testStr);
		System.out.println(testStr);
	}
}