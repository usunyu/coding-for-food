import java.util.*;

class BitObject {
	int decimal;

	public BitObject(int decimal) {
		this.decimal = decimal;
	}

	public void display() {
		displayDecimal();
		System.out.print("\t:\t");
		displayBinary();
		System.out.println();
	}

	public void displayDecimal() {
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

	public void swap() {
		int temp = decimal;
		int mask = 1;
		while(temp > 0) {
			temp >>= 1;
			int c1 = 1 & temp;
			int c2 = mask & decimal;

			if(c1 != 0)
				decimal |= mask;
			else
				decimal &= ~mask;

			mask <<= 1;

			if(c2 != 0)
				decimal |= mask;
			else
				decimal &= ~mask;

			temp >>= 1;
			mask <<= 1;
		}
	}
}

class Q5_6App {
	public static void main(String[] args) {
		BitObject bo = new BitObject(6);
		bo.display();
		System.out.println("Swap:");
		bo.swap();
		bo.display();
	}
}
