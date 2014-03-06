/*
Facebook hiring sample test

There are K pegs. Each peg can hold discs in decreasing order of radius when looked from bottom to top of the peg. 
There are N discs which have radius 1 to N; Given the initial configuration of the pegs and the final configuration of the pegs, 
output the moves required to transform from the initial to final configuration. You are required to do the transformations 
in minimal number of moves.

A move consists of picking the topmost disc of any one of the pegs and placing it on top of anyother peg.
At anypoint of time, the decreasing radius property of all the pegs must be maintained.

Constraints:
1<= N<=8
3<= K<=5

Input Format:
N K
2nd line contains N integers.
Each integer in the second line is in the range 1 to K where the i-th integer denotes the peg to which disc of radius i is 
present in the initial configuration.
3rd line denotes the final configuration in a format similar to the initial configuration.

Output Format:
The first line contains M - The minimal number of moves required to complete the transformation. 
The following M lines describe a move, by a peg number to pick from and a peg number to place on.
If there are more than one solutions, it's sufficient to output any one of them. You can assume, 
there is always a solution with less than 7 moves and the initial confirguration will not be same as the final one.

Sample Input #00:
 
2 3
1 1
2 2

Sample Output #00:
 
3
1 3
1 2
3 2

Sample Input #01:

6 4
4 2 4 3 1 1
1 1 1 1 1 1

Sample Output #01:

5
3 1
4 3
4 1
2 1
3 1

NOTE: You need to write the full code taking all inputs are from stdin and outputs to stdout 
If you are using "Java", the classname is "Solution"
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Peg {
	int peg;
	ArrayList<Disc> discs;
	
	public Peg(int peg) {
		this.peg = peg;
		discs = new ArrayList<Disc>();
	}
}

class Disc {
	int radius;
	int peg;
	
	public Disc(int radius) {
		this.radius = radius;
	}
}

class Move {
	int from;
	int to;
	
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public String toString() {
		return from + " " + to;
	}
}

class Solution {
	static int N, K; // N discs and K pegs
	static ArrayList<Peg> pegs = new ArrayList<Peg>();
	static ArrayList<Disc> discs = new ArrayList<Disc>();
	static int[] finalConfig;
	static ArrayList<Move> moves = new ArrayList<Move>();

	private static void input(String filePath) {
		// assume the file is in the right format
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			// read first line for N and K (N discs and K pegs)
			String line = sc.nextLine();
			String[] nodes = line.split(" ");
			N = Integer.parseInt(nodes[0]);
			K = Integer.parseInt(nodes[1]);
			// build discs
			for(int i = 0; i < N; i++) {
				discs.add(new Disc(i + 1));
			}
			// build pegs
			for(int i = 0; i < K; i++) {
				pegs.add(new Peg(i + 1));
			}
			// read second line for initial configuration
			line = sc.nextLine();
			nodes = line.split(" ");
			for (int i = 0; i < nodes.length; i++) {
				int p = Integer.parseInt(nodes[i]);
				Peg peg = pegs.get(p - 1);
				Disc disc = discs.get(i);
				disc.peg = p;
				peg.discs.add(disc);
			}
			// read third line for final configuration
			line = sc.nextLine();
			nodes = line.split(" ");
			finalConfig = new int[N];
			for(int i = 0; i < nodes.length; i++) {
				finalConfig[i] = Integer.parseInt(nodes[i]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Disc upDisc(Disc disc) {
		Peg onPeg = pegs.get(disc.peg - 1);
		int i = 0;
		Disc upDisc = null;
		while(onPeg.discs.get(i) != disc) {
			upDisc = onPeg.discs.get(i);
			i++;
		}
		return upDisc;
	}
	
	private static Disc toDisc(Disc disc, Peg toPeg) {
		Disc toDisc = null;
		if(toPeg.discs.size() > 0 && toPeg.discs.get(0).radius < disc.radius) {
			toDisc = toPeg.discs.get(0);
		}
		return toDisc;
	}
	
	private static Peg toPeg(Disc disc, Peg peg1, Peg peg2) {
		Peg peg = null;
		for(int i = 0; i < pegs.size(); i++) {
			if(pegs.get(i) == peg1 || pegs.get(i) == peg2) continue;
			else if(pegs.get(i).discs.size() > 0 && pegs.get(i).discs.get(0).radius < disc.radius) continue;
			else peg = pegs.get(i);
		}
		return peg;
	}
	
	private static void move(Disc disc, Peg fromPeg, Peg toPeg) {
		if(fromPeg != toPeg) {
			boolean success = false;
			while(!success) {
				Disc upDisc = null, toDisc = null;
				if((upDisc = upDisc(disc)) != null) {	// check up disc
					// move upDisc to a reasonable place
					Peg peg = toPeg(upDisc, fromPeg, toPeg);
					move(upDisc, fromPeg, peg);
				}
				else if((toDisc = toDisc(disc, toPeg)) != null) {	// check to disc
					// move toDisc to a reasonable place
					Peg peg = toPeg(toDisc, fromPeg, toPeg);
					move(toDisc, toPeg, peg);
				}
				else {	// we can move directly
					fromPeg.discs.remove(0);
					toPeg.discs.add(disc);
					disc.peg = toPeg.peg;
					Move move = new Move(fromPeg.peg, toPeg.peg);
					moves.add(move);
					success = true;
				}
			}
		}
	}
	
	private static void move() {
		// move form large to small
		for(int i = discs.size() - 1; i >= 0; i--) {
			Disc disc = discs.get(i);
			Peg fromPeg = pegs.get(disc.peg - 1);
			Peg toPeg = pegs.get(finalConfig[i] - 1);
			move(disc, fromPeg, toPeg);
		}
	}
	
	private static void output() {
		System.out.println(moves.size());
		for(Move move : moves) {
			System.out.println(move);
		}
	}
	
	private static void usage() {
		System.out.println("Usage: java Solution HanoiMoves_testcases/input00.txt.");
	}

	public static void main(String[] args) {
		if(args.length == 0) {
			usage();
			System.exit(-1);
		}
		input(args[0]);
		move();
		output();
	}
}
