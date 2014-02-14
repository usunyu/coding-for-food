import java.util.*;

class Q9_6App {
	public static HashSet<Integer> dupSet = new HashSet<Integer>();

	public static int hashCode(ArrayList<Character> list) {
		int i = 0;
		for(char ch : list) {
			int add = (ch == '(' ? 1 : 0);
			i = ((i + add) << 1);
		}
		return i;
	}

	public static void printList(ArrayList<ArrayList<Character>> pList) {
		for(ArrayList<Character> subList : pList) {
			for(int i = 0; i < subList.size(); i++)
				System.out.print(subList.get(i));
			System.out.println();
		}
		System.out.println();
	}

	public static ArrayList<ArrayList<Character>> validParenthese(int n) {
		ArrayList<ArrayList<Character>> pList;
		if(n == 1) {
			pList = new ArrayList<ArrayList<Character>>();
			ArrayList<Character> subList = new ArrayList<Character>();
			subList.add('(');
			subList.add(')');
			pList.add(subList);
			return pList;
		}
		pList = validParenthese(n - 1);
		ArrayList<ArrayList<Character>> tempList = new ArrayList<ArrayList<Character>>();
		for(ArrayList<Character> subList : pList) {
			for(int i = 0; i < subList.size(); i++) {
				ArrayList<Character> newList = new ArrayList<Character>(subList);
				newList.add(i, ')');
				newList.add(i, '(');
				int hashCode = hashCode(newList);
				if(!dupSet.contains(hashCode)) {
					tempList.add(newList);
					dupSet.add(hashCode);
				}
			}
		}
		return tempList;
	}

	public static void main(String[] args) {
		int n = 4;
		ArrayList<ArrayList<Character>> pList = validParenthese(n);
		printList(pList);
	}
}