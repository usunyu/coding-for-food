/*
Feel free to use any one of the C#, C/C++, Java, Python or PHP.

Now, the problem:

Almost-LZ77 compression
Given a description of the compressed data format, write code that compresses/decompresses files.

First, the compressed format:
A 0 bit followed by eight bits means just copy the eight bits to the output directly.
A 1 bit is followed by a pointer of 12 bits followed by a length encoded in 4 bits. This is to be interpreted as 
"copy the <length> bytes from <pointer> bytes ago in the output to the current location".
For example:

"mahi mahi" can be compressed as:

<0,'m'><0,'a'><0,'h'><0,'i'><

0,' '><1,4,4>

Original size = 9 bytes, compressed = just under 8 bytes.

You don't need to produce optimal compression (hard), greedy matching is fine. However, we want something that 
runs as fast as possible, without taking too much code (use your discretion).

The compressor and decompressor should take binary files as input and output. If you're familiar with Lempel-Ziv 
compressors, this is a simplified LZ77 compressor.

reference: http://cs.stanford.edu/people/eroberts/courses/soco/projects/2000-01/data-compression/lossless/lz77/index.htm
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class AlmostLZ77 {
	private static final int maxSize = 16;
	private static ArrayList<Byte> input = new ArrayList<Byte>();
	private static ArrayList<Byte> output = new ArrayList<Byte>();

	private static void readBytes(String fileName) {
		File file = new File(fileName);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte tempByte;
			while ((tempByte = (byte) in.read()) != -1) {
				input.add(tempByte);
            }
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void writeBytes(String fileName) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			for(byte b : output) {
				writer.write(b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static byte checkOutputByte(byte currentByte, byte currentBytePtr) {
		if(currentBytePtr == 8) {	// current byte filled
			output.add(currentByte);
			currentBytePtr = 0;
		}
		return currentBytePtr;
	}
	
	private static byte writeCurrentByte(byte currentByte, byte currentBytePtr, byte buffer, int i) {
		int bit = (buffer >> i) & 1;
		
		if(bit != 0) {
			currentByte |= (1 << (7 - currentBytePtr));
		}
		
		return currentByte;
	}
	
	private static void compress() {
		int windowSize = 0;
		byte currentByte = 0, currentBytePtr = 0;
		for(int pos = 0; pos < input.size(); pos++) {
			byte length = 0, pointer = -1;
			for(int i = pos - windowSize; i < pos; i++) {
				int match = 0, left = i, right = pos;
				while(left < pos && right < input.size() && input.get(left) == input.get(right)) {
					left++; right++; match++;
				}
				if(match > length) {
					length = (byte)match;
					pointer = (byte)(left - 1);
				}
			}
			
			if(pointer != -1) {
				currentByte |= (1 << (7 - currentBytePtr));	// first bit is 1
				
				for(int i = 0; i < 12; i++) {	// write pointer
					currentBytePtr = checkOutputByte(currentByte, currentBytePtr);
					currentByte = writeCurrentByte(currentByte, currentBytePtr, pointer, i);
					
					// debug
					printByte(currentByte);
					
					currentBytePtr++;
				}
				
				for(int i = 0; i < 4; i++) {	// write length
					currentBytePtr = checkOutputByte(currentByte, currentBytePtr);
					currentByte = writeCurrentByte(currentByte, currentBytePtr, length, i);
					
					// debug
					printByte(currentByte);
					
					currentBytePtr++;
				}
			}
			else {	// no match
				currentBytePtr++;	// first bit is 0
				byte cha = input.get(pos);	// character 
				for(int i = 0; i < 8; i++) {
					currentBytePtr = checkOutputByte(currentByte, currentBytePtr);
					currentByte = writeCurrentByte(currentByte, currentBytePtr, cha, i);
					
					// debug
					printByte(currentByte);
					
					currentBytePtr++;
				}
				
				// debug
				printByte(currentByte);
			}
			
			currentBytePtr = checkOutputByte(currentByte, currentBytePtr);
			
			if(windowSize < maxSize)
				windowSize++;
		}
		if(currentBytePtr != 0) {	// add last byte
			output.add(currentByte);
		}
	}
	
	private static void printByte(byte b) {
		System.out.println(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
	}
	
	private static void usage() {
		System.out.println("Usage: AlmostLZ77 compress|decompress InputFile [OutputFile]");
	}
	
	public static void main(String[] args) {
		if(args.length < 2) {
			usage();
			System.exit(-1);
		}
		String command = args[0];
		String input = args[1];
		String output = command + "_" + input;
		if(args.length == 3)
			output = args[2];
		
		readBytes(input);
		
		if(command.equals("compress")) {
			compress();
		}
		else if(command.equals("decompress")) {
			
		}
		else {
			usage();
			System.exit(-1);
		}
		
		writeBytes(output);
	}
}
