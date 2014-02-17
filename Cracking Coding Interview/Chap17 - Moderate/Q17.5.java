import java.util.HashMap;

class GuessResult {
	int hit;
	int pseudo_hit;
}

class Main {
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