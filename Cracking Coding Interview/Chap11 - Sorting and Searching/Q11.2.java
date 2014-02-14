import java.util.*;

class Q11_2App {
	public static String sortChars(String s) {
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}

	public static void sort(String[] strArr) {
		Hashtable<String, LinkedList<String>> hash = new Hashtable<String, LinkedList<String>>();
		for(String str : strArr) {
			String sortStr = sortChars(str);
			if(!hash.containsKey(sortStr)) {
				hash.put(sortStr, new LinkedList<String>());
			}
			LinkedList<String> list = hash.get(sortStr);
			list.add(str);
		}

		int i = 0;
		for(String str : hash.keySet()) {
			LinkedList<String> list = hash.get(str);
			for(String s : list) {
				strArr[i++] = s;
			}
		}
	}

	public static void main(String[] args) {
		String[] strArr = new String[10];
		sort(strArr);
	}
}