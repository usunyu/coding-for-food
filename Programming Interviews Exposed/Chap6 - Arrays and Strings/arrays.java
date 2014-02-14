import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class arrays
{
	public void parse(char [] arr)
	{
		for(int i = 0; i < arr.length; ++i)
		{
			int val;
			if(arr[i] >= '0' && arr[i] <= '9')
				val = arr[i] - '0';
			else if(arr[i] >= 'A' && arr[i] <= 'F')
				val = arr[i] - 'A';
			else if(arr[i] >= 'a' && arr[i] <= 'f')
				val = arr[i] - 'a';
		}
	}

	public void fasterparse(char [] arr)
	{
		for(int i = 0; i < arr.length; ++i)
		{
			int val;
			char ch = arr[i];
			if(ch >= '0' && ch <= '9')
				val = ch - '0';
			else if(ch >= 'A' && ch <= 'F')
				val = ch - 'A';
			else if(ch >= 'a' && ch <= 'f')
				val = ch - 'a';
		}
	}

	public static void main(String[] args)
	{
		byte [] arrayA = new byte[10];
		byte [] arrayB = new byte[10];
		if(arrayA.length <= arrayB.length)
		{
			System.arraycopy(arrayA, 0, arrayB, 0, arrayA.length);
		}
		arrayA = arrayB;

		Button myButtons[] = new Button[5];
		for(int i = 0; i < 5; i++)
		{
			myButtons[i] = new Button();
		}

		System.out.println("Arrays");
	}
}