#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void reverseString(char str[], int start, int end);

void reverseWords2(char str[])
{
	int start = 0, end = 0, length;

	length = strlen(str);

	// reverse entire string
	reverseString(str, 0, length - 1);

	while(end < length)
	{
		if(str[end] != ' ') // skip the non-word characters
		{
			// save the position of begining of word
			start = end;

			// scan to next non-word characters
			while(end < length && str[end] != ' ')
				end++;
			// back up to end of word
			end--;

			// reverse word
			reverseString(str, start, end);
		}
		end++; // advance to next token
	}
}

void reverseString(char str[], int start, int end)
{
	char temp;
	while(end > start)
	{
		// exchange characters
		temp = str[start];
		str[start] = str[end];
		str[end] = temp;

		// move indices towards to middle
		start++;
		end--;
	}
}

int reverseWords(char str[])
{
	char * buffer;
	int tokenReadPos, wordReadPos, wordEnd, writePos = 0;

	// position of the last character is length - 1
	tokenReadPos = strlen(str) - 1;

	buffer = (char *)malloc(tokenReadPos + 2);
	if(!buffer)
		return 0; // allocate space failed

	while(tokenReadPos >= 0)
	{
		if(str[tokenReadPos] == ' ') // non-word characters
		{
			// write charcter
			buffer[writePos++] = str[tokenReadPos--];
		}
		else // word characters
		{
			// store position of end of word
			wordEnd = tokenReadPos;

			// scan to next non-word character
			while(str[tokenReadPos] != ' ' && tokenReadPos >= 0)
				tokenReadPos--;

			// tokenReadPos went past the start of the word
			wordReadPos = tokenReadPos + 1;

			// copy the characters of words
			while(wordReadPos <= wordEnd)
			{
				buffer[writePos++] = str[wordReadPos++];
			}			
		}
	}
	//null terminate buffer and copy over str
	buffer[writePos] = '\0';
	strcpy(str, buffer);

	free(buffer);

	return 1; // reverseWords successful
}

main()
{
	printf("String.\n");
	char str[] = "Hello World!";
	printf("The original string is: %s\n", str);
	reverseWords2(str);
	printf("After reverse, the string is: %s\n", str);
}