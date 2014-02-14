class Q7_4App {
	public static int negate(int a) {
		int negate = 0;
		int d = a < 0 ? 1 : -1;
		while(a != 0) {
			negate += d;
			a += d;
		}
		return negate;
	}

	public static int abs(int a) {
		if(a < 0)
			a = negate(a);
		return a;
	}

	public static int multiply(int a, int b) {
		if(abs(a) < abs(b))	// different with answer
			return multiply(b, a);

		int result = 0;
		for(int i = 0; i < abs(b); i++)
			result += a;

		if(b < 0)
			result = negate(result);
		return result;
	}

	public static int divide(int a, int b) {
		int absA = abs(a);
		int absB = abs(b);

		if(absA < absB)
			return 0;

		if(b == 0)
			throw new java.lang.ArithmeticException("Error");

		int result = 0;
		int i = absB;
		while(i < absA) {
			i+= absB;
			result++;
		}
		if((a < 0 && b > 0) || (a > 0 && b < 0))
			result = negate(result);
		return result;
	}

	public static int substract(int a, int b) {
		return a + negate(b);
	}

	public static void main(String[] args) {
		int a = 0, b = -4;
		System.out.println(a + " X " + b + " = " + multiply(a, b));
		System.out.println(a + " - " + b + " = " + substract(a, b));
		System.out.println(a + " / " + b + " = " + divide(a, b));
	}
}