import java.io.*;
import java.util.*;

public class string
{
	public static final int MAX_DIGITS = 10;

	public static String intToStr(int num)
	{
		int i = 0;
		boolean isNeg = false;
		// Buffer big enough for largest int and - sign
		char[] temp = new char[MAX_DIGITS + 1];

		// check to see if the number is negative
		if(num < 0)
		{
			num *= -1;
			isNeg = true;
		}

		// fill buffer with digit character is reverse order
		do
		{
			temp[i++] = (char)((num % 10) + '0');
			num /= 10;
		} while(num != 0);

		StringBuffer b = new StringBuffer();

		if(isNeg == true)
			b.append('-');

		while(i > 0)
			b.append(temp[--i]);

		return b.toString();
	}

	public static int strToInt(String str)
	{
		int i = 0, num = 0;
		boolean isNeg = false;
		int len = str.length();

		if(str.charAt(0) == '-')
		{
			isNeg = true;
			i = 1;
		}

		while(i < len)
		{
			num *= 10;
			num += (str.charAt(i++) - '0');
		}

		if(isNeg == true)
			num *= -1;

		return num;
	}

	public static String removeChars(String str, String remove)
	{
		char[] s = str.toCharArray();
		char[] r = remove.toCharArray();
		boolean[] flags = new boolean[128]; // assumes ASCII
		int len = s.length;
		int rlen = r.length;
		int src, dst;

		// set flags for characters to be removed
		for(src = 0; src < rlen; src++)
		{
			flags[r[src]] = true;
		}

		src = 0;
		dst = 0;

		// now loop through all the characters,
		// copying only if they aren’t flagged
		while(src < len)
		{
			if(flags[s[src]] ==  false)
			{
				s[dst++] = s[src];
			}
			++src;
		}
		
		return new String(s, 0, dst);
	}

	// more efficient
	public static Character fistNonRepeated2(String str)
	{
		Hashtable <Character, Object> charHash = new Hashtable <Character, Object> ();
		int i, length;
		Character c;
		Object seenOnce = new Object();
		Object seenTwice = new Object();

		length = str.length();

		// scan str, building hash table
		for(i = 0; i < length; i++)
		{
			c = new Character(str.charAt(i));
			Object o = charHash.get(c);
			if(o == null)
			{
				charHash.put(c, seenOnce);
			}
			else
			{
				charHash.put(c, seenTwice);
			}
		}

		// search hash table in order of str
		for(i = 0; i < length; i++)
		{
			c = new Character(str.charAt(i));
			if(charHash.get(c) == seenOnce)
				return c;
		}

		return null;
	}

	public static Character fistNonRepeated(String str)
	{
		Hashtable <Character, Integer> charHash = new Hashtable <Character, Integer> ();
		int i, length;
		Character c;
		Integer intgr;

		length = str.length();

		// scan str, building hash table
		for(i = 0; i < length; i++)
		{
			c = new Character(str.charAt(i));
			intgr = (Integer)charHash.get(c);
			if(intgr == null)
			{
				charHash.put(c, new Integer(1));
			}
			else
			{
				// increment count corresponding to c
				charHash.put(c, new Integer(intgr.intValue() + 1));
			}
		}

		// search hash table in order of str
		for(i = 0; i < length; i++)
		{
			c = new Character(str.charAt(i));
			if(((Integer)charHash.get(c)).intValue() == 1)
				return c;
		}

		return null;
	}

	public static void main(String[] args)
	{
		System.out.println("String.");

		String s = "";
		for(int i = 0; i < 10; ++i)
		{
			s = s + i + " ";
		}
		System.out.println(s);

		// Equivalent
		String s2 = "";
		for( int i = 0; i < 10; ++i )
		{
			StringBuffer t = new StringBuffer();
			t.append(s2);
			t.append(i);
			t.append(" ");
			s2 = t.toString();
		}
		System.out.println(s2);

		// More efficiently
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 10; ++i)
		{
			sb.append(i);
			sb.append(" ");
		}
		String s3 = sb.toString();
		System.out.println(s3);

		String str = "teeter";
		System.out.println("The String is: " + str);
		System.out.println("The first non-repeated character is: " + fistNonRepeated2(str));

		str = "abcdefghijklmnopqrstuvwxyz";
		String remove = "fko";
		System.out.println("The String is: " + str + ", the Character need to be removed is: " + remove);
		System.out.println("The string after removed is: " + removeChars(str, remove));

		String strDigit = "-19880922";
		System.out.println("The Digit String is: " + strDigit);
		System.out.println("The Digit number is: " + strToInt(strDigit));

		int intDigit = 0;
		System.out.println("The Digit Int is: " + intDigit);
		System.out.println("The Digit String is: " + intToStr(intDigit));
	}
}