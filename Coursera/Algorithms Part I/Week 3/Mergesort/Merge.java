public class Merge {
	static final int CUTOFF = 7;

	/***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

	private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
 		assert isSorted(a, mid+1, hi); // precondition: a[mid+1..hi] sorted

		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];	// copy
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {	// merge
			if (i > mid) 					a[k] = aux[j++];
			else if (j > hi) 				a[k] = aux[i++];
			else if (less(aux[j], aux[i])) 	a[k] = aux[j++];
			else 							a[k] = aux[i++];
		}

		assert isSorted(a, lo, hi); // postcondition: a[lo..hi] sorted
	}

	private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		// if (hi <= lo) return;
		if (hi <= lo + CUTOFF - 1) {	// improve 1
			Insertion.sort(a, lo, hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid+1, hi);
		if (!less(a[mid+1], a[mid])) return;	// improve 2
		merge(a, aux, lo, mid, hi);
	}

	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	public static void main(String[] args) {
		Integer[] arr = {6, 3, 1, 3, 4, 2, 3, 5, 1};
		sort(arr);
		System.out.println(java.util.Arrays.toString(arr));
	}
}