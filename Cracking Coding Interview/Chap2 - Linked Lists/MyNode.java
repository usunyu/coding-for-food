public class MyNode {
	public char ch;
	public int val;
	public MyNode next;

	public MyNode(char ch) {
		this.ch = ch;
	}

	public MyNode(int val) {
		this.val = val;
	}

	public void displayVal() {
		System.out.println(val);
	}

	public String toString() {
		return "[" + ch + ", " + val + "]";
	}
}
