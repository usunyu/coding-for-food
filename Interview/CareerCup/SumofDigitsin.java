/**
 * Given a numebr N in [1...2000], get the sum of digits in N!
 * e.g., N=4, N!=24, sum = 2+4 = 6
 * 
 * Time: O(N^2); Space: O(N)
 */
import java.util.*;

class Main {
	public static long getSum(int n) {
		if(n == 0) return 0;
		ArrayList<Integer> digits = new ArrayList<Integer>();
		digits.add(1);
		for(int i = 1; i < n; i++) {
			int carrier = 0;
			for(int j = 0; j < digits.size(); j++) {
				int val = digits.get(j);
				int mul = val * i + carrier;
				val = mul % 10;
				carrier = mul / 10;
				digits.set(j, val);
			}
			if(carrier != 0) {
				int temp = carrier, bits = 0;
				while(temp != 0) {
					bits++;
					temp /= 10;
				}
				for(int j = 0; j < bits; j++) {
					digits.add(carrier % 10);
					carrier /= 10;
				}
			}
		}

		long sum = 0;
		for(int d : digits) {
			sum += d;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getSum(2000));
	}
}
