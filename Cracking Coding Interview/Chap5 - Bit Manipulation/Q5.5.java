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

	public int requireConvert(int num) {
		int require = 0;
		int n1 = decimal;
		int n2 = num;
		int c1, c2;
		while(n1 != 0 && n2 != 0) {
			c1 = n1 & 1;
			c2 = n2 & 1;
			if(c1 != c2)
				require++;
			n1 >>= 1;
			n2 >>= 1;
		}
		while(n1 != 0) {
			c1 = n1 & 1;
			if(c1 == 1)
				require++;
			n1 >>= 1;
		}
		while(n2 != 0) {
			c2 = n2 & 1;
			if(c2 == 1)
				require++;
			n2 >>= 1;
		}
		return require;
	}
}

class Q5_5App {
	public static void main(String[] args) {
		BitObject n1 = new BitObject(31);
		BitObject n2 = new BitObject(14);

		n1.display();
		System.out.print("\t:\t");
		n1.displayBinary();
		System.out.println();

		n2.display();
		System.out.print("\t:\t");
		n2.displayBinary();
		System.out.println();

		System.out.print("Require convert: ");
		System.out.println(n1.requireConvert(n2.decimal));
	}
}