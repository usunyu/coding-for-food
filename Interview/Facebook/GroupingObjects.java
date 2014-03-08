/*
There are N objects kept in a row. The ith object is at position x_i. You want to partition them into K groups. 
You want to move all objects belonging to the same group to the same position. Objects in two different groups may 
be placed at the same position. What is the minimum total amount by which you need to move the objects to accomplish this?

Input:
The first line contains the number of test cases T. T test cases follow. The first line contains N and K. 
The next line contains N space seperated integers, denoting the original positions x_i of the objects.

Output:
Output T lines, containing the total minimum amount by which the objects should be moved.

Constraints:
1 <= T <= 1000
1 <= K <= N <= 200
0 <= x_i <= 1000

Sample Input:
3
3 3
1 1 3
3 2
1 2 4
4 2
1 2 5 7

Sample Output:
0
1
3

Explanation:

For the first case, there is no need to move any object.
For the second case, group objects 1 and 2 together by moving the first object to position 2.
For the third case, group objects 1 and 2 together by moving the first object to position 2 and group objects 3 and 4 
together by moving object 3 to position 7. Thus the answer is 1 + 2 = 3.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

class Case {
	int N;	// number of objects
	int K;	// number of groups
	LinkedList<Group> groups;	// groups
	
	public Case(int N, int K) {
		this.N = N;
		this.K = K;
		groups = new LinkedList<Group>();
	}
	
	// find steps need for every group to move all objects to its nearest group
	public int calMoveGroup() {
		ListIterator<Group> iterator = (ListIterator<Group>) groups.iterator();
		int minMove = Integer.MAX_VALUE;
		while(iterator.hasNext()) {
			Group currentGroup = iterator.next();
			int prevDis = Integer.MAX_VALUE, nextDis = Integer.MAX_VALUE;
			iterator.previous();
			if(iterator.hasPrevious()) {
				Group prevGroup = iterator.previous();
				prevDis = currentGroup.position - prevGroup.position;
				iterator.next();
			}
			iterator.next();
			if(iterator.hasNext()) {
				Group nextGroup = iterator.next();
				nextDis = nextGroup.position - currentGroup.position;
				iterator.previous();
			}
			int dis = Math.min(prevDis, nextDis);
			int move = currentGroup.objects.size() * dis;
			currentGroup.minMove = move;
			minMove = Math.min(minMove, move);
		}
		return minMove;
	}
	
	public void removeMinGroup(int minMove) {
		ListIterator<Group> iterator = (ListIterator<Group>) groups.iterator();
		while(iterator.hasNext()) {
			Group currentGroup = iterator.next();
			if(currentGroup.minMove == minMove) {
				iterator.previous();
				int prevDis = Integer.MAX_VALUE, nextDis = Integer.MAX_VALUE;
				Group prevGroup = null, nextGroup = null;
				if(iterator.hasPrevious()) {
					prevGroup = iterator.previous();
					prevDis = currentGroup.position - prevGroup.position;
					iterator.next();
				}
				iterator.next();
				if(iterator.hasNext()) {
					nextGroup = iterator.next();
					nextDis = nextGroup.position - currentGroup.position;
					iterator.previous();
				}
				if(prevDis < nextDis) {	// move all objects to prev group
					prevGroup.objects.addAll(currentGroup.objects);
				}
				else {	// move all objects to next group
					nextGroup.objects.addAll(currentGroup.objects);
				}
				iterator.remove();
				break;
			}
		}
	}
	
	public void insertObject(int object, int position) {
		if(groups.size() == 0) {
			Group group = new Group(position);
			group.objects.add(object);
			groups.add(group);
		}
		else {	// find a right place to insert
			boolean inserted = false;
			ListIterator<Group> iterator = (ListIterator<Group>) groups.iterator();
			while(iterator.hasNext()) {
				Group current = iterator.next();
				if(current.position == position) {	// should insert to this group
					current.objects.add(object);
					inserted = true;
				}
				if(current.position > position) {	// should create a new group and insert
					Group group = new Group(position);
					group.objects.add(object);
					iterator.add(group);
					inserted = true;
				}
				if(inserted) break;
			}
			if(!inserted) {
				Group group = new Group(position);
				group.objects.add(object);
				iterator.add(group);
				inserted = true;
			}
		}
	}

	@Override
	public String toString() {
		return "Case [N=" + N + ", K=" + K + ", groups=" + groups + "]";
	}
}

class Group {
	int position;
	ArrayList<Integer> objects;
	int minMove;
	
	public Group(int position) {
		this.position = position;
		this.objects = new ArrayList<Integer>();
	}

	public Group(Group group) {
		this.position = group.position;
		this.objects = new ArrayList(group.objects);
	}

	@Override
	public String toString() {
		return "Group [position=" + position + ", objects=" + objects + "]";
	}
}

// Greedy Solution
class Solution {
	static int T;	// number of cases
	static ArrayList<Case> Cases = new ArrayList<Case>();
	static ArrayList<Integer> Result = new ArrayList<Integer>();
	
	// find steps need for every group to move all objects to its nearest group
	// get the minimal one and move
	private static void calculate() {
		for(Case cas : Cases) {
			int moves = 0;
			LinkedList<Group> groups = cas.groups;
			while(groups.size() > cas.K) {
				int move = cas.calMoveGroup();
				cas.removeMinGroup(move);
				moves += move;
			}
			Result.add(moves);
		}
	}
	
	private static void output() {
		for(int res : Result) {
			System.out.println(res);
		}
	}
	
	private static void input(String filePath) {
		// assume the file is in the right format
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			// read first line for T
			String line = sc.nextLine();
			T = Integer.parseInt(line);
			// read test cases
			while(sc.hasNext()) {
				// read N and K
				line = sc.nextLine();
				String[] nodes = line.split(" ");
				int N = Integer.parseInt(nodes[0]);
				int K = Integer.parseInt(nodes[1]);
				Case cas = new Case(N, K);
				// read positions
				line = sc.nextLine();
				nodes = line.split(" ");
				for(int i = 0; i < nodes.length; i++) {
					int position = Integer.parseInt(nodes[i]);
					cas.insertObject(i, position);
				}
				Cases.add(cas);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void usage() {
		System.out.println("Usage: java Solution GroupingObjects_testcases/input000.txt.");
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			usage();
			System.exit(-1);
		}
		input(args[0]);
		calculate();
		output();
	}
}

class Status {
	ArrayList<Group> groups;
	int move;

	public Status(LinkedList<Group> groups) {
		this.groups = new ArrayList<Group>();
		for(Group group : groups) {
			this.groups.add(new Group(group));
		}
		move = 0;
	}
	
	public Status(ArrayList<Group> groups) {
		this.groups = groups;
		move = 0;
	}
	
	public ArrayList<Group> copyGroups(ArrayList<Group> groups) {
		ArrayList<Group> grList = new ArrayList<Group>();
		for(Group group : groups) {
			grList.add(new Group(group));
		}
		return grList;
	}
	
	public ArrayList<Status> nextStatus() {
		ArrayList<Status> nextStatus = new ArrayList<Status>();
		for(int i = 0; i < groups.size(); i++) {
			ArrayList<Group> locGroups;
			Status status;
			Group curGroup, preGroup, nextGroup;
			// move to left
			if(i > 0) {
				locGroups = copyGroups(groups);
				
				curGroup = locGroups.get(i);
				preGroup = locGroups.get(i - 1);
				int dis = curGroup.position - preGroup.position;
				int move = dis * curGroup.objects.size();
				preGroup.objects.addAll(curGroup.objects);
				locGroups.remove(i);
				
				status = new Status(locGroups);
				status.move = this.move + move;
				nextStatus.add(status);
			}
			// move to right
			if(i < groups.size() - 1) {
				locGroups = copyGroups(groups);
				
				curGroup = locGroups.get(i);
				nextGroup = locGroups.get(i + 1);
				int dis = nextGroup.position - curGroup.position;
				int move = dis * curGroup.objects.size();
				nextGroup.objects.addAll(curGroup.objects);
				locGroups.remove(i);
				
				status = new Status(locGroups);
				status.move = this.move + move;
				nextStatus.add(status);
			}
		}
		return nextStatus;
	}

	@Override
	public String toString() {
		return "Status [groups=" + groups + ", move=" + move + "]";
	}
	
}

// BFS Solution
// should be optimal
class Solution2 {
    static int T;	// number of cases
	static ArrayList<Case> Cases = new ArrayList<Case>();
	static ArrayList<Integer> Result = new ArrayList<Integer>();
	
	// store two possible moves to its neighbors
	private static void calculate() {
		for(Case cas : Cases) {
			int size = cas.groups.size();
			Status status = new Status(cas.groups);
			ArrayList<Status> stList = new ArrayList<Status>();
			stList.add(status);
			while(size > cas.K) {
				ArrayList<Status> tmpList = new ArrayList<Status>();
				for(Status st : stList) {
					tmpList.addAll(st.nextStatus());
				}
				size--;
				stList = tmpList;
			}
			int minMove = Integer.MAX_VALUE;
			for(Status st : stList) {
				minMove = Math.min(st.move, minMove);
			}
			Result.add(minMove);
		}
	}
	
	private static void output() {
		for(int res : Result) {
			System.out.println(res);
		}
	}
	
	private static void input(String filePath) {
		// assume the file is in the right format
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			// read first line for T
			String line = sc.nextLine();
			T = Integer.parseInt(line);
			// read test cases
			while(sc.hasNext()) {
				// read N and K
				line = sc.nextLine();
				String[] nodes = line.split(" ");
				int N = Integer.parseInt(nodes[0]);
				int K = Integer.parseInt(nodes[1]);
				Case cas = new Case(N, K);
				// read positions
				line = sc.nextLine();
				nodes = line.split(" ");
				for(int i = 0; i < nodes.length; i++) {
					int position = Integer.parseInt(nodes[i]);
					cas.insertObject(i, position);
				}
				Cases.add(cas);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void usage() {
		System.out.println("Usage: java Solution GroupingObjects_testcases/input000.txt.");
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			usage();
			System.exit(-1);
		}
		input(args[0]);
		calculate();
		output();
	}
}
