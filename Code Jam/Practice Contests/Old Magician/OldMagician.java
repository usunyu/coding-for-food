import java.io.*;
import java.util.*;

public class OldMagician {
	private static ArrayList<Integer> whiteList = new ArrayList<Integer>();
	private static ArrayList<Integer> blackList = new ArrayList<Integer>();
	private static ArrayList<String> outputList = new ArrayList<String>();

	private static void input(String filePath) {
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			int n = sc.nextInt();
			// for each case
			for(int i = 0; i < n; i++) {
				// read W and B
				int w = sc.nextInt();
				int b = sc.nextInt();
				whiteList.add(w);
				blackList.add(b);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void solve() {
		for(int i = 0; i < whiteList.size(); i++) {
			String outStr = "Case #" + (i + 1) + ": ";
			if(blackList.get(i) % 2 == 1)
				outStr += "BLACK";
			else
				outStr += "WHITE";
			outputList.add(outStr);
		}
	}

	private static void output(String filePath) {
		try {
			PrintWriter out = new PrintWriter(filePath);
			for(String output : outputList)
				out.println(output);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		input(args[0]);
		solve();
		output(args[0] + ".out");
	}
}