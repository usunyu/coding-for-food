import java.io.*;

class AnagramApp {
	static int size;
	static char[] arrChar = new char[100];

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static void doAnagram(int newSize) {
		if(newSize == 1)
			return;
		for(int i = 0; i < newSize; i++) {
			doAnagram(newSize - 1);
			if(newSize == 2)			// if innermost,
				displayWord();			// display it
			rotate(newSize);
		}
	}

	public static void rotate(int newSize) {
		int position = size - newSize;
		char temp = arrChar[position];
		for(int i = position + 1; i < size; i++) {
			arrChar[i - 1] = arrChar[i];
		}
		arrChar[size - 1] = temp;
	}

	public static void displayWord() {
		for(int j=0; j<size; j++)
			System.out.print( arrChar[j] );
		System.out.println("");
	}

	public static void main(String[] args) throws IOException  {
		System.out.print("Enter a word: ");
		System.out.flush();
		String input = getString();
		size = input.length();
		for(int j = 0; j < size; j++)
			arrChar[j] = input.charAt(j);
		doAnagram(size);
	}
}