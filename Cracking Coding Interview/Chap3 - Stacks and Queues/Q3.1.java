import java.util.*;

class ThreeStack {
	int[] stackArray;
	int MAX = 1000;
	int top1, top2, top3;

	public ThreeStack() {
		stackArray = new int[MAX];
		top1 = 0;
		top2 = 1;
		top3 = 2;
	}

	public boolean isEmpty(int index) {
		switch (index) {
			case 0:
				return top1 == 0;
			case 1:
				return top2 == 1;
			case 2:
				return top3 == 2;
			default:
				break;
		}
		return false;
	}

	public void push(int value, int index) {
		switch (index) {
			case 0:
				stackArray[top1] = value;
				top1 += 3;
				break;
			case 1:
				stackArray[top2] = value;
				top2 += 3;
				break;
			case 2:
				stackArray[top3] = value;
				top3 += 3;
				break;
			default:
				break;
		}
	}

	public int pop(int index) {
		switch (index) {
			case 0:
				top1 -= 3;
				return stackArray[top1];
			case 1:
				top2 -= 3;
				return stackArray[top2];
			case 2:
				top3 -= 3;
				return stackArray[top3];
			default:
				break;
		}
		return -1;
	}
}

class Q3_1App {
	public static void main(String[] args) {
		ThreeStack threeStack = new ThreeStack();
		// first stack
		threeStack.push(10, 0);
		threeStack.push(9, 0);
		threeStack.push(8, 0);
		threeStack.push(7, 0);
		threeStack.push(6, 0);
		threeStack.push(5, 0);
		threeStack.push(4, 0);
		threeStack.push(3, 0);
		threeStack.push(2, 0);
		threeStack.push(1, 0);
		// second stack
		threeStack.push(1, 1);
		threeStack.push(2, 1);
		threeStack.push(3, 1);
		threeStack.push(4, 1);
		threeStack.push(5, 1);
		threeStack.push(6, 1);
		threeStack.push(7, 1);
		threeStack.push(8, 1);
		threeStack.push(9, 1);
		threeStack.push(10, 1);
		// third stack
		threeStack.push(5, 2);
		threeStack.push(4, 2);
		threeStack.push(3, 2);
		threeStack.push(2, 2);
		threeStack.push(1, 2);
		System.out.print("First Stack: ");
		while(!threeStack.isEmpty(0))
			System.out.print(threeStack.pop(0) + " ");
		System.out.println();
		System.out.print("Second Stack: ");
		while(!threeStack.isEmpty(1))
			System.out.print(threeStack.pop(1) + " ");
		System.out.println();
		System.out.print("Third Stack: ");
		while(!threeStack.isEmpty(2))
			System.out.print(threeStack.pop(2) + " ");
		System.out.println();
	}
}




