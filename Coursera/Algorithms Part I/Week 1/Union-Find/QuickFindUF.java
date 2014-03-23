public class QuickFindUF {
	private int[] id;

	public QuickFindUF(int N) {
		id = new int[N];
		for(int i = 0; i < N; i++)
			id[i] = i;
	}

	public boolean connected(int p, int q) {
		return id[p] == id[q];
	}

	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for(int i = 0; i < id.length; i++) {
			if(id[i] == pid) id[i] = qid;
		}
	}

	public void printIdArray() {
		System.out.print("[");
		for(int i = 0; i < id.length; i++) {
			if(i < id.length - 1) System.out.print(id[i] + "|");
			else System.out.println(id[i] + "]");
		}
		System.out.println();
	}
}