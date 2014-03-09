/*
You have a room-full of balances and weights. Each balance weighs ten pounds and is considered perfectly balanced when the 
sum of weights on its left and right sides are exactly the same. You have placed some weights on some of the balances, 
and you have placed some of the balances on other balances. Given a description of how the balances are arranged and how 
much additional weight is on each balance, determine how to add weight to the balances so that they are all perfectly balanced.

There may be more than one way to balance everything, but always choose the way that places additional weight on the lowest balances.

The input file will begin with a single integer, N, specifying how many balances there are.
Balance 0 is specified by lines 1 and 2, balance 1 is specified by lines 3 and 4, etc...
Each pair of lines is formatted as follows:

WL <balances>
WR <balances>

WL and WR indicate the weight added to the left and right sides, respectively. <balances> is a space-delimited list of the other 
balance that are on that side of this balance. <balances> may contain zero or more elements.

Consider the following input:

4
0 1
0 2
0
0 3
3
0
0
0

Balance 0 has balance 1 on its left side and balance 2 on its right side
Balance 1 has balance 3 on its right side
Balance 2 has three pounds on its left side
Balance 3 has nothing on it

Since balance 3 has nothing on it it is already perfectly balanced, and weighs a total of 10 pounds.
Balance 2 has no other balance on it, so all we need to do is balance it by putting three pounds on its right side. Now it weighs a 
total of 16 pounds.
Balance 1 has balance three on its right side, which weighs 10 pounds, so we just put 10 pounds on its left side. Balance 1 weighs 
a total of 30 pounds.
Balance 0 has balance 1 on its left side (30 pounds), and balance 2 on its right side (16 pounds), we can balance it by adding 14 
pounds to the right side.

The output should be N lines long, with the nth line listing the weight added to the nth balance, formatted as follows:

<index>: <weight added to left side> <weight added to right side>

So the output for this problem would be:

0: 0 14
1: 10 0
2: 0 3
3: 0 0
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Balance {
	int leftWeight;
	int rightWeight;
	
	ArrayList<Integer> leftBalances;
	ArrayList<Integer> rightBalances;
	
	int balanceWeight;	// the weight of balance, 10
	
	int leftAddWeight;	//	how many weight we need to add in left to make balanced
	int rightAddWeight;	//	how many weight we need to add in right to make balanced

	public Balance(int leftWeight, int rightWeight,
			ArrayList<Integer> leftBalances, ArrayList<Integer> rightBalances) {
		this.leftWeight = leftWeight;
		this.rightWeight = rightWeight;
		this.leftBalances = leftBalances;
		this.rightBalances = rightBalances;
		balanceWeight = 10;
	}

	@Override
	public String toString() {
		return "Balance [leftWeight=" + leftWeight + ", rightWeight="
				+ rightWeight + ", leftBalances=" + leftBalances
				+ ", rightBalances=" + rightBalances + "]";
	}
}

class Solution {
	// data structure to hold all balances
	static ArrayList<Balance> balances = new ArrayList<Balance>();
	
	private static int getBalancesWeight(ArrayList<Integer> balances, HashMap<Integer, Integer> balancedWeight) {
		int weight = 0;
		// get balances's weight if they are balanced
		for(int balance : balances) {
			if(balancedWeight.containsKey(balance)) {
				weight += balancedWeight.get(balance);
			}
			else return -1;	// one of balances is not balanced
		}
		return weight;
	}
	
	private static void process() {
		// store the weight of balance if it is balanced
		HashMap<Integer, Integer> balancedWeight = new HashMap<Integer, Integer>();
		while(balancedWeight.size() != balances.size()) {	// keep make balanced
			// try to make every balance balanced if we can
			for(int i = 0; i < balances.size(); i++) {
				Balance balance = balances.get(i);
				if(!balancedWeight.containsKey(i)) {	// it is not balanced, try to make it balanced
					// check balances its contained are balanced
					int leftBalancesWeight = getBalancesWeight(balance.leftBalances, balancedWeight);
					int rightBalancesWeight = getBalancesWeight(balance.rightBalances, balancedWeight);
					if(leftBalancesWeight != -1 && rightBalancesWeight != -1) {	// we can make it balanced
						int leftTotalWeight = leftBalancesWeight + balance.leftWeight;
						int rightTotalWeight = rightBalancesWeight + balance.rightWeight;
						if(leftTotalWeight > rightTotalWeight) {	// put weight on right
							balance.rightAddWeight = leftTotalWeight - rightTotalWeight;
							balance.rightWeight += balance.rightAddWeight;
						}
						else {	// put weight on left
							balance.leftAddWeight = rightTotalWeight - leftTotalWeight;
							balance.leftWeight += balance.leftAddWeight;
						}
						leftTotalWeight = leftBalancesWeight + balance.leftWeight;
						rightTotalWeight = rightBalancesWeight + balance.rightWeight;
						// we store the total weight of current balance in cache
						int totalWeight = leftTotalWeight + rightTotalWeight + balance.balanceWeight;
						balancedWeight.put(i, totalWeight);
					}
				}
			}
		}
	}
	
	private static void input(String filePath) {
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			int T = Integer.parseInt(sc.nextLine());
			// for each balance
			String line;
			String[] nodes;
			for(int i = 0; i < T; i++) {
				// left
				line = sc.nextLine();
				nodes = line.split(" ");
				int leftWeight = Integer.parseInt(nodes[0]);
				ArrayList<Integer> leftBalances = new ArrayList<Integer>();
				for(int j = 1; j < nodes.length; j++) {
					leftBalances.add(Integer.parseInt(nodes[j]));
				}
				// right
				line = sc.nextLine();
				nodes = line.split(" ");
				int rightWeight = Integer.parseInt(nodes[0]);
				ArrayList<Integer> rightBalances = new ArrayList<Integer>();
				for(int j = 1; j < nodes.length; j++) {
					rightBalances.add(Integer.parseInt(nodes[j]));
				}
				Balance balance = new Balance(leftWeight, rightWeight, leftBalances, rightBalances);
				balances.add(balance);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void input() {
		Scanner sc = new Scanner(System.in);
		// test case's number
		int T = Integer.parseInt(sc.nextLine());
		// for each balance
		String line;
		String[] nodes;
		for(int i = 0; i < T; i++) {
			// left part
			line = sc.nextLine();
			nodes = line.split(" ");
			// read left weight
			int leftWeight = Integer.parseInt(nodes[0]);
			// read left balances
			ArrayList<Integer> leftBalances = new ArrayList<Integer>();
			for(int j = 1; j < nodes.length; j++) {
				leftBalances.add(Integer.parseInt(nodes[j]));
			}
			// right part
			line = sc.nextLine();
			nodes = line.split(" ");
			// read right weight
			int rightWeight = Integer.parseInt(nodes[0]);
			// read right balances
			ArrayList<Integer> rightBalances = new ArrayList<Integer>();
			for(int j = 1; j < nodes.length; j++) {
				rightBalances.add(Integer.parseInt(nodes[j]));
			}
			Balance balance = new Balance(leftWeight, rightWeight, leftBalances, rightBalances);
			// add to balances list
			balances.add(balance);
		}
		sc.close();
	}
	
	private static void output() {
		// output as format require
		for(int i = 0; i < balances.size(); i++) {
			Balance balance = balances.get(i);
			System.out.println(i + ": " + balance.leftAddWeight + " " + balance.rightAddWeight);
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 0) input();
		else input(args[0]);
		process();
		output();
	}
}