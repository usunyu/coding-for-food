import java.util.*;

class BitObject {
	int decimal;

	public BitObject(int decimal) {
		this.decimal = decimal;
	}

	public void display() {
		System.out.print(decimal + " ");
	}

	public void displayBinary() {
		Stack<Integer> stack = new Stack<Integer>();

		int dec = decimal;
		while(dec != 0) {
			int bit = dec % 2;
			stack.push(bit);
			dec /= 2;
		}

		while(!stack.isEmpty())
			System.out.print(stack.pop());
		System.out.print(" ");
	}

	public void clear(int start, int end) {
		if(start < end)
			return;
		int length = start - end + 1;
		int mask = ~(((1 << length) - 1) << end);
		decimal &= mask;
	}

	public void insert(int m, int start, int end) {
		clear(start, end);
		int mask = m << end;
		decimal |= mask;
	}
}

class Q5_1App {
	public static void main(String[] args) {
		BitObject N = new BitObject(1024);
		N.display();
		System.out.print("\t:\t");
		N.displayBinary();
		System.out.println();

		BitObject M = new BitObject(19);
		M.display();
		System.out.print("\t:\t");
		M.displayBinary();
		System.out.println();

		N.insert(M.decimal, 6, 2);
		System.out.println("After insert:");
		N.display();
		System.out.print("\t:\t");
		N.displayBinary();
		System.out.println();
	}
}
