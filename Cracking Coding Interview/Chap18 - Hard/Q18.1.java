class Main {
	public static int add(int a, int b) {
		if(b == 0) return a;
		int sum = a ^ b;			// add without carrying
		int carry = (a & b) << 1;	// carry, but don't add
		return add(sum, carry);		// recurse
	}

	public static void main(String[] args) {
		System.out.println(add(759, 674));
	}
}