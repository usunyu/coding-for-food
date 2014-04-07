public class MergeBU {
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
 
 	public static void sort(Comparable[] a) {
 		int N = a.length;
 		Comparable[] aux = new Comparable[N];
 		for (int sz = 1; sz < N; sz = sz+sz)
 			for (int lo = 0; lo < N-sz; lo += sz+sz)
 				merge(a, aux, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
 	}
 }