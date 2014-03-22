/*
You are given C containers, B black balls and an unlimited number of white balls. 
You want to distribute balls between the containers in a way that every container 
contains at least one ball and the probability of selecting a white ball is greater or equal to P percent. 
The selection is done by randomly picking a container followed by randomly picking a ball from it. 

Find the minimal required number of white balls to achieve that.

INPUT

The first line contains 1 <= T <= 10 - the number of testcases.

Each of the following T lines contain three integers  C B P separated by a single space 1<= C <= 1000; 
0 <= B <= 1000; 0 <= P <= 100;

OUTPUT

For each testcase output a line containing an integer - the minimal number of white balls required.  
(The tests will assure that it's possible with a finite number of balls)

SAMPLE INPUT

3
1 1 60
2 1 60
10 2 50

SAMPLE OUTPUT

2
2
8

EXPLANATION

In the 1st testcase if we put 2 white balls and 1 black ball in the box the probability of selecting a white one is 66.(6)% 
which is greater than 60%

In the 2nd testcase putting a single white ball in one box and white+black in the other gives us 0.5 * 100% + 0.5 * 50% = 75%

For the 3rd testcase remember that we want at least one ball in each of the boxes.

10
70 70 70
65 50 58
51 51 60
47 47 61
50 51 69
55 61 66
42 39 63
45 46 58
55 62 60
63 68 62

49
38
31
29
35
37
27
27
33
40
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Case {
	int C, B, P;

	public Case(int C, int B, int P) {
		this.C = C;
		this.B = B;
		this.P = P;
	}
	
	public int calculate() {
		double p = (double) P / 100.0;
		int white = 0;
		if(C == 1) {
			white = (int) Math.ceil(p * B / (1 - p));
		}
		else {
			white = (int) Math.ceil(p * C);	// at least
			if(B < C - white) {	// need more
				white += (C - white - B);
			}
		}
		return white;
	}
}

class Solution {
	static ArrayList<Case> cases = new ArrayList<Case>();
	static ArrayList<Integer> result = new ArrayList<Integer>();

	private static void input(String filePath) {
		// assume the file is in the right format
		try {
			int T;
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			T = sc.nextInt();
			// for each case
			for (int i = 0; i < T; i++) {
				int C = sc.nextInt();
				int B = sc.nextInt();
				int P = sc.nextInt();
				Case cas = new Case(C, B, P);
				cases.add(cas);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void input() {
		int T;
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		// for each case
		for (int i = 0; i < T; i++) {
			int C = sc.nextInt();
			int B = sc.nextInt();
			int P = sc.nextInt();
			Case cas = new Case(C, B, P);
			cases.add(cas);
		}
		sc.close();
	}
	
	private static void process() {
		for(Case cas : cases) {
			result.add(cas.calculate());
		}
	}
	
	private static void output() {
		for(int i : result) {
			System.out.println(i);
		}
	}

	public static void main(String[] args) {
		if (args.length == 0) input();
		else input(args[0]);
		process();
		output();
	}
}