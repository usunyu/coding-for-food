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

	public int max() {
		int dec = decimal;
		int length = 0;
		int oneNum = 0;

		while(dec != 0) {
			int bit = dec % 2;
			if(bit == 1)
				oneNum++;
			length++;
			dec /= 2;
		}

		int mask = ((1 << oneNum) - 1);
		mask = (mask << (length - oneNum));

		return mask;
	}

	public int min() {
		int dec = decimal;
		int oneNum = 0;

		while(dec != 0) {
			int bit = dec % 2;
			if(bit == 1)
				oneNum++;
			dec /= 2;
		}

		int mask = ((1 << oneNum) - 1);

		return mask;
	}

	public void setBit(int index) {
		int mask = 1 << index;
		decimal |= mask;
	}

	public int setBit(int index, int num) {
		return num |= (1 << index);
	}

	public int clearBit(int index, int num) {
		return num &= (~(1 << index));
	}

	public boolean getBit(int index) {
		int mask = 1 << index;
		return ((decimal & mask) != 0);
	}

	public boolean getBit(int index, int num) {
		int mask = 1 << index;
		return ((num & mask) != 0);
	}

	public int setBits(int start, int end, int num) {
		int length = start - end;
		int mask = ((1 << length) - 1);
		mask <<= end;
		return num | mask;
	}

	public int nextSmallest() {
		int dec = decimal;
		int index = 0;
		int oneNum = 0;
		while(!getBit(index, dec))
			index++;
		while(getBit(index, dec)) {
			dec = clearBit(index, dec);
			oneNum++;
			index++;
		}
		dec = setBit(index, dec);
		dec = setBits(oneNum - 1, 0, dec);
		return dec;
	}

	public int nextLargest() {
		int dec = decimal;
		int index1 = 0;
		int index2 = 0;
		while((dec & 1) == 1) {
			index1++;
			dec >>= 1;
		}
		while((dec & 1) == 0 && dec != 0) {
			index2++;
			dec >>= 1;
		}
		dec = decimal;
		dec &= ~(1 << (index1 + index2));
		dec |= (1 << (index1 + index2 - 1));
		int mask = (1 << index1) - 1;
		mask <<= (index2 - 1);
		dec &= ~((1 << (index1 + index2 - 1)) - 1);
		dec |= mask;
		return dec;
	}
}

class Q5_3App {
	public static void main(String[] args) {
		BitObject ob = new BitObject(10115);
		System.out.print("Original:\t");
		ob.display();
		System.out.print("\t:\t");
		ob.displayBinary();
		System.out.println();

		BitObject smallest = new BitObject(ob.nextSmallest());
		System.out.print("Next Smallest:\t");
		smallest.display();
		System.out.print("\t:\t");
		smallest.displayBinary();
		System.out.println();

		BitObject largest = new BitObject(ob.nextLargest());
		System.out.print("Next Largest:\t");
		largest.display();
		System.out.print("\t:\t");
		largest.displayBinary();
		System.out.println();

		// BitObject max = new BitObject(ob.max());
		// System.out.print("MAX:\t\t");
		// max.display();
		// System.out.print("\t:\t");
		// max.displayBinary();
		// System.out.println();

		// BitObject min = new BitObject(ob.min());
		// System.out.print("MAX:\t\t");
		// min.display();
		// System.out.print("\t:\t");
		// min.displayBinary();
		// System.out.println();
	}
}
