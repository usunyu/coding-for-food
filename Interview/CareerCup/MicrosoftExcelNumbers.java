/*

Microsoft Excel numbers cells as 1...26 and after that AA, AB.... AAA, AAB...ZZZ and so on. 
Given a number, convert it to that format and vice versa.

http://www.careercup.com/question?id=6139456847347712
*/

class Main {
	public static String numberToString(int num) {
		StringBuilder sb = new StringBuilder();
		while(num > 0) {
			int val = (num - 1) % 26;
			char ch = (char)('A' + val);
			sb.insert(0, ch);
			num = (num - 1) / 26;
		}
		return sb.toString();
	}

	public static int stringToNumber(String str) {
		int base = 1, num = 0;
		for(int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			int val = ch - 'A' + 1;
			num += val * base;
			base *= 26;
		}
		return num;
	}

	public static void main(String[] args) {
		for(int i = 1; i <= 100; i++) {
			String str = numberToString(i);
			System.out.println(str + " should be : \t" + i + " - " + stringToNumber(str));
		}
	}
}