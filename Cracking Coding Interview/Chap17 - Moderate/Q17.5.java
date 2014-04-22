/*
The Came of Master Mind is played as follows:
The computer has four slots, and each slot will contain a ball that is red (R), yellow (Y), green (G) or blue (B). 
For example, the computer might have RGGB (Slot # 1 is red, Slots #2 and #3 are green, Slot #4 is blue).
You, the user, are trying to guess the solution. You might, for example, guess YRGB.
When you guess the correct color for the correct slot, you get a "hit." If you guess a color that exists but is in 
the wrong slot, you get a "pseudo-hit." Note that a slot that is a hit can never count as a pseudo-hit.
For example,if the actual solution is RGBY andyou guess GGRR, you have one hit and onepseudo-hit.
Write a method that, given a guess and a solution, returns the number of hits and pseudo-hits.
*/

import java.util.HashMap;

class GuessResult {
	int hit;
	int pseudo_hit;
}

class Solution {
	public static GuessResult guess(String answer, String guess) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i = 0; i < answer.length(); i++) {
			char ch = answer.charAt(i);
			if(map.containsKey(ch)) {
				map.put(ch, map.get(ch) + 1);
			}
			else {
				map.put(ch, 1);
			}
		}
		GuessResult result = new GuessResult();
		// check hit first
		for(int i = 0; i < guess.length(); i++) {
			char ch1 = guess.charAt(i);
			char ch2 = answer.charAt(i);
			if(ch1 == ch2) {
				result.hit++;
				int val = map.get(ch1);
				val--;
				if(val == 0) {
					map.remove(ch1);
				}
				else {
					map.put(ch1, val);
				}
			}
		}
		// check pseudo hit
		for(int i = 0; i < guess.length(); i++) {
			char ch1 = guess.charAt(i);
			if(map.containsKey(ch1)) {
				int val = map.get(ch1);
				val--;
				if(val == 0) {
					map.remove(ch1);
				}
				else {
					map.put(ch1, val);
				}
				result.pseudo_hit++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		GuessResult result = guess("RGBY", "GGRR");
		System.out.println("You got " + result.hit + " hits.");
		System.out.println("You got " + result.pseudo_hit + " pseudo-hits.");
	}
}