import java.util.*;

class Q1_5App {
	public static String compression(String str) {
		if(str == null || str.isEmpty())
			return str;
		StringBuffer sb = new StringBuffer();
		int cont = 1;
		char lastCh = str.charAt(0);
		sb.append(lastCh);
		boolean compress = true;
		for(int i = 1; i < str.length(); i++) {
			if(lastCh != str.charAt(i)) {
				sb.append(cont);
				cont = 0;
				lastCh = str.charAt(i);
				sb.append(lastCh);
			}
			if(sb.length() > str.length()) {
				compress = false;
				break;
			}
			cont++;
		}
		sb.append(cont);
		if(compress)
			str = sb.toString();
		return str;
	}

	public static void main(String[] args) {
		String testStr = "aabcccccaaa";
		System.out.println(testStr);
		testStr = compression(testStr);
		System.out.println(testStr);
	}
}