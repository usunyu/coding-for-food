public class Quick {
	private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

	// partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) { 
            // find item on lo to swap
            while (less(a[++i], a[lo]))
                if (i == hi) break;

            // find item on hi to swap
            while (less(a[lo], a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel

            // check if pointers cross
            if (i >= j) break;

            exch(a, i, j);
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] a, int lo, int hi) { 
        if (hi <= lo) return;

        /* practical improvements: Insertion sort small subarrays
        if (hi <= lo + CUTOFF - 1) {
        	Insertion.sort(a, lo, hi);
        	return;
        }
        */

        /* practical improvements: Median of sample
        int m = medianOf3(a, lo, lo + (hi - lo)/2, hi);
 		swap(a, lo, m);
 		*/

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        // assert isSorted(a, lo, hi);
    }
}