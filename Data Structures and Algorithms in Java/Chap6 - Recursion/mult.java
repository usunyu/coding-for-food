import java.io.*;

class MultApp {
	public static void main(String[] args) throws IOException {
		System.out.print("Enter a number: ");
		System.out.flush();
		int n1 = getInt();
		System.out.print("Enter another number: ");
		System.out.flush();
		int n2 = getInt();
		System.out.println("Result: " + mult(n1, n2));
	}

	public static int mult(int n1, int n2) {
		int res = 0;
		for(int i = 0; i < n2; i++) {
			res += n1;
		}
		return res;
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

}