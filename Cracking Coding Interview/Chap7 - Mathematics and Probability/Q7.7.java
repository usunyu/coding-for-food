import java.util.*;

class Q7_7App {
	public static int getKthMagicNumber(int k) {
		if(k < 0)
			return 0;

		LinkedList<Integer> Q3 = new LinkedList<Integer>();
		LinkedList<Integer> Q5 = new LinkedList<Integer>();
		LinkedList<Integer> Q7 = new LinkedList<Integer>();
		Q3.add(1);

		int val = 0;
		for(int i = 0; i < k; i++) {
			int v3 = Q3.size() > 0 ? Q3.peek() : Integer.MAX_VALUE;
			int v5 = Q5.size() > 0 ? Q5.peek() : Integer.MAX_VALUE;
			int v7 = Q7.size() > 0 ? Q7.peek() : Integer.MAX_VALUE;

			val = Math.min(v3, Math.min(v5, v7));

			if(val == v3) {
				Q3.poll();
				Q3.add(val * 3);
				Q5.add(val * 5);
				Q7.add(val * 7);
			}
			else if(val == v5) {
				Q5.poll();
				Q5.add(val * 5);
				Q7.add(val * 7);
			}
			else {
				Q7.poll();
				Q7.add(val * 7);
			}
		}

		return val;
	}

	public static void main(String[] args) {
		System.out.println(getKthMagicNumber(13));
	}
}