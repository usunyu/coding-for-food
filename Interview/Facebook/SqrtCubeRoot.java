/*
Newton method

Newton's Method, faster and more precise (see test result) than binary search solution 
The basic idea is that if x is an overestimate of sqrt(num), num/x will be an underestimate
So, the average of x and num/x will reasonably provide a better approximation
*/

class Main {
	static double small = 0.00000001;

	public static double square(double x) {
		return x * x;
	}

	public static double cube(double x) {
		return x * x * x;
	}

	public static double sqrtRoot(double n) {
		if(n == 0) return 0;
		double x0 = 1.0;
		while(true){
			double x1 = (x0 + n / x0) / 2;
			if (Math.abs(x1 - x0) < small)
				return x0;
			x0 = x1;
		}
	}

	public static double cubeRoot(double n) {
		double absN = Math.abs(n);
		double x0 = 1.0;
		while(true) {
			double x1 = (x0 + n / x0 / x0) / 2;
			if (Math.abs(x1 - x0) < small)
				return x0;
			x0 = x1;
		}
	}

	public static void main(String[] args) {
		double seed = 8.33;
		double square = square(seed);
		System.out.println(square + ": " + sqrtRoot(square));
		double cube = cube(seed);
		System.out.println(cube + ": " + cubeRoot(cube));
	}
}