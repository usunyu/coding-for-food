class Main {
	public static int trailZeros(int n) {
		if(n < 0) return -1;
		int count = 0;
		for(int i = 5; n / i > 0; i *= 5) {
			count += n / i;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(trailZeros(99));
	}
}