public class iteration
{
	public static int iterBinarySearch(int[] array, int lower, int upper, int target)
	{
		final int NOT_IN_ARRAY = -1;
		final int ARRAY_UNORDERED = -2;
		final int LIMITS_REVERSED = -3;

		int center, range;

		if(lower > upper)
			return LIMITS_REVERSED;

		while(true)
		{
			range = upper - lower;

			if(range == 0 && array[lower] != target)
				return NOT_IN_ARRAY;

			if(array[lower] > array[upper])
				return ARRAY_UNORDERED;

			center = lower + range / 2;

			if(array[center] == target)
				return center;
			else if(array[center] < target)
				lower = center + 1;
			else
				upper = center - 1;

		}
	}

	public static void main(String[] args)
	{
		System.out.println("Iteration.");

		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int result = iterBinarySearch(array, 0, 9, 5);
		System.out.println("Result is located at " + result);
	}
}