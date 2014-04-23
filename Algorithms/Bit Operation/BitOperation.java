/*
Implement +,-,*,/ with bit operation
*/

class BitOperation {
	public static int add(int a, int b) {
		if(b == 0) return a;
		int carry = ((a & b) << 1);
		int add = a ^ b;
		return add(add, carry);
	}

	// Get arithmetic negative number
	// The two's complement operation is the additive inverse operation
	public static int negative(int num){
		return add(~num, 1);
	}

	public static int minus(int a, int b) {
		return add(a, negative(b));
	}

	public static int multiply(int a, int b) {
		int absA = a < 0 ? negative(a) : a;
		int absB = b < 0 ? negative(b) : b;
		int res = 0;
		while(absA > 0) {
			if((absA & 1) > 0)
				res = add(res, absB);
			absA >>= 1;
			absB <<= 1;
		}
		if(a > 0 && b > 0 || a < 0 && b < 0)
			return res;
		else
			return negative(res);
	}

	// minus y^(2^31), y^(2^30),...y^2, y^1, if possible
	// binary division by shift and subtract, actually the reverse of multiplication
	// http://courses.cs.vt.edu/~cs1104/BuildingBlocks/divide.030.html
	public static int divide(int a, int b) {
		int absA = a < 0 ? negative(a) : a;
		int absB = b < 0 ? negative(b) : b;
		int res = 0, i = 31;
		while (i >= 0) {
			if ((absA >> i) >= absB) {
				res = add(res, (1 << i));
				absA = minus(absA, (absB << i));
			} else
				i--;
		}
		if(a > 0 && b > 0 || a < 0 && b < 0)
			return res;
		else
			return negative(res);
	}
}

class Main {
	public static void main(String[] args) {
		int a = -18, b = 3;
		System.out.println(a + " + " + b + " = " + BitOperation.add(a, b));
		System.out.println(a + " - " + b + " = " + BitOperation.minus(a, b));
		System.out.println(a + " * " + b + " = " + BitOperation.multiply(a, b));
		System.out.println(a + " / " + b + " = " + BitOperation.divide(a, b));
	}
}
