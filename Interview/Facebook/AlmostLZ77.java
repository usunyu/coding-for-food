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

reference:
http://cs.stanford.edu/people/eroberts/courses/soco/projects/2000-01/data-compression/lossless/lz77/index.htm
http://hi.baidu.com/sihochina/item/c4c66e0ae3033b25a1312d65
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class AlmostLZ77 {
	private static final int maxSize = (int)Math.pow(2, 12);	// max window size
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
	
	private static boolean isCurrentByteFilled(byte currentByte, byte currentBytePtr) {
		if(currentBytePtr == 8) {	// current byte filled
			output.add(currentByte);
			return true;
		}
		return false;
	}
	
	private static int getBit(int buffer, int i) {
		return (buffer >> i) & 1;
	}
	
	private static int setBit(int buffer, int i) {
		return buffer | (1 << i);
	}
	
	private static byte writeCurrentByte(byte currentByte, byte currentBytePtr, int pointer, int i) {
		int bit = getBit(pointer, i);
		
		if(bit != 0) {
			currentByte |= (1 << (7 - currentBytePtr));
		}
		
		return currentByte;
	}
	
	private static void compress() {
		int windowSize = 0;
		
		// debug
		printBytesChar(input);
		
		byte currentByte = 0, currentBytePtr = 0;
		for(int pos = 0; pos < input.size(); pos++) {
			byte length = 0;
			int pointer = -1;
			for(int i = pos - windowSize; i < pos; i++) {
				int match = 0, left = i, right = pos;
				while(left < pos && right < input.size() && input.get(left) == input.get(right)) {
					left++; right++; match++;
				}
				if(match > length) {
					length = (byte)match;
					pointer = (byte)(pos - left);	// pointer is relative position to pos
				}
			}
			
			if(pointer != -1) {
				currentByte |= (1 << (7 - currentBytePtr));	// first bit is 1
				
				for(int i = 0; i < 12; i++) {	// write pointer
					if(isCurrentByteFilled(currentByte, currentBytePtr)) {
						currentByte = 0; currentBytePtr = 0;
					}
					currentByte = writeCurrentByte(currentByte, currentBytePtr, pointer, i);
					
					// debug
					printByteBinary(currentByte);
					
					currentBytePtr++;
				}
				
				for(int i = 0; i < 4; i++) {	// write length
					if(isCurrentByteFilled(currentByte, currentBytePtr)) {
						currentByte = 0; currentBytePtr = 0;
					}
					currentByte = writeCurrentByte(currentByte, currentBytePtr, length, i);
					
					// debug
					printByteBinary(currentByte);
					
					currentBytePtr++;
				}
			}
			else {	// no match
				currentBytePtr++;	// first bit is 0
				byte cha = input.get(pos);	// character 
				for(int i = 0; i < 8; i++) {
					if(isCurrentByteFilled(currentByte, currentBytePtr)) {
						currentByte = 0; currentBytePtr = 0;
					}
					currentByte = writeCurrentByte(currentByte, currentBytePtr, cha, i);
					
					// debug
					printByteBinary(currentByte);
					
					currentBytePtr++;
				}
				
				// debug
				printByteBinary(currentByte);
			}
			
			if(isCurrentByteFilled(currentByte, currentBytePtr)) {
				currentByte = 0; currentBytePtr = 0;
			}
			
			if(windowSize < maxSize)
				windowSize++;
		}
		if(currentBytePtr != 0) {	// add last byte
			output.add(currentByte);
		}
	}
	
	private static void decompress() {
		boolean started = false;
		int bitCount = 0;
		ArrayList<Byte> buffer = new ArrayList<Byte>();
		ArrayList<Integer> flag = new ArrayList<Integer>();
		byte currentWriteByte = 0, currentWriteBytePtr = 0;
		for(byte currentByte : input) {
			for(byte currentBytePtr = 0; currentBytePtr < 8; currentBytePtr++) {	// read bit by bit
				if(!started) {	// start a new block
					currentWriteByte = 0;
					currentWriteBytePtr = 0;
					started = true;
					int bit = getBit(currentByte, currentBytePtr);
					if(bit != 0) {
						bitCount = 16;
						flag.add(1);
					}
					else {
						bitCount = 8;
						flag.add(0);
					}
					continue;
				}
				if(bitCount > 0) {	// we still have bits to read
					if(currentWriteBytePtr == 8) {
						buffer.add(currentWriteByte);
						currentWriteByte = 0;
						currentWriteBytePtr = 0;
					}
					
					int bit = getBit(currentByte, currentBytePtr);
					
					if(bit != 0) {
						currentWriteByte |= (1 << currentWriteBytePtr++);
					}
					
					bitCount--;
				}
				if(bitCount == 0) {	// current block read finish
					started = false;
					buffer.add(currentWriteByte);
					currentWriteByte = 0;
					currentWriteBytePtr = 0;
				}
			}
		}
		int index = 0;
		for(int flg : flag) {
			if(flg == 0) {	// read one byte and output
				output.add(buffer.get(index++));
			}
			else {	// read two byte and decompress
				byte b1 = buffer.get(index++);	// first 12 bits indicate pointer
				byte b2 = buffer.get(index++);	// next 4 bits indicate length
				int pointer = b1;
				for(int i = 0; i < 4; i++) {
					pointer <<= 1;
					int bit = getBit(b2, 7 - i);
					if(bit != 0) {
						pointer = setBit(pointer, 0);
					}
				}
				byte length = 0;
				for(int i = 4; i < 8; i++) {
					length <<= 1;
					int bit = getBit(b2, 7 - i);
					if(bit != 0) {
						length = (byte)setBit(length, 0);
					}
				}
				int prev = output.size() - pointer;
				for(int i = 0; i < length; i++) {
					output.add(output.get(prev++));
				}
			}
		}
	}
	
	private static void printBytesChar(ArrayList<Byte> bytes) {
		for(byte b : bytes) {
			System.out.print((char) b);
		}
		System.out.println();
	}
	
	private static void printByteBinary(byte b) {
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
			writeBytes(output);
		}
		else if(command.equals("decompress")) {
			decompress();
			writeBytes(output);
		}
		else {
			usage();
			System.exit(-1);
		}
	}
}
