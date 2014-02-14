class Q9_5App {
	public static String shift(String str, int index) {
		StringBuffer stb = new StringBuffer();
		// append the original until index
		for(int i = 0; i < index; i++) {
			char ch = str.charAt(i);
			stb.append(ch);
		}
		// shift
		for(int i = index + 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			stb.append(ch);
		}
		char end = str.charAt(index);
		stb.append(end);
		return stb.toString();
	}

	public static void permutations(String str, int index) {
		StringBuffer sBuffer;
		if(index == str.length() - 1) {
			System.out.println(str);
			return;
		}
		for(int i = index; i < str.length(); i++) {
			permutations(str, index + 1);
			str = shift(str, index);
		}
	}

	public static void permutations(String str) {
		permutations(str, 0);
	}

	public static void main(String[] args) {
		String str = "ABCD";
		permutations(str);
	}
}