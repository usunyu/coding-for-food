#include <iostream>

using namespace std;

// use Median of Medians algorithm to guarantee O(n)

void swap(int * a, int b, int c)
{
	int temp = a[b];
	a[b] = a[c];
	a[c] = temp;
}

int randomized_partition(int * a, int p, int r)
{
	int value = a[r];
	int position = p;
	for(int i = p; i < r; i++)
	{
		if(a[i] < value)
		{
			swap(a, i, position);
			position++;
		}
	}
	swap(a, position, r);
	return position;
}

int randomized_select(int * a, int p, int r, int i)
{
	if(p == r)
		return a[p];
	int q = randomized_partition(a, p, r);
	int k = q - p + 1; // the size of the left partition
	if(k == i)
		return a[q];
	else if(k > i)
		return randomized_select(a, p, q - 1, i);
	else
		return randomized_select(a, q + 1, r, i - k);
}

int main()
{
	int a[10] = {7, 2, 8, 4, 1, 3, 4, 5, 9, 4};
	cout << randomized_select(a, 0, 9, 10) << endl;
}