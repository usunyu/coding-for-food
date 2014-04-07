import java.util.Comparator;

public class Insertion {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j-1]))
                    exch(a, j, j-1);
                else break;
    }

    public static void sort(Comparable[] a, int i, int j) {
        for (; i <= j; i++)
            for (int k = i; k > 0; k--)
                if (less(a[k], a[k-1]))
                    exch(a, k, k-1);
                else break;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i; j > 0 && less(comparator, a[j], a[j-1]); j--)
                exch(a, j, j-1);
    } 
    
    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }
    
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i]; a[i] = a[j]; a[j] = swap;
    }
}