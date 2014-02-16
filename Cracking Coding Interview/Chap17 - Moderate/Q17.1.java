class Main {
	public static void main(String[] args) {
		int i1 = 10, i2 = 5;
		System.out.println("i1 = " + i1 + ", i2 = " + i2);
		i2 += i1;
		i1 = i2 - i1;
		i2 -= i1;
		System.out.println("i1 = " + i1 + ", i2 = " + i2);
	}
}