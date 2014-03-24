import java.util.Arrays;

public class ThreeSumFast {
	// My 3Sum algorithm
	public static int count(int[] a) {
		int N = a.length;
		if(N < 3) return 0;
		int count = 0;
        Arrays.sort(a);
        for(int i = 0; i < N - 2; i++) {
            // remove duplicate
            if(i > 0 && a[i] == a[i - 1]) continue;
            int target = -a[i];
            int start = i + 1, end = N - 1;
            while(start < end) {
                int sum = a[start] + a[end];
                if(sum > target) {
                    end--;
                }
                else if(sum < target) {
                    start++;
                }
                else {
                    count++;
                    // remove duplicate
                    do { start++; }
                    while(start < end && a[start] == a[start - 1]);
                }
            }
        }
        return count;
	}
}