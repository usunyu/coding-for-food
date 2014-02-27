/*
	Rocket Fuel Inc Hiring Test 1 (Short)
	Question 1 / 1 (Lazer Maze)

	You are standing in a rectangular room and are about to fire a laser toward the east wall. 
	Inside the room a certain number of prisms have been placed. They will alter the direction of the laser beam if it hits them. 
	There are north-facing, east-facing, west-facing, and south-facing prisms. If the laser beam strikes an east-facing prism, 
	its course will be altered to be East, regardless of what direction it had been going in before.  
	If it hits a south-facing prism, its course will be altered to be South, and so on. You are interested to know how far the laser 
	will travel before it hits a wall.
 
	INPUT
	Your program must read the room description from standard input.  The room is represented as a character array.  
	The width and height of the array are the width and height of the room.  The characters inside the array denote the placement of 
	the prisms and the laser's starting position. Each line of input from STDIN represents a row of the array.
	The number of lines/elements will not be specified in the input, so the program will have to keep reading from STDIN until there 
	are no more lines to read and determine the total number of elements based on the input.  However, the number of lines of input 
	will be at most 50. Each line will contain the same number of characters.
	The resulting character array will contain exactly one '@' character, representing the laser's position in the room, and any 
	number of characters from the set {"^", "V", "<", ">", "-"}.  The first four of these represent prisms that are guaranteed to 
	direct the laser in the direction in which they are pointing.  The "^" character directs the laser north, "<" directs it to the west, 
	and so on.  The  "-" character represents empty space.
	 
	OUTPUT
	Your program should print to standard out a single string, terminated by a newline, representing the distance that the laser will 
	travel before hitting a wall.  For example, if the laser travels a distance of 14 cells before hitting a wall, then your program 
	should print the string "14" to STDOUT.   Your program should treat the "@'"symbol the same as the "-" character, that is, as empty 
	space.  So the laser will pass through the original location from which it was fired.
	If the laser will get caught in an infinite loop, then your program should print "-1" to STDOUT.
	 
	Directions:
	Please code this problem in Java, C++, C, or Python (all else being equal, we prefer Java) using best coding practices.
	NOTE: If you download the attached .zip file of examples, and you are running Windows, do not look at them using the windows program 
	"Notepad", because this will not show the carriage returns properly in the input files.  Look at them with WordPad.  Each input 
	file should consist of multiple lines, each of the same length.
	 
	What We Are Looking For:
	We are looking for clear, concise, well-documented, modular code that reflects good design, object-oriented principles, and understanding 
	of appropriate data structures. We not only want to see code that works, but code that is as beautiful as you can make it according to 
	your personal coding aesthetic. 
 
	Examples:
	Input:
	@--
	---
	---
	Output: 3
	 
	Input:
	@-v
	---
	---
	---
	Output: 6
	 
	Input:
	@-v-
	----
	--<-
	Output: 7
	 
	Input:
	@-v
	---
	-^<
	Output: 8
	 

	Input: 
	@-v
	->-
	-^<
	Output: 8
	 

	Input:
	@-v
	->v
	-^<
	Output: -1
*/

import java.util.*;
import java.io.*;

class Point {
	int row;
	int col;

	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

class Status {
	Point point;
	Direction direction = Direction.NONE;

	public Status(Point p, Direction d) {
		point = p;
		direction = d;
	}

	public boolean equals(Object o) {
       if (!(o instanceof Status)) return false;
       Status status = (Status) o;
       return status.point.row == point.row 
       		&& status.point.col == point.col
       		&& status.direction == direction;
    }

    public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + point.row;
		result = prime * result + point.col;
		result = prime * result + direction.hashCode();
		return result;
    }
}

enum Direction {
	EAST,
	NORTH,
	WEST,
	SOUTH,
	NONE
}

class Solution {
	// create the room
	private static Point getRoom(ArrayList<ArrayList<Character>> room) {
		Point point = null;
		try {
			File file = new File("input006.txt");
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNext()) {
				String input = scanner.nextLine();
				ArrayList<Character> line = new ArrayList<Character>();
				for(int i = 0; i < input.length(); i++) {
					char ch = input.charAt(i);
					if(ch == '@') {
						point = new Point(room.size(), i);
						ch = '-';
					}
					line.add(ch);
				}
				room.add(line);
			}
			scanner.close();
		}
    	catch (Exception e) {
    		e.printStackTrace();
    	}
    	return point;
	}

	// get direction according char
	private static Direction getDirection(char dir) {
		Direction direction = null;
		switch(dir) {
			case '^':
				direction = Direction.NORTH;
				break;
			case 'v':
				direction = Direction.SOUTH;
				break;
			case '<':
				direction = Direction.WEST;
				break;
			case '>':
				direction = Direction.EAST;
				break;
			default:
				direction = Direction.NONE;
		}
		return direction;
	}

	// check if hit the wall
	private static boolean isHitWall(ArrayList<ArrayList<Character>> room, Point current) {
		if(current.col == room.get(0).size() || current.col == -1) return true;
		if(current.row == room.size() || current.row == -1) return true;
		return false;
	}

    public static void main(String args[]) throws Exception {
    	// input
    	ArrayList<ArrayList<Character>> room = new ArrayList<ArrayList<Character>>();
    	Point current = getRoom(room);
    	// initial direction
    	Direction direction = Direction.EAST;
    	// record distance
    	int distance = 0;
    	char ch = '-';
    	HashSet<Status> visited = new HashSet<Status>();
    	while(!isHitWall(room, current)) {
    		ArrayList<Character> row;
    		switch(direction) {
    			case EAST:
    				row = room.get(current.row);
					if(ch == '>' && current.col < row.size()) {
    					distance++;
    					current.col++;
    				}
    				while(current.col < row.size() && (ch = row.get(current.col)) == '-') {
    					distance++;
    					current.col++;
    				}
    				// next direction
    				direction = getDirection(ch);
    				break;
    			case WEST:
    				row = room.get(current.row);
    				if(ch == '<' && current.col >= 0) {
    					distance++;
    					current.col--;
    				}
    				while(current.col >= 0 && (ch = row.get(current.col)) == '-') {
    					distance++;
    					current.col--;
    				}
    				// next direction
    				direction = getDirection(ch);
    				break;
    			case NORTH:
    				if(ch == '^' && current.row >= 0) {
    					distance++;
    					current.row--;
    				}
    				while(current.row >= 0 && (ch = room.get(current.row).get(current.col)) == '-') {
    					distance++;
    					current.row--;
    				}
    				// next direction
    				direction = getDirection(ch);
    				break;
    			case SOUTH:
    				if(ch == 'v' && current.row < room.size()) {
    					distance++;
    					current.row++;
    				}
    				while(current.row < room.size() && (ch = room.get(current.row).get(current.col)) == '-') {
    					distance++;
    					current.row++;
    				}
    				// next direction
    				direction = getDirection(ch);
    				break;
    			case NONE:
    				distance = -1;
    				break;
    		}
    		Status status = new Status(current, direction);
    		if(visited.contains(status)) {
    			distance = -1;
    			break;
    		}
    		visited.add(status);
    	}
    	System.out.println(distance);
    }
}


