/*
Write an efficient C program to find the sum of contiguous subarray within a one-dimensional array of 
numbers which has the largest sum.

http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
*/

#include<stdio.h>
 
int max(int x, int y)
{ return (y > x)? y : x; }
 
int maxSubArraySum(int a[], int size)
{
   int max_so_far = a[0], i;
   int curr_max = a[0];
 
   for (i = 1; i < size; i++)
   {
        curr_max = max(a[i], curr_max+a[i]);
        max_so_far = max(max_so_far, curr_max);
   }
   return max_so_far;
}
 
/* Driver program to test maxSubArraySum */
int main()
{
   int a[] =  {-2, -3, 4, -1, -2, 1, 5, -3};
   int n = sizeof(a)/sizeof(a[0]);
   int max_sum = maxSubArraySum(a, n);
   printf("Maximum contiguous sum is %d\n", max_sum);
   return 0;
}