public class recursion
{
	static final int PHONE_NUMBER_LENGTH = 7;

	public static void printTelephoneWords2(int[] phoneNum)
	{
		char[] result = new char[PHONE_NUMBER_LENGTH];
		// initialize the result
		for(int i = 0; i < PHONE_NUMBER_LENGTH; i++)
			result[i] = getCharKey(phoneNum[i], 0);

		// main loop
		while(true)
		{
			System.out.println(new String(result));

			// start at the end and try to increment
			for(int i = PHONE_NUMBER_LENGTH - 1; i >= -1; i--)
			{
				// you are done, because you tried to carry the leftmost digit
				if(i == -1)
					return;

				// otherwise, we are not done and must continue

				// start with this condition so that you can deal with special case, 0 and 1 right away
				if(getCharKey(phoneNum[i], 2) == result[i] || phoneNum[i] == 0 || phoneNum[i] == 1)
				{
					result[i] = getCharKey(phoneNum[i], 0);
				}
				else if(getCharKey(phoneNum[i], 0) == result[i])
				{
					result[i] = getCharKey(phoneNum[i], 1);
					break;
				}
				else if(getCharKey(phoneNum[i], 1) == result[i])
				{
					result[i] = getCharKey(phoneNum[i], 2);
					break;
				}
			}
		}
	}

	public static void printTelephoneWords(int[] phoneNum)
	{
		char[] result = new char[PHONE_NUMBER_LENGTH];

		doPrintTelephoneWords(phoneNum, 0, result);
	}

	public static void doPrintTelephoneWords(int[] phoneNum, int curDigit, char[] result)
	{
		if(curDigit == PHONE_NUMBER_LENGTH)
		{
			System.out.println(new String(result));
			return;
		}

		for(int i = 0; i < 3; i++)
		{
			result[curDigit] = getCharKey(phoneNum[curDigit], i);
			doPrintTelephoneWords(phoneNum, curDigit + 1, result);
			if(phoneNum[curDigit] == 0 || phoneNum[curDigit] == 1)
				return;
		}
	}

	static final char[][] PHONE_CHAR_MAP = {{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'},
		{'M', 'N', 'O'}, {'P', 'R', 'S'}, {'T', 'U', 'V'}, {'W', 'X', 'Y'}};

	public static char getCharKey(int teleKey, int place)
	{
		char teleChar;
		if(teleKey == 0)
			teleChar = '0';
		else if(teleKey == 1)
			teleChar = '1';
		else
			teleChar = PHONE_CHAR_MAP[teleKey - 2][place];

		return teleChar;
	}

	public static void combine2(String str)
	{
		int length = str.length();
		char[] instr = str.toCharArray();
		StringBuffer outstr = new StringBuffer();

		doCombine2(instr, outstr, length, 0, 0);
	}

	public static void doCombine2(char[] instr, StringBuffer outstr, int length, int level, int start)
	{
		for(int i = start; i < length; i++)
		{
			outstr.append(instr[i]);
			System.out.println(outstr.toString());

			// if(i < length - 1)
				doCombine2(instr, outstr, length, level + 1, i + 1);

			outstr.setLength(outstr.length() - 1);
		}
	}

	public static void combine(String str)
	{
		int length = str.length();
		char[] instr = str.toCharArray();
		StringBuffer outstr = new StringBuffer();

		doCombine(instr, outstr, length, 0);
	}

	public static void doCombine(char[] instr, StringBuffer outstr, int length, int level)
	{
		if(length == level)
		{
			if(outstr.length() > 0)
				System.out.println(outstr.toString());
			return;
		}

		// the char is in the outstr
		outstr.append(instr[level]);
		doCombine(instr, outstr, length, level + 1);
		// the char is not in the outstr
		outstr.setLength(outstr.length() - 1);
		doCombine(instr, outstr, length, level + 1);
	}

	public static void permute(String str)
	{
		int length = str.length();
		boolean[] used = new boolean[length];
		StringBuffer out = new StringBuffer();
		char[] in = str.toCharArray();

		doPermute(in, out, used, length, 0);
	}

	public static void doPermute(char[] in, StringBuffer out, boolean[] used, int length, int level)
	{
		if(length == level)
		{
			System.out.println(out.toString());
			return;
		}

		for(int i = 0; i < length; i++)
		{
			if(used[i] == true)
				continue;

			out.append(in[i]);
			used[i] = true;
			doPermute(in, out, used, length, level + 1);
			used[i] = false;
			out.setLength(out.length() - 1);
		}
	}

	public static int[] allFactorials(int n)
	{
		// wrapper routine
		int[] results = new int[n == 0 ? 1 : n];
		doAllFactorials(n, results, 0);
		return results;
	}

	public static int doAllFactorials(int n, int[] results, int level)
	{
		if(n > 1) // recursive case
		{
			results[level] = n * doAllFactorials(n - 1, results, level + 1);
			return results[level];
		}
		else // base case
		{
			results[level] = 1;
			return 1;
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Recursion.");

		int n = 10;
		int[] results = allFactorials(n);
		for(int i = 0; i < n; i++)
		{
			System.out.print(results[i]);
			System.out.print(' ');
		}
		System.out.println();

		String str = "abcd";
		System.out.println("Permute " + str);
		// permute(str);

		str = "wxyz";
		System.out.println("Combine " + str);
		// combine(str);

		System.out.println("Combine2 " + str);
		// combine2(str);

		int teleKey = 6, place = 2;
		// System.out.println("getCharKey Key: " + teleKey + " Place: " + place + " Value: " + getCharKey(teleKey, place));

		int[] phoneNum = {4, 9, 7, 1, 9, 2, 7};
		System.out.print("Phone Number ");
		for(int i = 0; i < phoneNum.length; i++)
			System.out.print(phoneNum[i]);
		System.out.println();
		printTelephoneWords2(phoneNum);
	}
}