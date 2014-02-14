/*
	Task: Find the missing term in an Arithmetic Progression.

	An Arithmetic Progression is defined as one in which there is a constant difference between the consecutive terms of a given series of numbers. 
	You are provided with consecutive elements of an Arithmetic Progression. There is however one hitch: Exactly one term from the original series 
	is missing from the set of numbers which have been given to you. The rest of the given series is the same as the original AP.
	Find the missing term.

	Input Format
	The first line contains an Integer N, which is the number of terms which will be provided as input.
	This is followed by N consecutive Integers, with a space between each pair of integers. All of these are on one line, and they are in AP 
	(other than the point where an integer is missing).

	Output Format
	One Number which is the missing integer from the series.
 
	Sample Input
	5
	1 3 5 9 11  
 
	Sample Output
	7
 
	Explanation
	You are provided with 5 integers. As you can can observe, they have been picked from a series, in which the starting term is 1 and the 
	common difference is 2. The only abberration, i.e. the missing term (7), occurs between 5 and 9. This is the missing element which you need to find.
 
	Constraints
	3 <= N <= 2500
	All integers in the series will lie in the range [-10^6,+10^6].
*/

import java.io.*;
import java.util.*;

class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int count = Integer.parseInt(input);
        input = scanner.nextLine();
        String[] strs = input.split(" ");
        scanner.close();
        if(count <= 1) return;
        if(strs.length < count) return;
        int[] nums = new int[count];
        for(int i = 0; i < count; i++) {
        	nums[i] = Integer.parseInt(strs[i]);
        }
        // find common diff
        int diff = nums[1] - nums[0];
        for(int i = 2; i < count; i++) {
        	int d = nums[i] - nums[i - 1];
        	if(d == diff) break;
        	else diff = d;
        }
        // find missing using binary search
        int start = 0, end = count - 1;
        while(start <= end) {
        	int mid = start + (end - start) / 2;
        	int mid_should = nums[0] + diff * mid;
        	if(mid_should == nums[mid]) {
        		start = mid + 1;
        	}
        	else {
        		// check if is itself
        		int mid_left_should = nums[0] + diff * (mid - 1);
        		if(mid_left_should == nums[mid - 1]) {
        			System.out.println(nums[mid - 1] + diff);
        			// end
        			break;
        		}
        		else {
        			end = mid - 1;
        		}
        	}
        }
    }
}

