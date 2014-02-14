import java.io.*;

class ShowTeamApp {
	static char[] members;
	static int sum;
	static int select;

	public static void showTeams(String output, int i) {
		if(output.length() == select) {
			System.out.println(output);
			return;
		}
		if(i >= sum) {
			return;
		}
		// include
		showTeams(output + members[i], i + 1);
		// not include
		showTeams(output, i + 1);
	}

	public static void main(String[] args) {
		sum = 5;
		select = 3;
		members = new char[sum];
		for(int i = 0; i < sum; i++)
			members[i] = (char)('A' + i);
		showTeams("", 0);
	}
}



