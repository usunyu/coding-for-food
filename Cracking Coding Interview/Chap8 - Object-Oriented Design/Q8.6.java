/*
Implement a jigsaw puzzle. Design the data structures and explain an algorithm to solve the puzzle. 
You can assume that you have a fits With method which, when passed two puzzle pieces, returns true 
if the two pieces belong together.
*/

import java.util.*;

class Piece {
	int pieceSum;

	public Piece() {
		pieceSum = 1;
	}

	public void combine(Piece p) {
		System.out.println("Combine " + pieceSum + " pieces and " + p.pieceSum + " pieces together.");
		pieceSum += p.pieceSum;
		p.pieceSum = 0;
	}
}

class Jigsaw {
	int LENGTH;
	int WIDTH;
	ArrayList<Piece> pieceList;

	public Jigsaw(int l, int w) {
		LENGTH = l;
		WIDTH = w;
		pieceList = new ArrayList<Piece>(LENGTH * WIDTH);
		for(int i = 0; i < LENGTH * WIDTH; i++) {
			Piece piece = new Piece();
			pieceList.add(piece);
		}
	}

	public boolean fitsWith(Piece p1, Piece p2) {
		if(Math.random() > 0.5)
			return true;
		else
			return false;
	}

	public void play() {
		for(int i = 0; i < LENGTH * WIDTH; i++) {
			Piece p1 = pieceList.get(i);
			if(p1.pieceSum == LENGTH * WIDTH) {
				System.out.println("Game finished!");
				break;
			}
			int j = i + 1;
			for(; j < LENGTH * WIDTH; j++) {
				Piece p2 = pieceList.get(j);
				if(fitsWith(p1, p2)) {
					p2.combine(p1);
					break;
				}
			}
			if(j == LENGTH * WIDTH) {	// at least one fit
				Piece p2 = pieceList.get(j - 1);
				p2.combine(p1);
			}
		}
	}
}

class Solution {
	public static void main(String[] args) {
		Jigsaw game = new Jigsaw(20, 25);
		game.play();
	}
}