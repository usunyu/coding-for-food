class Q1_2App {
	public static String reverse(String str) {
		StringBuffer sb = new StringBuffer(str);
		for(int i = 0, j = sb.length() - 1; i < j; i++, j--) {
			char temp = sb.charAt(i);
			sb.setCharAt(i, sb.charAt(j));
			sb.setCharAt(j, temp);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String testStr = "ABCDEFG";
		System.out.println(testStr);
		testStr = reverse(testStr);
		System.out.println(testStr);
	}
}