import java.io.*;

class PowerApp {
	public static int recPower(int base, int exp) {
		if(exp == 0)
			return 1;
		else {
			return base * recPower(base, exp - 1);
		}
	}

	public static int power(int base, int exp) {
		int res = 1;
		for(int i = 0; i < exp; i++) {
			res *= base;
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println(recPower(2, 10));
	}
}