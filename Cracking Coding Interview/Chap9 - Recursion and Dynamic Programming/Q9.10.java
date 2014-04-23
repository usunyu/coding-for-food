/*
You have a stack of n boxes, with widths wi, heights hi and depths di. 
The boxes cannot be rotated and can only be stacked on top of one another if each box in the stack is 
strictly larger than the box above it in width, height, and depth. 
Implement a method to build the tallest stack possible, where the height of a stack is the sum 
of the heights of each box.
*/
/* Thought
using dynamic programming, sort the box list first(a can be above of b, a < b. a cannot be above of b and
b cannot be above of a, a '=' b)
*/
import java.util.*;

class Box {
	int width, height, depth;

	public Box(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	public boolean isLargerThan(Box box) {
		if(width > box.width && height > box.height && depth > box.depth)
			return true;
		else
			return false;
	}
}

class Solution {

	public static int getMaxHeight(ArrayList<Box> boxList) {
		int heightMax = -1;
		for(int i = 0; i < boxList.size(); i++) {
			ArrayList<Box> boxStack = new ArrayList<Box>();
			boxStack.add(boxList.get(i));
			ArrayList<Box> newBoxList = new ArrayList<Box>(boxList);
			newBoxList.remove(i);
			int height = getMaxHeight(newBoxList, boxStack);
			if(height > heightMax)
				heightMax = height;
		}
		return heightMax;
	}

	public static int getHeight(ArrayList<Box> boxList) {
		int height = 0;
		for(Box box : boxList) {
			height += box.height;
		}
		return height;
	}

	public static int getMaxHeight(ArrayList<Box> boxList, ArrayList<Box> boxStack) {
		Box top = boxStack.get(boxStack.size() - 1);
		int heightMax = -1;
		boolean noSmall = true;
		for(int i = 0; i < boxList.size(); i++) {
			if(top.isLargerThan(boxList.get(i))) {
				noSmall = false;
				ArrayList<Box> newBoxStack = new ArrayList<Box>(boxStack);
				newBoxStack.add(boxList.get(i));
				ArrayList<Box> newBoxList = new ArrayList<Box>(boxList);
				newBoxList.remove(i);
				int height = getMaxHeight(newBoxList, newBoxStack);
				if(height > heightMax)
					heightMax = height;
			}
		}
		if(noSmall)
			return getHeight(boxStack);
		else
			return heightMax;
	}

	public static void main(String[] args) {
		ArrayList<Box> boxList = new ArrayList<Box>();
		boxList.add(new Box(20, 30, 10));
		boxList.add(new Box(10, 40, 20));
		boxList.add(new Box(40, 50, 30));
		System.out.println("Max height is " + getMaxHeight(boxList));
	}
}