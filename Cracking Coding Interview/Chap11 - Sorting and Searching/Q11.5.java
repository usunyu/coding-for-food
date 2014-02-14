// time complexity : O(logN)
// space complexity : O(1)

class Q11_5App {
	public static int find(String[] strArr, String str, int start, int end) {
		while(strArr[start].equals(""))
			start++;
		while(strArr[end].equals(""))
			end--;
		if(end < start)
			return -1;

		if(strArr[start].compareTo(str) > 0 || strArr[end].compareTo(str) < 0)
			return -1;

		if(strArr[start].equals(str))
			return start;
		if(strArr[end].equals(str))
			return end;

		int mid = (start + end) / 2;
		int result = -1;
		result = find(strArr, str, start + 1, mid);
		if(result == -1)
			result = find(strArr, str, mid + 1, end - 1);
		return result;
	}

	public static void main(String[] args) {
		String[] strArr = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		int index = find(strArr, "ball", 0, strArr.length - 1);
		System.out.println(index);
	}
}