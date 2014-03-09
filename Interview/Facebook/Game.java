/*
The beauty of a number X is the number of 1s in the binary representation of X.

Two players are plaing a game. There is number n written on a black board. The game is played as following:

	Each time a player chooses an integer number (0 <= k) so that 2^k is less than n and (n-2^k) has as beautiful as n.
	Next he removes n from blackboard and writes n-2^k instead.

The player that can not continue the game (there is no such k that satisfies the constrains) looses the game.

The First player starts the game and they play in turns alternatively. Knowing that both two players play optimally you have to specify the winner.

Input:

First line of the Input contains an integer T, the number of testcase. 0 <= T <= 5.

Then follow T lines, each containing an integer n.

 n more than 0 and less than 10^9 +1.

Output

For each testcase print "First Player" if first player can win the game and "Second Player" otherwise.

Sample Input

7
1
2
8
16
42
1000
123

Sample Output

Second Player
First Player
First Player
Second Player
Second Player
First Player
Second Player

Explanation

In the first example n is 1 and first player can't change it so the winner is the second player.

In the second example n is 2, so the first player subtracts 1 (2^0) from n and the second player looses the game.
*/

// http://learn.hackerearth.com/question/332/facebook-interview-question/
// http://learn.hackerearth.com/forum/55/facebook-interview-question/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Solution {
	static int T;
	static ArrayList<Integer> inputList = new ArrayList<Integer>();
	static ArrayList<Integer> winnerList = new ArrayList<Integer>();
	
	private static void input(String filePath) {
		// assume the file is in the right format
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			// read first line for T
			String line = sc.nextLine();
			T = Integer.parseInt(line);
			// read integers
			while(sc.hasNext()) {
				line = sc.nextLine();
				inputList.add(Integer.parseInt(line));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void input() {
		// assume the file is in the right format
		Scanner sc = new Scanner(System.in);
		// read first line for T
		String line = sc.nextLine();
		T = Integer.parseInt(line);
		// read integers
		while(sc.hasNext()) {
			line = sc.nextLine();
			inputList.add(Integer.parseInt(line));
		}
		sc.close();
	}
	
	private static void output() {
		for(int winner : winnerList) {
			if(winner == 1) System.out.println("First Player");
			else System.out.println("Second Player");
		}
	}
	
	private static int countBinOne(int num) {
		int count = 0;
		while(num > 0) {
			if((num & 1) == 1) count++;
			num >>= 1;
		}
		return count;
	}
	
	private static int findN2K(int num, int beauty) {
		// find K
		int sum = 1;
		while(sum < num) {
			// we choose first one, actually no matter how we chose,
			// the final result will be same
			int n_2k = num - sum;
			if(countBinOne(n_2k) == beauty) {
				return n_2k;
			}
			sum *= 2;
		}
		return -1;
	}
	
	private static void playing(int num) {
		boolean firstPlayer = true, end = false;
		while(!end) {
			// num - 2^K = beauty
			int n_2k = findN2K(num, countBinOne(num));
			if(firstPlayer) {
				if(n_2k == -1) {	// player two win
					winnerList.add(2);
					end = true;
				}
				firstPlayer = false;
			}
			else {
				if(n_2k == -1) {	// player one win
					winnerList.add(1);
					end = true;
				}
				firstPlayer = true;
			}
			num = n_2k;
		}
	}
	
	private static void playing() {
		for(int i = 0; i < inputList.size(); i++) {
			playing(inputList.get(i));
		}
	}
	
	public static void main(String[] args) {
		if (args.length == 0) input();
		else input(args[0]);
		playing();
		output();
	}
}

