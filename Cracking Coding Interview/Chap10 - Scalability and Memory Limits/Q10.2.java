import java.util.*;

// HashMap
class Member {
	long id;
	String name;
}

// Graph
class MemberNode {
	long id;
	String name;
	ConnectNode firstConnect;
}

class ConnectNode {
	long id;
	ConnectNode nextConnect;
}

class ConnectGraph {
	MemberNode[] memberList;
	long memberCount;

	public void addMember(MemberNode member) {

	}

	public void addConnect(long id1, long id2) {
		
	}
}

class Q10_2App {
	public static void main(String[] args) {
		HashMap<Member, ArrayList<Member>> relationMap = new HashMap<Member, ArrayList<Member>>();
	}
}